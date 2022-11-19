/*
 * NAME: Michael Weatherburn
 * 
 * aMAZing! This is a client for the Maze class.
 * This client generates an n-by-n maze, solves it with three methods, and draws the maze and the solutions.
 * A boolean in Maze class allows a choice between perfect or imperfect maze.
 * Solutions to the maze find a path from cell (startx, starty) to cell (solnx, solny).
 * The three solutions are as follows.
 * 1) The method in solve() instance method from the Maze class.
 * 2) The maze is converted to a graph and then the Depth First Path (DFP) is found.
 * 3) The maze is converted to a graph and then the Breadth First Path (BFP) is found.
 * The solutions are color coded:
 *  	Red circle indicates start and end. 
 *  	Blue circle indicates path taken by the Maze class solve() function. 
 *  	Gray circle indicates backtracking steps made by the solve() function.
 *      Green circle for DFP
 *      Orange circle for BFP
 *  
 *  
 *  HOMEWORK QUESTIONS: (Not expecting long answers, but well written answers.)
 *  1) What is an perfect maze? (Google it)
 *  
 *  answer: A perfect maze is where every point is reachable and where there is only one single path from one point in the maze to any other point. That's a perfect a maze
 *  
 *  2) Which program settings result in DFS and BFS returning the same solution? Why?
 *  
 *  answer:  Imperfect or false results leads to DFS and BFS producing the same solution even though a perfect maze has only one solution
 *  
 *  3) When DFS and BFS are different, which one is better? Why?
 *   
 *  answer: BFS tends to produce the shorter path bc it is focused on breadth searching, not depth
 *  
 *  4) Why is the Blue solution producing Gray circles?
 *  
 *  answer: A gray circle is produced when we hit a point in the maze where we have to backtrack a step to find another path becaue we hit a wall in the previous spot. So the grey circle shows where we need to backtrack in order to go to the next path
 *   
 *  
 *  
 */
import edu.princeton.cs.algs4.StdDraw; 
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.DepthFirstPaths;
import edu.princeton.cs.algs4.BreadthFirstPaths;

public class aMAZing {
	
	/*
	 * Convert a cell (x,y) to an integer vertex index in the Graph
	 */
	 
	public static int xyToV(int n, int x, int y) {
		//TO DO
		int v = (x-1) + ((y-1) * n );
		return v;
	}
	

	/* 
	 * Convert a integer vertex index in the Graph to a (x,y) in the cell
	 * (Output stored in xy)
	 */
	 
    public static void vToXY(int n, int v, int[] xy) {
		//TO DO
    	xy[0] = (v % n) + 1;
		xy[1] = (v / n) + 1;
	}
	
	
	/*
	 * Convert the Maze data structure to a Graph data structure
	 * The Maze data structure starts at (0,0) and runs to (n+1,n+1), 
	 * but the actual maze occupies cells (1,1) to (n,n).
	 * Graph vertex 0 should correspond to (1,1), vertex 1 to (2,1), and vertex n^2 -1 to (n,n)
	 * 
	 */
	 
	public static Graph Maze2Graph(Maze maze, int n) {
		
		boolean[][] north = new boolean[n+2][n+2];    
	    boolean[][] east = new boolean[n+2][n+2];
	    boolean[][] south = new boolean[n+2][n+2];
	    boolean[][] west = new boolean[n+2][n+2]; 
		
	    maze.outputMaze(north, east, south, west);
	     
		 //TO DO
	    Graph graph = new Graph(n*n);
	    for(int i=1; i<=n; i++) {
	    	for(int j=1; j<=n; j++) {
	    		xyToV(n, j, i);
	    	}
	    }
	    for(int i = 1; i<=n; i++)
	    {	
	    	for(int j = 1; j<=n; j++) 
	    	{	
	    		if(!north[i][j])graph.addEdge(i, j+1);
	    		if(!east[i][j])graph.addEdge(i, j+1);
	    		//if(!south[i][j])graph.addEdge(i, j-1);
	    		//if(!west[i][j])graph.addEdge(i, i-1);
	    	}
	    	
	    }
	    
	    return graph;  
	    
	}
	
	
	public static void main(String[] args) { 
		
			// Input n  the size of the nxn maze 
			//int n = Integer.parseInt(args[0]);
			int n = 4;
			StdOut.println("Maze size n = " +n);
			
			// set start cell  
			int startx = 1;
	        int starty = 1;
			// end cell -- define as instructed in the HW specifications
	        int solnx = n;
	        int solny = n;
	        //int solnx = ??;
	        //int solny = ??;
	        StdOut.println("Solve maze for start = x= " +startx +" y= " +starty);
	      //  StdOut.println("            solution = x= " +solnx +" y= " +solny);    
	        // creating int array to store (x,y)
	        int[] xy = new int[2];
			
			// base radii from that used in Maze class
			double greenRadius = 0.25 - 0.08;
			double orangeRadius = greenRadius - 0.08;
			int delayTime = 30;
			
			StdOut.println("aMAZing!");
			
			// TO DO: Code here should be like the code in Maze.java main()
			// create the maze, draw it, and solve it using the method solve()
		    Maze maze = new Maze(n);
		     
			StdDraw.enableDoubleBuffering();
			maze.draw(solnx, solny);
			
		   
			maze.solveIt(startx, starty, solnx, solny);
			
			// leave display up
			StdDraw.pause(1000);
	         
	        // TO DO Create an undirected Graph-- call your Maze2Graph function
	        // TO DO Print Graph
	         
		    StdOut.println(Maze2Graph(maze, n));
	         
	        // TO DO define a source (start) and sink (end) of the maze puzzle
		    int source = xyToV(n,startx,starty);
			int sink = xyToV(n,solnx,solny);
			
	        StdOut.printf("Solve Graph with source = %d  sink = %d",source,sink);
			
	        StdOut.printf("Solve Graph with source = %d  sink = %d",source,sink);
	        StdOut.println(" ");
	        
	        // TO DO Depth first solution
			// TO DO: use greenRadius for circle
			// TO DO: Print path
	        
	        StdOut.println("Depth First Path -- Green ");
	        
	        DepthFirstPaths dfs = new DepthFirstPaths(Maze2Graph(maze,n), source);
	        
	        StdDraw.setPenColor(StdDraw.GREEN);
			// this is the basic drawing code to draw a circle at xy
			
			StdDraw.filledCircle(xy[0] + 0.5, xy[1] + 0.5, greenRadius);
    	        StdDraw.show();
    	        StdDraw.pause(delayTime);
			
			
	        
	        
	         
  
	        // TO DO Breadth First solution
			// TO DO: use orangeRadius for circle
			// TO DO: Print path
			
	        
	        StdOut.println("Breadth First -- Orange ");
	        
	        BreadthFirstPaths bfs = new BreadthFirstPaths(Maze2Graph(maze,n), source);
	         
	        StdDraw.setPenColor(StdDraw.ORANGE);
	        
	        StdDraw.filledCircle(xy[0] + 0.5, xy[1] + 0.5, orangeRadius);
	        StdDraw.show();
	        StdDraw.pause(delayTime);
	        
	        
	        
	        StdDraw.pause(1000);
	}

}
