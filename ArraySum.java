/*
 * Siraj Sayeed
 * Recursion Lab
 * CMSC 204
 * Recursive function to get total sum of an array
 */
public class ArraySum {
	
	public int sumOfArray(Integer[] a, int index) {
		
		if(index == 0)
			return a[index];
		
		return a[index] + sumOfArray(a, index-1);
		
	}

}
