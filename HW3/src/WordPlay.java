
/*
 * Student Name: Michael Weatherburn
 *
 * WordPlay
 * 
 * This client examines the performance of the following symbol table (ST) implementations:
 * Sequential search, binary search, BST, and red black BST from the algs4 library
 * 
 * One ST implementation is chosen via the "activeST" variable.
 * 
 * This client 
 * -- reads a text file 
 * -- creates a symbol table (ST) with (word, word frequency) as (key, value), called "WF" ST.
 * -- reports the time it took to build this ST 
 * -- reports statistics concerning this WF ST
 * -- reports the number of unique words in the input file that are greater than "minlen"
 * -- reports the minimum and maximum words based on lexicographical ordering and the associated frequencies
 * 
 * Next this client creates a second ST with (key, value) = (frequency, word) called "FW"
 * -- using this FW ST, the max and min frequencies with associated words are reported
 * 
 * Reporting is made with prints to the console. 
 * 
 * (The program actually processes strings; it does not extract words necessarily; 
 * Example "file." is a "word".)
 *
 *  
 * 
 */
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.RedBlackBST;
import edu.princeton.cs.algs4.ST;

import java.util.HashSet;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.Bag;

public class WordPlay {

	// Do not instantiate.
	private WordPlay() {
	}

	public static void main(String[] args) {

		StdOut.println("Hello WordPlay!");

		/*
		 * ************************************************************************
		 * Choose one input file by removing the comment characters
		 * *************************************************************************
		 */
		//In inputFile = new In("quickFox.txt");
		// In inputFile = new In("mobydick.txt");
		// In inputFile = new In("aesop.txt");
		 In inputFile = new In("dickens.txt");

		// create a symbol table with key as string and value as integer
		// (word, length) "WF" ST

		ST<String, Integer> WFST = new ST<String, Integer>(); // mweath1

		// #1
		SequentialSearchST<String, Integer> WFssST = new SequentialSearchST<String, Integer>();
		// #2
		BinarySearchST<String, Integer> WFbsST = new BinarySearchST<String, Integer>();
		// #3
		BST<String, Integer> WFbstST = new BST<String, Integer>();
		// #4
		RedBlackBST<String, Integer> WFrbST = new RedBlackBST<String, Integer>();

		// *********************************************
		// Choose one of the ST implementations to use
		// Numbers indicated in ST declaration
		// *********************************************
		int activeST = 4;

		StdOut.println("activeST = " + activeST);
		if (activeST < 1 || activeST > 4) {
			StdOut.println("ERROR: activeST must be set to 1 - 4.  Setting to 1 ");
			activeST = 1;
		}

		switch (activeST) {
		case 1:
			StdOut.println("ST Implemenation: Sequential Search");
			break;
		case 2:
			StdOut.println("ST Implemenation: Binary Search");
			break;
		case 3:
			StdOut.println("ST Implemenation: BST");
			break;
		case 4:
			StdOut.println("ST Implemenation: Red Black BST");
			break;
		}

		// set the min length of words to enter into the symbol table
		int minlen = 5;
		StdOut.println("Minimum length of word   minlen = " + minlen);

		// number of words read from file
		// int your variable here
		double readFromFile = 0;
		// number of words >= minlen
				// int your variable here
				double noOfWords = 0;//num of words greater than min length
		// Number of UNIQUE words >= minlen
				int nUniqueW = 0;
		
		

		StdOut.println("\nCreate a ST of (key,value) = (word, frequency) where length >=  " + minlen);

		StdOut.println("Set timer to measure time to build ST");

		// start timer to measure time to build (word, freq) ST
		double time;
		Stopwatch timer = new Stopwatch();
		time = timer.elapsedTime();

		// Check Number of Unique Words
		HashSet<String> set = new HashSet<String>();

		// Build the WF ST (You will need a switch statement )
		switch (activeST) {

		case 1: // ST Implementation: Sequential Search
				// Read string from file and update key and frequency in ST
			StdOut.println("\nST Implemenation: Sequential Search");

			while (!inputFile.isEmpty()) { // While inputFile is not empty
				String word = inputFile.readString();
				readFromFile++;
				
				// Set will only contain unique words greater than minlen
				//So it checks if unique word in set and adds if not present
				
				if (word.length() < minlen) {
					continue;
				}
				if (!set.contains(word)) {
					set.add(word);
					nUniqueW++;
				} //adding keys to set 
				
				if (!WFssST.contains(word)) {
					WFssST.put(word, 1);
					noOfWords++;
				} else
					WFssST.put(word, WFssST.get(word) + 1);
				   
			}
			// Print a String with max frequency
//			String max = "";
//			WFssST.put(max, 0);
//			for (String word : WFssST.keys())
//				if (WFssST.get(word) > WFssST.get(max)) {
//					max = word;
//				}
//			WFssST.delete("");
//			keysetSize = set.size() - 1;// this line gets the number of unique words within hashSet
//
//			StdOut.println("\nThe max frequency of a word that's greater than min length(Word,Frequency): " + max
//					+ " | " + WFssST.get(max));
			break;

		case 2:
			StdOut.println("\nST Implemenation: Binary Search");
			while (!inputFile.isEmpty()) { // While inputFile is not empty
				String word = inputFile.readString();
				readFromFile++;
				// checks if word is blank
				// if(word=="") {continue;}
				// checks unique word in set and adds if not present
				if (word.length() < minlen) {
					continue;
				}
				if (!set.contains(word)) {
					set.add(word);
					nUniqueW++;
				} // unique keys

				
				if (!WFbsST.contains(word)) {
					WFbsST.put(word, 1);
					noOfWords++;
				} else
					WFbsST.put(word, WFbsST.get(word) + 1);

			}
			// Print a String with max frequency
//			String max2 = "";
//			WFbsST.put(max2, 0);
//			for (String word : WFbsST.keys())
//				if (WFbsST.get(word) > WFbsST.get(max2)) {
//					max2 = word;
//				}
//			WFbsST.delete("");
			//keysetSize = set.size() - 1;// this line gets the number of unique words within hashSet

//			StdOut.println("\nThe max frequency of a word that's greater than min length(Word,Frequency): " + max2
//					+ " | " + WFbsST.get(max2));
			
			break;
		case 3:
			StdOut.println("\nST Implemenation: BST");
			while (!inputFile.isEmpty()) { // While inputFile is not empty
				String word = inputFile.readString();
				readFromFile++;
				
				// Set will only contain unique words greater than minlen
				//So it checks if unique word in set and adds if not present
				
				if (word.length() < minlen) {
					continue;
				}
				if (!set.contains(word)) {
					set.add(word);
					nUniqueW++;
				} //adding keys to set 
				
				if (!WFbstST.contains(word)) {
					WFbstST.put(word, 1);
					noOfWords++;
				} else
					WFbstST.put(word, WFbstST.get(word) + 1);
				   
			}
			break;
		case 4:
			StdOut.println("\nST Implemenation: Red Black BST");
			while (!inputFile.isEmpty()) { // While inputFile is not empty
				String word = inputFile.readString();
				readFromFile++;
				
				// Set will only contain unique words greater than minlen
				//So it checks if unique word in set and adds if not present
				
				if (word.length() < minlen) {
					continue;
				}
				if (!set.contains(word)) {
					set.add(word);
					nUniqueW++;
				} //adding keys to set 
				
				if (!WFrbST.contains(word)) {
					WFrbST.put(word, 1);
					noOfWords++;
				} else
					WFrbST.put(word, WFrbST.get(word) + 1);
				   
			}
			break;
		}

		// stop timer
		time = timer.elapsedTime();

		
		
		
		
		
		
		
		// report information

		// Report on (word, frequency) ST build
		// create variables for this section and then uncomment

		StdOut.println("Number of words read from file:  " + readFromFile);
		StdOut.println("Number of words in file >= " + minlen + " = " + noOfWords);

		StdOut.printf("Percentage of words >= min length:  %3.1f", (noOfWords / readFromFile) * 100);
		StdOut.println("% ");

		StdOut.println("Number of unique words >= min length(#keys in ST):  " + noOfWords);

		StdOut.printf("Percentage of unique words:  %3.1f", (nUniqueW / noOfWords) * 100);
		StdOut.println("% ");

		StdOut.println("Build ST Time = " + time * 1000 + " milliseconds");
		
		
		
		
		

		// Print up to the first 10 items in the ST: word and frequency
		// Add some text to the output to communicate what is being printed
		// Use the for each statement
		StdOut.println("The WFbsST (Words over minlength:Frequency): ");
		int i = 1;
		for (String key : WFrbST.keys()) { // have unusued symbol table to utilize keys with from above
			StdOut.print("| " + key + ": " + WFrbST.get(key) + " |");
			if (i == 10) {
				break;
			} // only print up to 10 words==
			i++;
		}

		
		
		
		
		/*
		 * Print min and max word and its associated frequency from the WF ST Only do
		 * this if using a class with ordered operations
		 */

		/* ****************************** */
		/*
		 * PART 2: Create a (frequency, word) ST using the words and frequencies in the
		 * WF ST Call it "FW" ST Just as above, there will be 4 ST structures
		 */
		/****************************** */

		SequentialSearchST<Integer, String> FWST = new SequentialSearchST<Integer, String>(); // (Frequency, Word) ST
		BinarySearchST<Integer, String> FWST2 = new BinarySearchST<Integer, String>(); // (Frequency, Word) ST
		BST<Integer, String> FWST3 = new BST<Integer, String>();
		RedBlackBST<Integer, String> FWST4 = new RedBlackBST<Integer, String>();

		StdOut.println("\n\nCreate (Frequency, Word) ST: .... \nThe output of the FW Symbol Table is: "); // REMEMBER TO
																											// UNCOMMENT

		// Build the FW ST

		// Print up to the first 10 items in the ST: frequency and word
		// Add some text to the output to communicate what is being printed
		int j;// incrementor for up to 10 items
		switch (activeST) {
		case 1:
			// j=1;
			for (String key : WFssST.keys()) {
				// StdOut.print("| "+WFssST.get(key) + ": "+ key+" |");
				FWST.put(WFssST.get(key), key);
				// if(j==10) {break;}
				// j++;//only prints up to 10 words
//	    				if(!FWST.get(null)) {FWST.put(WFssST.get(key), key);}
//	        			else WFssST.put(key, WFbsST.get(key)+1);

			}
			break;
		case 2:
			for (String key : WFbsST.keys()) {
				// StdOut.print("| "+WFssST.get(key) + ": "+ key+" |");
				FWST2.put(WFbsST.get(key), key);
			}
			break;
		case 3:
			for (String key : WFbstST.keys()) {
			// StdOut.print("| "+WFssST.get(key) + ": "+ key+" |");
			FWST3.put(WFbstST.get(key), key);
		}
			break;
		case 4:
			for (String key : WFrbST.keys()) {
				// StdOut.print("| "+WFssST.get(key) + ": "+ key+" |");
				FWST4.put(WFrbST.get(key), key);
			}
			break;
		}
		// Print up to the first 10 items in the ST: frequency and word
		// Add some text to the output to communicate what is being printed

		// Use the for each statement
		j = 1;
		for (Integer a : FWST4.keys()) {
			StdOut.print("| " + a + ": " + FWST4.get(a) + " |");
			if (j == 10) {
				break;
			}
			j++;// only prints up to 10 words
		}

		// Print min and max frequencies with associated word
		// Only do for ST classes with ordered operations
		//StdOut.println("The min and max frequencies with associated word")

	} // end main

} // end class
