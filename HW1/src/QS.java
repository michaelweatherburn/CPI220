

/**
 * QS.java
 *
 * Class "QS" for QueueStack
 *  This code was developed from the Queue class from the algs4 library.
 *  The original Queue class was modified to have 
 *  pop at he enqueue end ("last") and
 *  push at the dequeue end("first")
 
 Original code Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
 */
 
import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;


public class QS<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue (dequeue end)
    private Node<Item> last;     // end of queue (enqueue end)
    private int n;               // number of elements on queue

    // helper doubly linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    /**
     * Initializes an empty queue.
     */
    public QS() {
        first = null;
        last  = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty.
     *
     */
    public boolean isEmpty() {
    	if(n == 0) return true;
    	else return false;
    }

    /**
     * Returns the number of items in this queue.
     *
     */
    public int size() {
        return n;
    }

    /**
     * Returns the item least recently added to this queue.
     * throws NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    /**
     * Adds the item to this queue.
     *
     */
    public void enqueue(Item item) {
       
    	Node<Item> oldLast = last;
    	
    	last = new Node<Item>();
        last.item = item;
        last.next = null;
        last.prev = oldLast;
        
        if (isEmpty())  
        	first = last;
        else  oldLast.next = last;
        
        n++;
        
    }
    
    /**
     * To Do: pop item from queue "last" (enqueue end)
     *
     */
     /* 
    public Item pop() {
    	return item;
    	
    }
     */

    /**
     * Removes and returns the item on this queue that was least recently added.
     * throws NoSuchElementException if this queue is empty
     */
    public Item dequeue() {
    	
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        
        Item item = first.item;
        first = first.next;
                  
        n--;
        
        if (isEmpty()) {
        	last = null;    
        }
        else {
        	first.prev = null;
        }
        
        return item;
    }
    
	/* To Do: add this function */
    public void push(Item item) {
        
    }
    

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    } 

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<Item> iterator()  {
        return new ListIterator(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
    // ***********************************************************************
	// draw()
    /* Draw QS if there are items in it
     * The drawing space is the unit square [0,1] x [0,1].
     * QS items are represented as circles, centered on the x-axis at y=0.5.
     * If the animate flag is set, one of the items will move up, appearing to disappear.
     * A pair of calls to this function with a dequeue or pop will animate the departure of an item.
     * Example:
     *    QS.draw(0, 100, numColors, colors, radius);
     *	  QS.dequeue();
     *	  QS.draw(-1, 1000, numColors, colors, radius);
     *
     * int ianimate = -1 do not animate
     *     ianimate >= 0 animate the kth item in the queue (k=0, 1, ...) (if out of bounds, none will animate)
     * 
     * int pauseTime: pauses for t milliseconds
     * int numColors: number of colors in input color array
     * int color[][]: rgb colors
     * double radius: radius for circles (drawing space is unit square [0,1] x [0,1])
     */
	    public void draw(int ianimate, int pauseTime, int numColors, int [][] colors, double radius) {
	    	
	    	StdDraw.enableDoubleBuffering();
	    	
	    	int qsize = size();
	    	//StdOut.println("\n draw: size of QS = " + qsize);
	    	    	
	    	// draw if the queue has items
	    	if(qsize > 0) {
	    		 		
	    		// create some variables to make plot look "nice"
	    		// use up 3/4 of the screen space which is one unit wide
	    		double maxXlgth = 0.75;
	    		// x-axis goes from 0 to 1. Scale and translate to center the circles
	    		double translate = 0.5 - maxXlgth/2.0;
	    		
	    		
	    		// set number of times to animate a circle if the ianimate flag is set to valid item in QS
	    		int numIters = 1;
	    		if(ianimate != -1)numIters = 10; 
	    		
	    		// set the step size in y to animate the circle
	    		double delta = 0.4/numIters;
	    		
	    		//StdOut.println("\n draw: ianimate = " + ianimate);
	    		
	    		// animate by redrawing with different y-value for a circle
	    		for(int iter=1; iter <= numIters; iter++) {
	    		
	    			// keep track of which entry in qs is being processed
		    		int k = -1;
		    		
		    		// clear the screen
		    		StdDraw.clear();
		    	
			    	//StdOut.println("\n draw: iter = " + iter);
			    	
		    		// iterate thru each item in the QS
			        Iterator<Item> i = iterator();
			        
			        while(i.hasNext()) {
			        	Item j = i.next();
			        	//StdOut.print(j + " ");
			        	
			        	// x position a function of the kth item
			        	// if last item, center it
			        	// y position is just center
			        	k++;
			        	double x;
			        	if(qsize == 1) {
			        		x = 0.5;
			        	}
			        	else x = (1.0*k/(qsize-1))*maxXlgth + translate;
			        	double y = 0.5;
			        	// animate the kth entry by changing the y-value
			        	if(k == ianimate) y = 0.5 + (iter - 1)*delta;
			        		
			        	//StdOut.println("k="+ k + "  x=" + x + "  y=" + y + "  radius = " + radius);
			        	
			        	// grab color in color map based on item
			        	int icolor = Math.floorMod((int) j, numColors); 
			        	StdDraw.setPenColor(colors[icolor][0], colors[icolor][1], colors[icolor][2]);
			        	//StdOut.println("item = " + (int) j + "  icolor = "+ icolor);
			        	
			        	
			        	StdDraw.filledCircle(x, y, radius);
			        	//StdOut.println("draw: color  " + colors[icolor][0] + " " +colors[icolor][1] + " " +  colors[icolor][2]);
			        }
			         
			        StdDraw.show();
			        StdDraw.pause(pauseTime);
			        
		    	}
	    		// before leaving, clear the screen
	    		//StdDraw.clear();
		        //StdDraw.show();
	    	}
	    }

    /**
     * Unit tests the QS data type.
     * 
     * Functionality:
     * Three tests take place
	 * Test 1: enqueue items and then dequeue all
	 * Test 2: push items and then pop all
	 * To Do: Test 3: enqueue all then alternately dequeue and pop until qs empty
	 *
	 * For all tests, a visualization of the items as circles is created.
	 * When items are removed, an animation of this removal is implemented
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
    	
        QS<Integer> qs = new QS<Integer>();
        
        // Define the number of items to put on QS
        // This will correspond to integers 1, 2, ... numberItems put on QS
		// start with a small number when testing, then increase for fun!
        int numberItems = 5;
         
        
        StdOut.print("Number of items to put on QS = " + numberItems);
        StdOut.println(" ");
        StdOut.println("In image: enqueue and pop on the right");
        StdOut.println("          dequeue and push on the left ");
        
        // create numberItems colors  
        // RGB format, where each component is between [0,255]
        // Here: red and green are set as random numbers and blue is 255 - green.
        int [][] colors;
        colors = new int [numberItems][3];
        for(int i=0; i<numberItems; i++) {
        	colors[i][0] = StdRandom.uniform(255);
        	colors[i][1] = StdRandom.uniform(255);
        	colors[i][2] = 255 - colors[i][1];
        }
         
        
        // specify the radius of each circle to draw 
        // (just a guess to make the circle size look good based on number of data)
        
		double minRadius = 0.001;
		double radius = 0.25/numberItems;
		if(radius < minRadius) radius = minRadius;
		//StdOut.println("radius =  " + radius);
		
		// create variables for long pause and short pause for animation
		// short for animation, long for just looking at it
		int shortPause = 100;
		int longPause = 500;
      
		/* 
		 * TEST #1  enqueue then dequeue
		 */
		 StdOut.println("\n\n TEST #1: enqueue then dequeue");
		// test enqueue
		// add integers 1, ..., numberItems to the QS using enqueue
        for(int i=1; i<= numberItems; i++)  {
        	qs.enqueue(i);
        	// draw w/o animate, pause briefly
        	qs.draw(-1, longPause, numberItems, colors, radius);
        }  
        
        // print the contents of the qs
        StdOut.println("\n Enqueued items to QS");
        StdOut.println(qs);
            
        // Demonstrate iterating thru qs
        // using long form of foreach
        /*
        StdOut.println("\n Print qs using iterator");
        Iterator<Integer> i = qs.iterator();
        while(i.hasNext()) {
        	Integer j = i.next();
        	StdOut.print(j + " ");
        }
        */
        // Demonstrate iterating thru qs
        //foreach
        /*
        StdOut.println("\n Print qs using foreach");
        for(Integer ival : qs)
        	StdOut.print(ival + " ");
        */
        
        
        // test dequeue
        for(int k = 1; k <= numberItems; k++)  {
        	// animate
        	qs.draw(0, shortPause, numberItems, colors, radius);
        	int value = qs.dequeue();
            // draw w/o animate, pause briefly
            qs.draw(-1, longPause, numberItems, colors, radius);
         }
             
    	/* 
		 * TEST #2  push then pop
		 */
        
        StdOut.println("\n\n TEST #2: push then pop");
        // test push
     	// add integers 1, ..., numberItems to the QS using enqueue
        for(int k=1; k<= numberItems; k++)  {
             qs.push(k);
             // draw w/o animate, pause briefly
             qs.draw(-1, longPause, numberItems, colors, radius);
         }  
             
        // print the contents of the qs
        StdOut.println("\n Pushed items to QS");
        StdOut.println(qs);
        
        // test pop
        for(int k = 1; k <= numberItems; k++)  {
        	// animate
        	qs.draw(qs.size()-1, shortPause, numberItems, colors, radius);
        	//int value = qs.pop(); must fix pop mweath
            // draw w/o animate, pause briefly
            qs.draw(-1, longPause, numberItems, colors, radius);
         }
        
        /* 
		 * To Do:
		 * TEST #3  enqueue all then alternately dequeue and pop until qs empty.
		 *Include a visualization as done in the previous Tests.
		 */
        StdOut.println("\n\n TEST #3: enqueue then alternate between dequeue and pop");
        
        
        
        
        
     
       
      // clear the window
      StdDraw.clear();
      StdDraw.show();
        
    }
}

