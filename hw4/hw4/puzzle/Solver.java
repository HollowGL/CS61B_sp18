package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Solver {

    private MinPQ<SearchNode> minPQ = new MinPQ<>();
    private int moves = 0;
    private SearchNode cur;

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState state;
        private int moveCnt;
        private SearchNode prev;
        private int priority;

        public SearchNode(WorldState init, int move, SearchNode p) {
            this.state = init;
            this.moveCnt = move;
            this.prev = p;
            this.priority = moveCnt + state.estimatedDistanceToGoal();
        }
        public SearchNode(WorldState init, int move) {
            this(init, move, null);
        }

        @Override
        public int compareTo(SearchNode o) {
            return priority - o.priority;
        }
    }

    public Solver(WorldState initial) {
        minPQ.insert(new SearchNode(initial, 0));
        Set<WorldState> visitedState = new HashSet<>();

        cur = minPQ.delMin();
        visitedState.add(cur.state);
        while (!cur.state.isGoal()) {
            for (WorldState neibor : cur.state.neighbors()) {
                if (!visitedState.contains(neibor)) {
                    minPQ.insert(new SearchNode(neibor, cur.moveCnt + 1, cur));
                }
            }
            cur = minPQ.delMin();
            visitedState.add(cur.state);
            moves++;
        }
    }

    public int moves() {
        return cur.moveCnt;
    }

    public Iterable<WorldState> solution() {
        Stack<WorldState> sol = new Stack<>();
        while (cur != null) {
            sol.push(cur.state);
            cur = cur.prev;
        }
        return sol;
    }
}
