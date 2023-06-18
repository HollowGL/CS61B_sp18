package lab11.graphs;
import edu.princeton.cs.algs4.MinPQ;


/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private MinPQ<SearchNode> minPQ;
    private class SearchNode implements Comparable<SearchNode> {
        public int v;
        public SearchNode parent;
        public int priority;
        public SearchNode(int v, SearchNode p) {
            this.v = v;
            this.parent = p;
            this.priority = h(v);
        }
        @Override
        public int compareTo(SearchNode o) {
            return this.priority - o.priority;
        }
    }

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        marked[s] = true;
        announce();
        minPQ = new MinPQ<>(m.N() * m.N());
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - maze.toX(t)) + Math.abs(maze.toY(v) - maze.toY(t));
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        minPQ.insert(new SearchNode(s, null));
        SearchNode cur = minPQ.delMin();
        while (cur.v != t) {
            for (int node : maze.adj(cur.v)) {
                if (!marked[node]) {
                    minPQ.insert(new SearchNode(node, cur));
                }
            }
            cur = minPQ.delMin();
            edgeTo[cur.v] = cur.parent.v;
            distTo[cur.v] = distTo[cur.parent.v] + 1;
            marked[cur.v] = true;
            announce();
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

