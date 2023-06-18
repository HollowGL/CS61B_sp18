package lab11.graphs;


/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private boolean cycleFound = false;
    private Maze maze;
    private int[] nodeTo;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        nodeTo = new int[maze.N() * maze.N()];
    }


    @Override
    public void solve() {
        // TODO: Your code here!
        dfs(0, -1);
    }

    // Helper methods go here
    private void dfs(int v, int parent) {
        marked[v] = true;
        announce();
        for (int w: maze.adj(v)) {
            if (!marked[w]) {
                nodeTo[w] = v;
                dfs(w, v);
            }
            else if (w != parent) { // cycle has been found
                cycleFound = true;
                edgeTo[w] = v;
                announce();
                for (int x = v; x != w; x = nodeTo[x]) {
                    edgeTo[x] = nodeTo[x];
                    announce();
                }
                return;
            }
            if (cycleFound) {
                return;
            }
        }
    }
}

