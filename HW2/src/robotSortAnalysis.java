//Student: Michael Weatherburn
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Knuth;
import edu.princeton.cs.algs4.Stopwatch;
import java.text.DecimalFormat;
import edu.princeton.cs.algs4.*;
import java.util.*;

public class robotSortAnalysis {
	//takes input of time and num of elements then does calculations to determine time complexity
	 public static void dataPrint(double time, int num) {
		 DecimalFormat form = new DecimalFormat("#.000");
		 StdOut.println("\n\nDoubling Ratio Table for " + num + " items");
		 StdOut.println(num + "     |    " + time + "  | " + (2*time/time)+ "  | " + form.format(Math.log(2*time/time)));
		 StdOut.println("N items |  time  |  ratio  |  lg(ratio)");
	 }
}
