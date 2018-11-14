import java.util.Random;
import java.util.Scanner;

public class MSS {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the size of the array: ");
		Random rand = new Random();
		int n = in.nextInt();
		int[] array = new int[n];
		for (int i = 0; i < n; i++) { // Initializing random numbers into array
			int randomInt = rand.nextInt(200) - 100;
			array[i] = randomInt;
//			System.out.println(array[i]);
		}

		//BIG O(n)
		long startTime = System.nanoTime();
		MSS(array);
		long endTime = System.nanoTime() - startTime;
		System.out.println("O(n) time: " + endTime + " nanoseconds");
		// End
		
		//Big O(nlogn)
		long startTime2 = System.nanoTime();
		int max_sum = maxSum(array, 0, n - 1);
		long endTime2 = System.nanoTime() - startTime2;
		System.out.println("------------------------------------");
		System.out.println("MSS O(nlogn): " + max_sum);
		System.out.println("O(nlogn) time: " + endTime2 + " nanoseconds");
		// End

	}

	public static void MSS(int[] array) {
		int temp = 0;
		int MSS = 0;
		for (int i = 0; i < array.length; i++) {
			temp += array[i];
			//Reset to 0 if temp goes negative
			if (temp < 0) {
				temp = 0;
			}
			// MSS = temp if temp is temp is bigger than mss
			if (temp > MSS) {
				MSS = temp;
			}
		}
		System.out.println("O(N)");
		System.out.println("Temp: " + temp);
		System.out.println("MSS: " + MSS);
	}
	
	//N LOG N, divide and conquer approach
	public static int findMSS(int a[], int low, int mid, int high)
	{
		//  Find MSS of left side
		int temp = 0;
		int leftMSS = 0;
		for (int i = mid; i >= low; i--)
		{
			temp += a[i];
			if (temp < 0) {
				temp = 0;
			}
			if (temp > leftMSS)
				leftMSS = temp;
		}

		// FIND MSS OF RIGHT SIDE
		temp = 0;
		int rightMSS = 0;
		for (int i = mid + 1; i <= high; i++)
		{
			temp = temp + a[i];
			if (temp < 0) {
				temp = 0;
			}
			if (temp > rightMSS)
				rightMSS = temp;
		}

		return leftMSS + rightMSS;
	}

	static int maxSum(int a[], int low, int high)
	{
		if (low == high) {
			return a[low];
		}
		//Find mid
		int mid = (low + high)/2; 
		//Return of nlogn
		return Math.max(Math.max(maxSum(a, low, mid),maxSum(a, mid+1, high)), findMSS(a, low, mid, high));
	}
}
