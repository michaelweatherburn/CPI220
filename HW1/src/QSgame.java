
/*
* QS Game
* This is a client for the QS class (short for QueueStack).
* QSGame is a die rolling game of chance. 
* We start with seven circles: * * * o * * *
* where the center "o" circle is the prize and it is surrounded by three circles on either side.
* We have "maxRolls" rolls of a 5-sided die to remove the prize circle as follows.
* The seven circles are a graphical representation of seven items on a QS. 
* The items on the QS have values 1 1 1 0 2 2 2.
* The values serve as identifiers for identifying the prize circle (value=0) 
* and correct coloring (pointers into a color array).
* 
* Game play:
* The die is rolled. A random number generator determines the face for the roll. 
* The following action/inaction occures
* die = 0 no action / lost roll
*       1 enqueue
*       2 dequeue
*       3 push
*       4 pop
* If the prize circle is removed (by dequeue or pop) before the maximum number of rolls are made, then you win.
* Statistics are return for the number of times each face appears and the corresponding percentage of all rolls.
* 
* >>>>>>> Homework Specifications <<<<<<<<<<
* Implement the functionality described above in the code provided below.
* Some starter code has been provided, particularly code to help you draw the circles.
* Please read the comments in the starter for guidence in building the game.
* When a circle is removed (dequeue or pop), it should be animated (as done in QS's main())
*
* 
* If you want to play around, the game can be easily modified as follows.  
* -- The number initial circles may be increased or decreased
* -- The maximum number of rolls may be modified
* -- Change the drawing speed/redraw times
* 
* Do you have a nifty idea to improve this game? Let me know!
* 
* This document was prepared by Dianne Hansford
* This document was finished by Michael Weatherburn
*/
import java.util.Random;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QSgame {
	

	public static void main(String[] args) {

		
			
        //>>>>>>>>> GAME DESIGN CHOICE:
		// specify number of circles to generate on each side of center (prize) circle
		// Do not allow < 1 circle
		int numCircles = 3;
		if(numCircles < 1) numCircles = 1;
		
		// Save the total number of circles
		int numItems = 1 + (numCircles * 2);
		
		//>>>>>>>>> GAME DESIGN CHOICE:
		// max tries/rolls to get prize circle
        // what is a good number here?  Make it dependent on the number of rolls and faces of die?
		// DCH: numItems*4 is my pick
        int maxRolls = numItems * 4;
         
		// create 3 colors (prize/center, left, right, resp.)
        // You can google "color picker" to find RGB choices that you like and change the colors below
		int numColors = 3;
        int [][] baseColors;
        baseColors = new int [numColors][3];
        
        // center "prize" circle color
        baseColors[0][0] = 235;
        baseColors[0][1] = 177;
        baseColors[0][2] = 52; 
        
        //side circles
        baseColors[1][0] = 65;
        baseColors[1][1] = 133;
        baseColors[1][2] = 30;
        
        // other side circles
        baseColors[2][0] = 30;
        baseColors[2][1] = 99;
        baseColors[2][2] = 133;
        
        // specify the radius of each circle to draw -- used by draw()
		double minRadius = 0.001;
		double radius = 0.25/(numCircles*2);
		if(radius < minRadius) radius = minRadius;
		//StdOut.println("\n radius =  " + radius);
		
		// create variables for long pause and short pause for animation
		// short for animation, long for just looking at it
		int shortPause = 100;
		int longPause = 400;
				
		// Create an array to keep track of the number of times each die value appears (0,1,2,3,4)
		// Initialize to zero.
		 
		        
		// SET UP QS for the game. Load the "prize" circle and the others to left and right
        
		//1.  enqueue prize circle
		QS <Integer> qs = new QS<Integer>();
		
		
		//2. Enqueue value 0 to give it baseColor[0]
		qs.enqueue(0);
		qs.draw(-1, longPause, 3, baseColors, radius);
		
		//3. enqueue and push the rest of the circles
		//3. Set value to match baseColor[2] and baseColor[1]
		/*method: create temp int = numCircles
		 * 	    while temp > 0
		 * 		*/
		qs.enqueue(2);
		qs.enqueue(2);
		qs.enqueue(2);
		
		qs.push(1);
		qs.push(1);
		qs.push(1);
		
		StdOut.println("\n Print qs using foreach");
        for(Integer ival : qs)
        	StdOut.print(" "+ ival + " ");
            
		 
		
		// (debug) print the contents of the qs
        
		
        // IF want extra prints during game, make true ep ="extra prints"
		// This is optional if you want to use it
        boolean ep = false;
        
        // -----------------------------------------------------------------
        // READY FOR GAME
         int maxR = maxRolls; //hard coded maxRolls = maxRollss from above
        StdOut.println("\n \n Let's Play! You get " + maxR + " rolls");
        
        // draw all  
         
        // initialize what you need here
        //values to hold maxRoll, numberOfTimes each option appears i.e. num0 for player choosing option 0 and etc.
        //and value to keep track if game is won or lost
        boolean gameDone = false; //checks if game is done or not
        boolean win = false;//checks if won or not
        int checkZero = 1;//if 1, means zero is not in list, if 0, it means 0 is in list
        float num0 =0, num1=0, num2=0, num3=0, num4=0;
        int count = 0;//keeps track of how many rolls we make so we can do calculations with later
        
        //test roll
        Random rand = new Random();
        int r = rand.nextInt(5); //hardcoded random player input for now -- this random player chooses number 0-5 until the game is done
        // Continue rolling until maximum number of rolls reached or a win occurs
        /*
         *
        1 enqueue
*       2 dequeue
*       3 push
*       4 pop
*       */
        while(gameDone==false) {
        	if(maxR>0) //if player has rolls
        	{
	        		for(Integer l:qs) //checks if there is a 0 in list
	        		{
	        			if(l==0)checkZero = 0;
	        			
	        			
	        			
	        			
	        		}
	        		
	        
        	}
        	
        	if(checkZero == 0) //after checking list, if 0 exists, run switch
    		{
        			switch(r) 
        				{
        			case 0: count++; maxR--; StdOut.println("\n oops you wasted a turn! \n maxR = " + maxR--);
        			case 1: count++; qs.enqueue(2); num1++; StdOut.println("\n oops you enqueued 2! \n maxR = " + maxR--); StdOut.println(qs); qs.draw(-1, longPause, 3, baseColors, radius);
        			case 2: count++; qs.draw(0, shortPause, 3, baseColors, radius);int x = qs.dequeue(); qs.draw(-1, longPause, 3, baseColors, radius);  num2++; StdOut.println("\n oops you dequeued " + x + "\n maxR = " + maxR--); StdOut.println(qs);
        			case 3: count++; qs.push(1);	num3++; StdOut.println("\n oops you pushed 1! \n maxR = " + maxR--); StdOut.println(qs);  qs.draw(-1, longPause, 3, baseColors, radius);
        			case 4: count++; qs.draw(qs.size()-1, shortPause, 3, baseColors, radius); int v = qs.pop(); qs.draw(-1, longPause, 3, baseColors, radius);	num4++; StdOut.println("\n oops you popped " + v + "\n maxR = "+maxR--); StdOut.println(qs);
        			//enqueue and pop on the right
        	        //dequeue and push on the left
        				}
        	}
        	if(checkZero == 1 && maxR>0) { //if you still have rolls but the there are no zeros in list
        		gameDone = true;
        		win = true;
        		break;
        		
        	}
        	if(checkZero == 0 && maxR<=0) //if you have no rolls and there is a zero in list
        	{
        		gameDone = true;
        		win = false;
        		break;
        	}
        	
        	for(Integer ca : qs) { //check list after switch case so we dont continously run while loop
        		if(ca != 0) checkZero = 1;
        		else checkZero = 0;
        	}
        	
        		
        		    			
        		    			
        	
    			
        	
		// Keep statistics as the game continues
        
         
        // Let user know if won or lost
		
		
		
		
        
        // Output some statistics
        // Number of rolls, number of each face, and percentage to times this face appeared
        // Use formatted integer and double (%3d and %.1f). A % sign is %% in "printf"
        
    	 
    	
    	
        								
        
        			}//closes while loop
        float n0,n1,n2,n3,n4;
        n0 = num0/(num0+num1+num2+num3+num4);
        n1 = num1/(num0+num1+num2+num3+num4);
        n2 = num2/(num0+num1+num2+num3+num4);
        n3 = num3/(num0+num1+num2+num3+num4);
        n4 = num4/(num0+num1+num2+num3+num4);
        if(win==true && gameDone == true) 
        {
        	StdOut.println("\n You won! \n These are your stats:");
        	
        	StdOut.println("\n Number Of times you missed: \n " + num0 + "/" + count);
        	StdOut.print(num0 + " \nPercentage: ");
			StdOut.println(String.format("%.2f%%%n",(num0/count)*100));
			StdOut.println("\n Number Of times you enqueued: ");
			StdOut.print(num1 + "/" + count +" \nPercentage: ");
			StdOut.println(String.format("%.2f%%%n",(num1/count)*100));
			StdOut.println("\n Number Of times you dequeued: ");
			StdOut.print(num2 + "/" + count + " \nPercentage: ");
			StdOut.println(String.format("%.2f%%%n",(num2/count)*100));
			StdOut.println("\n Number of times you pushed: ");
			StdOut.print(num3 + "/" + count +" \nPercentage: ");
			StdOut.println(String.format("%.2f%%%n",(num3/count)*100));
			StdOut.println("\n Number of times you popped: ");
			StdOut.print(num4 + "/" + count +" \nPercentage: ");
			StdOut.println(String.format("%.2f%%%n",(num4/count)*100));
        }
        else 
        {
        	StdOut.println("\n You Lose! \n Here are your stats: ");
        	StdOut.println("\n Number Of times you missed: ");
        	StdOut.print(num0 + " \nPercentage: ");
        	StdOut.println(String.format("%.2f%%%n",(num0/count)*100));
        	StdOut.println("\n Number Of times you enqueued: ");
        	StdOut.print(num1 + "/" + count +" \nPercentage: ");
        	StdOut.println(String.format("%.2f%%%n",(num1/count)*100));
			StdOut.println("\n Number Of times you dequeued: ");
			StdOut.print(num2 + "/" + count + " \nPercentage: ");
			StdOut.println(String.format("%.2f%%%n",(num2/count)*100));
			StdOut.println("\n Number of times you pushed: ");
			StdOut.print(num3 + "/" + count +" \nPercentage: ");
			StdOut.println(String.format("%.2f%%%n",(num3/count)*100));
			StdOut.println("\n Number of times you popped: ");
			StdOut.print(num4 + "/" + count +" \nPercentage: ");
			StdOut.println(String.format("%.2f%%%n",(num4/count)*100));
        }
        System.exit(0);}//closes main method

}
