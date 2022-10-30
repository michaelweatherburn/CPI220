 /* 
 * API
   public class Deque<Item> implements Iterable<Item>{
   public Deque()                   									 // construct an empty deque
   public boolean isEmpty()          						// return true if the queue is empty, false otherwise
   public int size()  													// return number of items in deque
   public boolean validItem(Item item)  		// return true if item is not null; null items not allowed on deque
   public void pushHead(Item item)   				// insert the item at the head of the deque; does not push null item
   public void pushTail(Item item)  				  // insert the item at the tail of the deque; does not push null item
   public Item popHead()           						  // delete and return the item at the head in the deque; if no item exists, return null
   public Item popTail()            							 // delete and return the item at the tail in the deque; if no item exists, return null
   public Item peek(int numSteps)      			 // return the  item numSteps from the Head in the deque (0 returns Head); return null if invalid numSteps
   public Iterator												    // iterate from Head to Toe
   }
 *
 * main() at the end of this file tests the deque ADT
 * It reads the file dequeTest.txt , which is expected to be a list of strings
 * See main for the tests of the functionality of deque
 * 
 * Written by: Dianne Hansford, August 2017
 */


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{

		private Node head;
		private Node tail;
		private int numItems;
		
		private class Node {
			Item item;
			Node headDir;
			Node tailDir;
		}
		
		// Constructor 
		public Deque () {
			head = null;
			tail = null;
			numItems = 0;
		}
		
		public boolean isEmpty() { return numItems == 0; }
		
		public int size() { return numItems; }
		
		public boolean validItem(Item item) {return item != null; }
		
		public void pushTail(Item item) {
			
			// check item not null
			if(validItem(item)) {
			
				// deque empty, create first node
				if(isEmpty()) {
					tail = new Node();
					tail.item = item;
					tail.headDir = null;
					tail.tailDir = null;
					head = tail;
				}
				// deque not empty, add node and fix pointers
				else {
					Node oldTail = tail;
					tail = new Node();
					tail.item = item;
					tail.headDir = oldTail;
					tail.tailDir = null;
					oldTail.tailDir = tail;	
				}
				// update number of items in deque
				numItems++;
			}
		}
		
		public void pushHead(Item item) {
			
			// check that input item is not null
			if(validItem(item)) {
				
				// if deque empty, add first node and fix pointers
				if(isEmpty()) {
					head = new Node();
					head.item = item;
					head.headDir = null;
					head.tailDir = null;
					tail = head;
				}
				// deque not empty; add node and fix pointers
				else {		
					Node oldHead = head;
					head = new Node();
					head.item = item;
					head.headDir = null;
					head.tailDir = oldHead;
					oldHead.headDir = head;
				} 
				// increment the number of items in deque
				numItems++;
			}
		}
		
		public Item popTail( ) {

			// no items to return
			if(isEmpty()) return null;
			
			// save the tail item
			Item poppedItem = tail.item;
			
			// popping last item off deque
			if(head == tail) {
				head = null;
				tail = null; 
			}
			// not last item in deque; popping item off and fix pointers
			else {					
				Node newTail = tail.headDir;
				tail = newTail;
				tail.tailDir = null;
				newTail = null;						 
			}
			
			// decrement the number of items on deque
			numItems--;
						
			return poppedItem;
		}
		
		public Item popHead( ) {
			
			// no items to return
			if(isEmpty()) return null;
			
			// save the head item
			Item poppedItem = head.item;
				 
			// last item in deque
			if(head == tail) {
				head = null;
				tail = null; 
			}
			// not last item on deque; pop and fix pointers
			else {
				Node newHead = head.tailDir;
				head = newHead;			
				head.headDir = null;
				newHead = null; 									 
			}
			
			// decrement the number of items on deque
			numItems--;	
	 
			return poppedItem;
		}
		
		public Item peek(int numSteps) {
		
			// if deque empty, return null indicating that numSteps invalid
			if(numItems == 0) return null;
			
			// traverse deque head to toe to entry numSteps
			Node current = head;
			for(int i=1;  i <= numSteps;  i++) {
				current = current.tailDir;
			}
			return current.item;
		}
		
		// This function is not part of API any longer
		// Find the number of occurances of item in the deque
		public int peekForItem(Item item) {
			
			// variable to record occurrences of item
			int num = 0;
			
			// if deque empty, return zero count
			if(numItems == 0) return 0;
			
			// traverse deque head to toe
			Node current = head;
			for(int i=0; i<numItems; i++) {
				if(current.item.equals(item)) num++;
				current = current.tailDir;
			}
			// output number of occurences of input item 
			return(num);
		}
		
		/*
		 * iterate from head to tail
		 * @see java.lang.Iterable#iterator()
		 */
		public Iterator<Item> iterator() { return new Head2ToeIterator(); }
		
		private class Head2ToeIterator implements Iterator<Item> {
			private Node current = head;
			public boolean hasNext() { return current != null; }
			public void remove() { }  // leave empty
			public Item next() {
				Item item = current.item;
				 current = current.tailDir;
				return item;
			}
		}
		

		
		/* ******************************************************************************************  */
		/* 
		 * main() for testing Deque
		 */
		public static void main(String[] args) {
			
			 Deque<String> history1 = new Deque<String>();
			 Deque<String> history2 = new Deque<String>();
			 Deque<String> history3 = new Deque<String>();
			 Deque<String> history4 = new Deque<String>();
			 Deque<String> history5 = new Deque<String>();
			 
			 /*
			  * Check performance of constructor
			  */
			 StdOut.println("Hello Deque history1,  are you empty?  " + history1.isEmpty() + "           size=  " + history1.size());
			 StdOut.println("Hello Deque history2,  are you empty?  " + history2.isEmpty() + "           size=  " + history2.size());
			 StdOut.println("Hello Deque history3,  are you empty?  " + history3.isEmpty() + "           size=  " + history3.size());
			 StdOut.println("Hello Deque history4,  are you empty?  " + history4.isEmpty() + "           size=  " + history4.size());
			 StdOut.println("Hello Deque history5,  are you empty?  " + history5.isEmpty() + "           size=  " + history5.size());
			 StdOut.println(" ");
			 
			 /*
			  * Read an Input file with strings
			  */
			 //Can take file name as argument
			 //StdOut.println("input file name = " + args[0]); 
			 //In inputFile = new In(args[0]);		 
			 
			 //Simplify grading by reading a fixed file
			 In inputFile = new In("dequeTest.txt");
			 
			 /*
			  * TEST 1:
			  * Read,  push to Head, print from Head to Tail with foreach
			  */
			 	  
			 while(!inputFile.isEmpty()) {
				 String s = inputFile.readString(); 
				 history1.pushHead(s);
			 }
			 
			 StdOut.println(" ");
			 StdOut.println("TEST 1 ");
			 StdOut.println("Deque  loaded by push to Head       empty = " + history1.isEmpty() + "       size=  " + history1.size()); 
			 StdOut.println("Print head to tail ");
			 for(String s : history1)
				 StdOut.print(s + " ");
			 StdOut.println("  ");
			 
			/*
			 * TEST 2
			 * RE-open file
			 * Read,  pushed to Tail, print from Head to Tail with foreach
			 */
			
			 inputFile = new In("dequeTest.txt");
	  
			 while(!inputFile.isEmpty()) {
				 String s = inputFile.readString(); 
				 history2.pushTail(s);
			 }
			 
			 StdOut.println(" ");
			 StdOut.println("TEST 2 ");
			 StdOut.println("Deque  loaded loaded by push to Tail       empty = " + history2.isEmpty() + "       size=  " + history2.size()); 
			 StdOut.println("Print head to tail ");
			 for(String s : history2)
				 StdOut.print(s + " ");
			 StdOut.println("  ");
			 
			 /*
				 * TEST 3
				 * RE-open file
				 * Read,  pushed to Head, pop all from Head, print from Head to Tail with foreach
				 */
				
				 inputFile = new In("dequeTest.txt");
		  
				 while(!inputFile.isEmpty()) {
					 String s = inputFile.readString(); 
					 history3.pushHead(s);
				 }
				 while(history3.size() > 0)  {
					 history3.popHead(); 
				 }
				 
				 StdOut.println(" ");
				 StdOut.println("TEST 3");
				 StdOut.println("Deque  loaded by push from Head and pop all from Head       empty = " + history3.isEmpty() + "       size=  " + history3.size()); 
				 StdOut.println("Print head to tail ");
				 for(String s : history3)
					 StdOut.print(s + " ");
				 StdOut.println("  ");
				 
				 /*
					 * TEST 4
					 * RE-open file
					 * Read,  pushed to Tail, pop all from Tail, print from Head to Tail with foreach
					 */
					
					 inputFile = new In("dequeTest.txt");
			  
					 while(!inputFile.isEmpty()) {
						 String s = inputFile.readString(); 
						 history4.pushTail(s);
					 }
					 while(history4.size() > 0)  {
						 history4.popTail(); 
					 }
					 
					 StdOut.println(" ");
					 StdOut.println("TEST 4");
					 StdOut.println("Deque  loaded by push from Tail and pop all from Tail       empty = " + history4.isEmpty() + "       size=  " + history4.size()); 
					 StdOut.println("Print Head to Tail ");
					 for(String s : history4)
						 StdOut.print(s + " ");
					 StdOut.println("  ");
					 
					 /*
					  * TEST 5:
					  * Read,  push to Head and Tail (duplicating item), pop last item from head and tail, print from Head to Tail with foreach
					  */
					 inputFile = new In("dequeTest.txt");
					 
					 while(!inputFile.isEmpty()) {
						 String s = inputFile.readString(); 
						 history5.pushHead(s);
						 history5.pushTail(s);
					 }
					 
					 StdOut.println(" ");
					 StdOut.println("TEST 5 ");
					 StdOut.println("Deque  loaded by push to Head and Tail  (duplicate item)      empty = " + history5.isEmpty() + "       size=  " + history5.size()); 
					 StdOut.println("Print head to tail ");
					 for(String s : history5)
						 StdOut.print(s + " ");
					 StdOut.println("  ");
					 
					 String poppedHead  = history5.popHead();
					 String poppedTail =     history5.popTail();
					 StdOut.println("Popped one item from Head and Tail       empty = " + history5.isEmpty() + "       size=  " + history5.size()); 
					 StdOut.println("Popped Tail string  " + poppedTail + "     Popped Head string " + poppedHead);
					 
					 // Test the validItem method
					 history5.pushTail(null);
					 StdOut.println("Pushed null item  now  size=  " + history5.size()); 
			 
					 /*
					  * Test Peek by printing out the last two items in history5
					  */
					 StdOut.println("   " );
					 StdOut.println("Test Peek for " );
					 for(int i=0;  i<2; i++)
						 StdOut.println("Peek    " + i +  "   " +history5.peek(i));
						 
			 
			 StdOut.println(" ");
			 StdOut.println("Goodbye Deque");
	    } 
}

