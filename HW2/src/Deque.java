/* Michael Weatherburn
 * Deque.java
 * This class implements a double-ended Queue or deque (pronounced "deck")
 *
 * 
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
import edu.princeton.cs.algs4.Knuth;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

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
		public Deque() {
			head = null;
			tail = null;
			numItems = 0;
		}
		
		public boolean isEmpty() { return numItems == 0; }
		
		public int size() { return numItems; }
		
		public boolean validItem(Item item) {return item != null; }
		
		public Deque<Item>.Node getHead() {
			return head;
		}
		
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
		 * ******************************************************************************************
		 * 
		 * 
		 * main() for testing Deque
		 */
		public static void main(String[] args) {
			//setting time for runs while n = sheets
			//start stopwatch then get time right before dataprint method is called to get time it took to run program before it got to that function
			double time = 0;
			Stopwatch stopwatch = new Stopwatch();
			
			StdOut.println("Enter the number of integers you want to add to the deque: ");
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt() ;
			
			
			// create Integer object array with  0, n-1
			Integer[] sdata;
			sdata = new Integer[n];
			for(int i=0; i<n; i++)
				sdata[i] = i;
			// Shuffle objects in array using knuth class
			Knuth.shuffle(sdata);
			
			// create deque
			Deque<Integer> mydeque = new Deque<Integer>();
			
			//create array of deque by creating new array
			//and insert of index of deque in new arr
			int ni = mydeque.size();
			int[] myArr;
			myArr = new int[n];
			
			
			// load deque by push to Head
			for(int i=0; i<n; i++)
			mydeque.pushHead(sdata[i]);
			StdOut.println("  ");
			
			
			StdOut.println("Input deque: Print head to tail ");
			 for(Integer s : mydeque)
				 StdOut.print(s + " ");
			 StdOut.println("  ");
 
			
			
			StdOut.println("Push 0 to Tail and Print head to tail ");
			mydeque.pushTail(sdata[0]);
			 for(Integer s : mydeque)
				 StdOut.print(s + " ");
			 StdOut.println("  ");

			 
			mydeque.popHead(); 
			StdOut.println("popHead test and Print head to tail ");
			 for(Integer s : mydeque)
				 StdOut.print(s + " ");
			 StdOut.println("  ");

			 
			
			mydeque.popTail();
			
			StdOut.println("popTail test and Print head to tail ");
			 for(Integer s : mydeque)
				 StdOut.print(s + " ");
			 StdOut.println("  ");

			
			 StdOut.println("The num of items in deque is: ");
			 StdOut.println(mydeque.numItems);
			 
			 StdOut.println("The num of items in array is: ");
			 int[] newArr = new int[mydeque.size()];
			 StdOut.println(newArr.length);
			 StdOut.println("Conversion method prints: ");
			// StdOut.println(mechSort.convert(mydeque));
			 
			
			 StdOut.println("print method from mech sort: ");
			 mechSort.printDeque(mydeque);
			
			 StdOut.println("Shift head in deque");
			 
			 mechSort.shift(mydeque);
			 StdOut.println("Then Print head to tail ");
			 for(Integer s : mydeque)
				 StdOut.print(s + " ");
			 StdOut.println("  ");
			 
			 StdOut.println("Print Sorted Deque");
			 mechSort.sort(mydeque);
			 StdOut.print("Head= "); 
			 for(Integer s : mydeque)
				 StdOut.print(s + " ");
			 StdOut.println("=Tail "); 
			 StdOut.println("  ");
			 
			 /*StdOut.println("\nPrint head to tail again and see if sort works: ");
			 for(Integer s : mydeque)
				 StdOut.print(s + " ");
			 StdOut.println("  ");*/
			 
			 //StdOut.println("Is deque sorted?: ");
			 //StdOut.println(mechSort.isSorted(mydeque));
			 
			 StdOut.println(" ");
			 time = stopwatch.elapsedTime();
			 StdOut.println("Time analysis of algorithm");
			 mechSortAnalysis.dataPrint(time, n);
			 StdOut.println("\nGoodbye Deque");
	    } 
}
