import java.util.HashMap;

public class main {
	//practice recursion
	public static int factorial(int n) {

        // Base case: n = 0 or 1
        if (n <= 1) {
            return 1;
        }

        // Recursive case: n! = n * (n - 1)!
        return n * factorial(n-1);
    }
public static int[] solution(int target, int[] arr) {
	HashMap <Integer, Integer> map = new HashMap<Integer,Integer>();
	int res[]= {0,0};
	for(int i = 0; i<arr.length; i++) {
		int expected = target - arr[i];
		if(map.containsKey(expected)) 
		{
			res[0] = map.get(expected);
			res[1] = i;
		}
		map.put(arr[i], i);
	}
	return res;
}
	public static void main(String[] args) {
		int[] arr = {2,4,7,1};
		
		StdOut.println("Return indices that add up to target number, else return");
		
		
		
		StdOut.println(solution(9,arr));
		 
		

	}

}
