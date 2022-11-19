
/******************************************************************************
 *  Maze.java
 *  Generates a perfect or imperfect  n-by-n maze using depth-first search with a stack.
 *  It solves the maze by starting at the (1,1) (bottom left) cell and finding its way to the cell defined by (solnx, solny).
 *  The test client draws the maze and the solution.
 *  	Red circle indicates start and end. 
 *  	Blue circle indicates path taken to end. 
 *  	Gray circle indicates backtracking steps that are not part of the solution to the maze.
 *   
 *  Compilation:  javac Maze.java
 *  Execution:    java Maze.java n
 *  Dependencies: StdDraw.java, StdOut, StdRandom
 *  
 *  The original Maze java class is from the algs4 library.
 *  This version is a modification for CPI 220 HW4.
 *  
 *  Details on the maze construction:
 *  -- A grid of size 0 ... n+1 x 0 ... n+1 is constructed, where n is input
 *  -- The outer band of the grid cells, for example cell (0,0) and (1, 0), are not part of the maze. 
 *     These cells help with the construction of the maze.
 *  -- Each cell has 4 potential walls (north, south, east, west). 
 *     At first, all cells have 4 walls and all 4 booleans are set to true.
 *     The maze is created by removing some walls by setting booleans to false.
 *     
 *  -- See below in the code "Some variables for HW" for variables that modify the maze creation 
 *     and drawing delay. These variables are helpful for testing (by removing randomness).
 *  
 ******************************************************************************/
import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;


public class Maze {
	
    private int n;                         // dimension of maze
    private boolean[][] north;     // is there a wall to north of cell i, j
    private boolean[][] east;
    private boolean[][] south;
    private boolean[][] west;
    private boolean[][] visited;
    private boolean done = false;
    
    /* ************************************************** */
    /* ****  Some variables for HW **************************************************** */
    /* ************************************************** */
    // Turn on/off random maze creation for testing
    // When testing, randomOn = false is best
    private boolean randomOn = true;
    
    // Speed of draw
    // Increase to slow down draw
    private int delayTime = 30;
    
    // Create a perfect or imperfect maze
    // Part of hw: look up what this means!
    private boolean imperfectMaze = true;
    /* *********************************************************** */

    /* Constructor: generate the maze based on user input n */
    
    public Maze(int n) {
        this.n = n;
        StdDraw.setCanvasSize(800,800);
        StdDraw.setXscale(0, n+2);
        StdDraw.setYscale(0, n+2);
        init();
        generate();
    }
    
    /* initialize the grid of cells.
     * set border cells to visited so they are not part of maze
     * create walls for all cells
     * */

    private void init() {
        // initialize border cells as already visited
        visited = new boolean[n+2][n+2];
        for (int x = 0; x < n+2; x++) {
            visited[x][0] = true;
            visited[x][n+1] = true;
        }
        for (int y = 0; y < n+2; y++) {
            visited[0][y] = true;
            visited[n+1][y] = true;
        }


        // initialze all walls as present
        north = new boolean[n+2][n+2];
        east  = new boolean[n+2][n+2];
        south = new boolean[n+2][n+2];
        west  = new boolean[n+2][n+2];
        for (int x = 0; x < n+2; x++) {
            for (int y = 0; y < n+2; y++) {
                north[x][y] = true;
                east[x][y]  = true;
                south[x][y] = true;
                west[x][y]  = true;
            }
        }
    }


    /* generate the maze
     * break down some of the walls
     */
    private void generate(int x, int y) {
    	
    	int increment = -1;
    	double r;
    	
        visited[x][y] = true;
        
        //StdOut.println("generate x= " +x +" y= " +y);

        // while there is an unvisited neighbor
        while (!visited[x][y+1] || !visited[x+1][y] || !visited[x][y-1] || !visited[x-1][y]) {

            // pick random neighbor (could use Knuth's trick instead)
        	// or turn off random for repeating test
            while (true) {
                if(randomOn) r = StdRandom.uniform(4);
                else r = ++increment;
                
                
                //StdOut.println("     randomOn=" +randomOn +"  random r = " +r);
                
                if (r == 0 && !visited[x][y+1]) {
                    north[x][y] = false;
                    south[x][y+1] = false;
                    generate(x, y + 1);
                    break;
                }
                else if (r == 1 && !visited[x+1][y]) {
                    east[x][y] = false;
                    west[x+1][y] = false;
                    generate(x+1, y);
                    break;
                }
                else if (r == 2 && !visited[x][y-1]) {
                    south[x][y] = false;
                    north[x][y-1] = false;
                    generate(x, y-1);
                    break;
                }
                else if (r == 3 && !visited[x-1][y]) {
                    west[x][y] = false;
                    east[x-1][y] = false;
                    generate(x-1, y);
                    break;
                }
            }
        }
    }

    // generate the maze starting from lower left
    private void generate() {
        generate(1, 1);

        // if imperfect maze wanted, then this code executes. 
        // the number of walls deleted can be modified (but keep as is for HW)
        if(imperfectMaze) {
	        // delete some random walls
	        for (int i = 0; i < 2*n; i++) {
	            int x = 1 + StdRandom.uniform(n-1);
	            int y = 1 + StdRandom.uniform(n-1);
	            north[x][y] = south[x][y+1] = false;
	        }
        }    
    }



    // solve the maze using depth-first search
    private void solve(int x, int y, int solnx, int solny) {
    	
    	//StdOut.println("solve x= " +x +" y= " +y); 	 
    	
        if (x == 0 || y == 0 || x == n+1 || y == n+1) return;
        if (done || visited[x][y]) return;
        visited[x][y] = true;

        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show();
        StdDraw.pause(delayTime);

        // reached middle
        //if (x == n/2 && y == n/2) done = true;
        // reached solution
        if (x == solnx && y == solny) done = true;
        
        // HW Question: Why not return here instead of below? 
        //if (done) return

        if (!north[x][y]) solve(x, y + 1, solnx, solny);
        if (!east[x][y])  solve(x + 1, y, solnx, solny);
        if (!south[x][y]) solve(x, y - 1, solnx, solny);
        if (!west[x][y])  solve(x - 1, y, solnx, solny);
        
        //StdOut.println("     checked all nghbr for solve x= " +x +" y= " +y);

        if (done) return;

        StdDraw.setPenColor(StdDraw.GRAY);
        StdDraw.filledCircle(x + 0.5, y + 0.5, 0.25);
        StdDraw.show();
        StdDraw.pause(delayTime);
    }

    // solve the maze starting from the start state
    // initialize all as not visited
    public void solveIt(int startx, int starty, int solnx, int solny) {
        for (int x = 1; x <= n; x++)
            for (int y = 1; y <= n; y++)
                visited[x][y] = false;
        done = false;
        solve(startx, starty, solnx, solny);
    }

    // draw the maze
    public void draw(int solnx, int solny) {
        StdDraw.setPenColor(StdDraw.RED);
        // DCH: they had n/2.0 causing different location than search
        StdDraw.filledCircle(solnx + 0.5, solny + 0.5, 0.375);
        StdDraw.filledCircle(1.5, 1.5, 0.375);

        StdDraw.setPenColor(StdDraw.BLACK);
        for (int x = 1; x <= n; x++) {
            for (int y = 1; y <= n; y++) {
                if (south[x][y]) StdDraw.line(x, y, x+1, y);
                if (north[x][y]) StdDraw.line(x, y+1, x+1, y+1);
                if (west[x][y])  StdDraw.line(x, y, x, y+1);
                if (east[x][y])  StdDraw.line(x+1, y, x+1, y+1);
            }
        }
        StdDraw.show();
        
        // DCH: moved this to main so I can control in client
        //StdDraw.pause(1000);
    }

    /* *** Function added to class for HW  ************************************************************* */
    // This allows a client to get the maze data structure
    public void outputMaze(boolean[][] northOut, boolean[][] eastOut, boolean[][] southOut, boolean[][] westOut) {
    	 
    	for (int x = 0; x <n+2; x++) {
            for (int y = 0; y <n+2; y++) {
            	northOut[x][y] = north[x][y];
            	eastOut[x][y] = east[x][y];
            	southOut[x][y] = south[x][y];
            	westOut[x][y] = west[x][y];
            }
    	}
    }

    /* **************************************************************** */

    // a test client
    // create maze, solve, and draw maze and solution path, including backtracking
    
    public static void main(String[] args) {
        
    	//int n = Integer.parseInt(args[0]);
    	// size of maze
    	int n = 4;
    	int startx = 1;
        int starty = 1;
        int solnx = n;  
        int solny = n;
        StdOut.println("Solve maze for start = x= " +startx +" y= " +starty);
        StdOut.println("            solution = x= " +solnx +" y= " +solny);        
    	
        Maze maze = new Maze(n);
     
        StdDraw.enableDoubleBuffering();
        maze.draw(solnx, solny);
        
       
        maze.solveIt(startx, starty, solnx, solny);
        
        // leave display up
        StdDraw.pause(1000);
        
        
        
      
    }

}

