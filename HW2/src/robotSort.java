//Student: Michael Weatherburn
import edu.princeton.cs.algs4.StdOut;

//“Robot sort” a deque of items following three rules. 
//1. Take two sheets off of the head of the deque. 
//2. Exchange the order of the two sheets at the head of the deque. 
//3. Move the head sheet to the tail of the deque. 

/*
 *  private robotSort()  {}                     // Do not need to instantiate 
	public static void sort(Deque<Integer> deque)  // Sort the deque so Head is smallest item 
	private static void exch(Deque<Integer> deque)     //Exchange order of sheets at Head 
	private static void shift(Deque<Integer> deque)    // Move item from Head to Tail 
	private static boolean isSorted(Deque<Integer> deque)  //Is the deque sorted? 
	main()                   // for testing: see details below  
 */
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Knuth;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;

public class robotSort {
	public static void sort(Deque<Integer> deque) 
	{
		// Sort the deque so Head is smallest item 
		for(int i=0; i<deque.size(); i++) {
			//if sorted then break, otherwise check values
			if (isSorted(deque)==true)
			break;
			for(int j=0; j<deque.size()-1; j++) {
				if(deque.peek(0)>deque.peek(1)) {
					exch(deque);
				}
				shift(deque);
			}
		shift(deque);
		}
	} 
	
	
	private static void exch(Deque<Integer> deque) 
	{
		//Exchange order of sheets at Head 
		int a = deque.popHead();
		int b = deque.popHead();
		deque.pushHead(a);
		deque.pushHead(b);
	}     
	
	
	private static void shift(Deque<Integer> deque) 
	{
		// Move item from Head to Tail 
		int n = deque.popHead();
		deque.pushTail(n);
		
	}   
	
	
	private static boolean isSorted(Deque<Integer> deque) 
	{//Is the deque sorted? 
		
		int size = (int)deque.size();
		int i = 0;
		boolean f = false;

		while(i < size-1) {
		if((int)deque.peek(i) < (int)deque.peek(i+1))
		f = true;
		else
		return false;

		i++;
		}

		return f;
	}  

	public static void main(String[] args) {
		
		//Create deque
		Deque mydq = new Deque();
		
//      Input: n the number of sheets in the book.  
		
		StdOut.println("Enter the number of sheets you want to add to the deque: ");
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt() ;
//Start Timing calculations
		double time = 0;
		Stopwatch timer = new Stopwatch();
		time = timer.elapsedTime();
//	  Create synthetic data:  //	  Create an Integer array sdata[] with elements sdata[i] = i 
	
		Integer[] sdata;
		sdata = new Integer[n];
		for(int i=0; i<n; i++) {
			sdata[i] = i;
		}
		StdOut.println("\nThis is our data set [array]: ");
		for(int i: sdata) {
			StdOut.print(i + " ");
		}
		
//	  Randomize sdata[] using Knuth.java  
		
		Knuth.shuffle(sdata);
		StdOut.println("\n\nThis is the knuth data that has been randomized[array]: ");
		
//	(This simulates out of order one‐sided sheets of a book.) 
//	  Print sdata 
		
		for(int i: sdata) {
			StdOut.print(i + " ");
		}
		
//	  Load deque with pushHead()  so Tail node has item that was in sdata[0]  
		
		for(int i: sdata) {
			mydq.pushHead(i);
		}
		
		//Printing deque
		
		StdOut.println("\n\nThis is my deque created from array: ");
		for(Object i:mydq) {
			StdOut.print(i + " ");
		}
//	  Sort and print deque
		StdOut.println("\n\nThis is the sorted deque:");
		sort(mydq);
		for(Object i:mydq) {
			StdOut.print(i + " ");
		}
		
		time = timer.elapsedTime();
//	  Print statements should include text describing output 
       robotSortAnalysis.dataPrint(time, n);
       
	}

}
