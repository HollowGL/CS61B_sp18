package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        Queue<Integer> searchNodes = new Queue<>();
        searchNodes.enqueue(s);

        while (!searchNodes.isEmpty()) {
            int cur = searchNodes.dequeue();
            marked[cur] = true;
            announce();
            if (cur == t) {
                return;
            }
            for (int w: maze.adj(cur)) {
                if (!marked[w]) {
                    searchNodes.enqueue(w);
                    distTo[w] = distTo[cur] + 1;
                    edgeTo[w] = cur;
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

