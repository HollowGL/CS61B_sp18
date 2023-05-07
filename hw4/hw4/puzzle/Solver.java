package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;

public class Solver {

    private MinPQ<SearchNode> minPQ = new MinPQ<>();
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

        cur = minPQ.delMin();
        while (!cur.state.isGoal()) {
            for (WorldState neibor : cur.state.neighbors()) {
                /* The critical optimization only checks that
                 * no enqueued WorldState is its own grandparent!*/
                if (cur.prev == null || !neibor.equals(cur.prev.state)) {
                    minPQ.insert(new SearchNode(neibor, cur.moveCnt + 1, cur));
                }
            }
            cur = minPQ.delMin();
        }
    }

    public int moves() {
        return cur.moveCnt;
    }

    public Iterable<WorldState> solution() {
        ArrayList<WorldState> sol = new ArrayList<>();
        while (cur != null) {
            sol.add(cur.state);
            cur = cur.prev;
        }
        ArrayList<WorldState> reverse = new ArrayList<>();
        for (int i = sol.size() - 1; i >= 0; i--) {
            reverse.add(sol.get(i));
        }
        return reverse;
    }
}
