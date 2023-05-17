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
    public boolean cycleFound = false;
    public int cycleStart = -1;  // mark the start node of the cycle
    public int cycleMarked = 2;  // connect the start node first time when cycleMarked == 2, then secnond 1;

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        distTo[0] = 0;
        dfs(0, 0);
    }

    // Helper methods go here
    private void dfs(int v, int parent) {
        if (marked[v]) {
            cycleFound = true;
            cycleStart = v;
        }
        if (cycleFound) {
            return;
        }
        marked[v] = true;
        distTo[v] = distTo[parent] + 1;
        announce();

        for (int w: maze.adj(v)) {
            if (w != parent) {
                dfs(w, v);
                if (cycleFound && cycleMarked > 0) {
                    edgeTo[w] = v;
                    if (w == cycleStart) {
                        cycleMarked--;
                    }
                    announce();
                }
            }
        }
    }
}

