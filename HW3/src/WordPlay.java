/*
 * Student Name:
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
import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.BinarySearchST;
import edu.princeton.cs.algs4.SequentialSearchST;
import edu.princeton.cs.algs4.Stopwatch;
import edu.princeton.cs.algs4.Bag;

public class WordPlay {

	// Do not instantiate.
    private WordPlay() { }
    
	public static void main(String[] args) {
		

		StdOut.println("Hello WordPlay!");
		
		/*
    	 * ************************************************************************
    	 * Choose one input file by removing the comment characters
    	 * *************************************************************************
    	 */
    	In inputFile = new In("quickFox.txt"); 
    	//In inputFile = new In("mobydick.txt");
		//In inputFile = new In("aesop.txt");
    	//In inputFile = new In("dickens.txt"); 
		 
	
      
    	// create a symbol table with key as string and value as integer 
		// (word, length)  "WF" ST
		 
		//#1
		SequentialSearchST<String, Integer> WFssST = new SequentialSearchST<String, Integer>();
		// #2
		BinarySearchST<String, Integer> WFbsST = new BinarySearchST<String, Integer>();
		//#3
	    BST<String, Integer> WFbstST = new BST<String, Integer>();
	    //#4
    	RedBlackBST<String, Integer> WFrbST = new RedBlackBST<String, Integer>();
		
    	
    	// *********************************************
    	// Choose one of the ST implementations to use
    	// Numbers indicated in ST declaration
    	// *********************************************
		int activeST = 1;
		
		StdOut.println("activeST = " + activeST);
	    if(activeST < 1 || activeST > 4) {
	    	StdOut.println("ERROR: activeST must be set to 1 - 4.  Setting to 1 ");
	    	activeST = 1;
	    }
	    
	    switch(activeST) {
		    case 1: StdOut.println("ST Implemenation: Sequential Search");
		    		break;
		    case 2: StdOut.println("ST Implemenation: Binary Search");
					break;
		    case 3: StdOut.println("ST Implemenation: BST");
					break;
		    case 4: StdOut.println("ST Implemenation: Red Black BST");
					break;
	    }
	    	
	    		
		
    	 // set the min length of words to enter into the symbol table
        int minlen = 5;
        StdOut.println("Minimum length of word   minlen = " + minlen);
        
        // number of words read from file
        //int your variable here 
        // number of words >= minlen
        //int your variable here 
        
        StdOut.println("Create a ST of (key,value) = (word, frequency) where length >=  " + minlen);
		  
        StdOut.println("Set timer to measure time to build ST");
        
	    // start timer to measure time to build (word, freq) ST
	     	 
	     	 
	    // Build the WF ST (You will need a switch statement )
	     	   
	        
	    //stop timer  
	        
	        
	    // report information
	        
	     
	        
	        // Report on (word, frequency) ST build
			// create variables for this section and then uncomment
			/*
	        StdOut.println("Number of words read from file:  " + xxxxx);
	        StdOut.println("Number of words in file >= " + minlen + " = " + xx);
	         
	        StdOut.printf("Percentage of words >= min length:  %3.1f",xx);
	        StdOut.println("% ");
	           
	        StdOut.println("Number of unique words (#keys in ST):  " + xx); 
	         
	        StdOut.printf("Percentage of unique words:  %3.1f", xx); 
	        StdOut.println("% ");
	        
	        StdOut.println("Build ST Time = " + xx);
			*/
	         
	        
	        // Print up to the first 10 items in the ST: word and frequency
			// Add some text to the output to communicate what is being printed
	        // Use the for each statement
	         
	        
	        
	        /* 
	         * Print min and max word and its associated frequency from the WF ST
	         * Only do this if using a class with ordered operations
	         */
	        
	        
	        
	        
	        /* ****************************** */
	        /* PART 2: Create a (frequency, word) ST 
			 * using the words and frequencies in the WF ST 
	         * Call it "FW" ST
			 * Just as above, there will be 4 ST structures
			 */
	        /****************************** */
				 
	        StdOut.println("Create (Frequency, Word) ST ");
	         
	    	
	    	// Build the FW ST
	    	
	    	 
	    	
	    	// Print up to the first 10 items in the ST: frequency and word
			// Add some text to the output to communicate what is being printed
	        // Use the for each statement
			
	    	 
	        
	        // Print min and max frequencies with associated word
	        // Only do for ST classes with ordered operations
	        
	         
	        
	
	} // end main

} // end class
