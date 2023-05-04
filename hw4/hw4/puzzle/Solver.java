package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.List;

public class Solver {

    private MinPQ<SearchNode> minPQ = new MinPQ<>();
    private int moves = 0;
    private SearchNode cur;

    private class SearchNode implements Comparable<SearchNode> {
        public WorldState state;
        public int moveCnt;
        public SearchNode prev;

        public SearchNode(WorldState init, int move, SearchNode p) {
            this.state = init;
            this.moveCnt = move;
            this.prev = p;
        }
        public SearchNode(WorldState init, int move) {
            this(init, move, null);
        }

        @Override
        public int compareTo(SearchNode o) {
            return moveCnt + state.estimatedDistanceToGoal() - o.moveCnt - o.state.estimatedDistanceToGoal();
        }
    }

    public Solver(WorldState initial) {
        minPQ.insert(new SearchNode(initial, 0));

        cur = minPQ.delMin();
        while (!cur.state.isGoal()) {
            for (WorldState neibor : cur.state.neighbors()) {
                minPQ.insert(new SearchNode(neibor, cur.moveCnt + 1, cur));
            }
            cur = minPQ.delMin();
            moves++;
        }
    }

    public int moves() {
        return moves;
    }

    public Iterable<WorldState> solution() {
        List<WorldState> sol = new ArrayList<>();
        while (cur != null) {
            sol.add(cur.state);
            cur = cur.prev;
        }
        return sol;
    }
}
