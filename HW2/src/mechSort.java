//Student: Michael Weatherburn
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Knuth;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;

public class mechSort {
//  Mechanical sort a deque of items using only the following operations. 
	// 1. Peek or take two sheets off the head of the deque. 
	// 2. Exchange the order of the two sheets at the head of the deque. 
	// 3. Move the head sheet to the tail of the deque. 
	

	private mechSort() {}
	//method to convert copy deque to arr
	
	//printdeque
	public static void printDeque(Deque<Integer> deque) {// print the deque
	       int x;
	       StdOut.print("Head= ");
	       for(int j = 0; j < deque.size(); j++) {
	           x = (int)deque.peek(j);
	           StdOut.print(x + " ");
	       }
	       StdOut.println("=Tail "); 
	       StdOut.println();
	   }
	
	public static void sort(Deque<Integer>deque) {
		//sort the deque so head is smallest item
		//create new arraylist and pop all elements from deque to arraylist
		ArrayList<Integer> dq = new ArrayList<Integer>();
		for(Integer s: deque) {
			dq.add(deque.popHead());
		}
		//use sort method to sort arraylust
			Collections.sort(dq);
			//set size just in case
		int arrSize = dq.size();
		//for all elements in arraylist, copy them back to deque
		for(Integer e: dq) {
			deque.pushTail(e);
		}
		//print all elements in deque
		/*
		for(Integer s: deque) {
			StdOut.print(s + " ");
		}
		*/
		
		
		
	}
	public static void exch(Deque<Integer>deque) {
		//Exchange order of sheets at head
		//declare temp array to hold values of head and head.next
		//declare temp var
		int[] temp = new int[2];
		//set first index in array to head value and second index in array to head.next value
		temp[0] = deque.peek(0);
		temp[1] = deque.peek(1);
		
		//then push the index holding the old head first then push the old second after so it becomes the new head with pushhead method
		deque.pushHead(temp[0]);
		deque.pushHead(temp[1]);
	}
	static void shift(Deque<Integer>deque) {
		//shift item from head to tail
		//declare temp and x 
		int temp;
		int x;
		//base case
		if(deque.peek(0) == null) {return;}
		//hold value at head of deque in temp
		temp = deque.peek(0);
		//then remove the head
		deque.popHead();
		//then push temp value to deque which is holding the value from the previous head
		deque.pushTail(temp);
	}
	public static boolean isSorted(Deque<Integer>deque) {
		//store size of deque in temp int
		int size = deque.size();
		//i will be used to iterate and set the boolean var as false un
		int i = 0;
		boolean pass = false;
		while(i<size-1) {
			//if value at deque index is less than next index value in deque, change pass to true 
			//and return until condition is false then return false
			if(deque.peek(i) < deque.peek(i+1)) {
				pass = true;
			}else {
				return false;
			}
		}i++;
		return pass;
	}
	private void mergesort(int low, int high) {
		//didnt use
		//check if low is smaller than high if not than array is sorted
		if(low < high) {
			//get index of element in the middle
			int middle = low + (high - low) / 2;
			//sort the left side of the array
			mergesort(low, middle);
			//sort the right side of the array
			mergesort(middle+1, high);
			//combine them both
			merge(low,middle,high);
		}
	}
	private void merge(int low, int middle, int high) {
		//didnt use
		//copy both parts into helper array
		int[] numbers = new int[high];
		int[] helper = new int[high];
		
		
		for (int i = low; i <= high; i++) {
			helper[i] = numbers[i];
		}
	}
}
