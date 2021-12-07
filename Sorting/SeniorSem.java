package seniorSeminar;

import java.io.File;
import java.util.Scanner;
import java.io.IOException;


public class SeniorSem {

	public static void main(String[] args) throws IOException {
		
		// create scanner scan for user input
		Scanner scan = new Scanner(System.in);
		
		// prompt user to input array size
		System.out.println("Enter dataset size: ");
		int size = scan.nextInt();
		
		//prompt user to input array order
		System.out.println("Enter dataset order: ");
		String order = scan.next();
		
		// user input used to read correct file
		String file = "" + size + order + ".txt";
		scan.close();
		Scanner scanner = new Scanner(new File(file));	
		System.out.println();

		
		
		// read numbers from text file into the array
		// set array size to the user defined input size
		// while loop reads text file and populates array until each integer is read
		int array[] = new int[size];
		int i = 0;
		while(scanner.hasNext()) {
			array[i++] = scanner.nextInt();
		}
		scanner.close();
		
		
		
		

		// create arrays for each algorithm to store results of each test
		double bubbleResult[] = new double[10];
		double selectionResult[] = new double[10];
		double quickResult[] = new double[10];
		double mergeResult[] = new double[10];
		
		double bubbleMem[] = new double[10];
		double selectionMem[] = new double[10];
		double quickMem[] = new double[10];
		double mergeMem[] = new double[10];
		
		
		
		// loop to run tests for however many times you need
		// test each sorting algorithm 10-50 times and store each result
		// take average at the end of all test results for each algorithm
		for (int inc = 0; inc < 10; inc++) {
			
			// create 4 copies of the array to test each array in a separate algorithm
			// purpose: make sure to reset array at each loop so input is equal 
			int array1[] = new int[size];
			int array2[] = new int[size];
			int array3[] = new int[size];
			int array4[] = new int[size];
			for (int j = 0; j < array.length; j++) {
				array1[j] = array[j];
				array2[j] = array[j];
				array3[j] = array[j];
				array4[j] = array[j];
			}
			
			
			
			// test bubble sort
			// inputs array and outputs time taken to compute
			bubbleResult[inc] = bubbleSort(array1);


			
			// test selection sort
			// inputs array and outputs time taken to compute
			selectionResult[inc] = selectionSort(array2);


			
			// test quick sort algorithm 
			// calculate runtime and memory usage outside of algorithm because it is recursive 
			Runtime runtimeQuick = Runtime.getRuntime();
			double mem1Quick = runtimeQuick.totalMemory() - runtimeQuick.freeMemory();
			double time1Quick = System.nanoTime();
			quickSort(array3, 0, array3.length-1);
			double time2Quick = System.nanoTime();
			
			// find total time used by quick sort 
			double totalTimeQuick = time2Quick-time1Quick;
			
			// find total memory used by quick sort 
			double mem2Quick = runtimeQuick.totalMemory() - runtimeQuick.freeMemory();
			
			// print results 
			System.out.println("Quick sort completed in " + totalTimeQuick/1000000 + " milliseconds and used " + (mem2Quick-mem1Quick) + " bytes ");	
			quickResult[inc] = totalTimeQuick/1000000;
			quickMem[inc] = mem2Quick-mem1Quick;
			

			
			// test merge sorting algorithm
			// calculate runtime and memory usage outside of algorithm because it is recursive
			Runtime runtime = Runtime.getRuntime();
			
			// memory available before sort 
			double mem1 = runtime.totalMemory() - runtime.freeMemory();
			double time1 = System.nanoTime();
			mergeSort(array4, 0, array4.length-1);
			double time2 = System.nanoTime();
			
			// total time 
			double totalTime = time2-time1;
			
			// memory available at end of sort 
			double mem2 = runtime.totalMemory() - runtime.freeMemory();
			
			// print results 
			System.out.println("Merge sort completed in " + totalTime/1000000 + " milliseconds and used " + (mem2-mem1) + " bytes ");	
			
			// find total time in milliseconds 
			mergeResult[inc] = totalTime/1000000;
			
			//find memory results 
			mergeMem[inc] = mem2-mem1;
			
			
			System.out.println();
			
			
			
			
			
		}

		System.out.println("\n");		
		
		
		// find average time of results
		double bubbleAvg = average(bubbleResult);
		double selectionAvg = average(selectionResult);
		double quickAvg = average(quickResult);
		double mergeAvg = average(mergeResult);
		
		double mergeMemAvg = average(mergeMem);
		double quickMemAvg = average(quickMem);
		
		System.out.println("bubble sort average: " + bubbleAvg + "ms");
		System.out.println("selection sort average: " + selectionAvg + "ms");
		System.out.println("quick sort average: " + quickAvg + "ms"); 
		System.out.println("merge sort average: " + mergeAvg + "ms and " + mergeMemAvg + " bytes");
		
	
		
		

		
		
		

	}
	
		// method to easily take average time of all tests of the algorithms
		// input result array for each algorithm and output the avg time for the algorithm to complete 
		public static double average(double array[]) {		
			double sum = 0;
			for (int i = 0; i<array.length; i++) {
				sum = sum + array[i];
			}
			double avg = sum/array.length;
			return avg;
			
		}
		
		
		
		
		
		
		// method to print an array.
		// used for testing and debugging 
		public static void printArr(int array[]) {
			for (int i =0; i<array.length; i++) {
				System.out.print(array[i] + " ");
			}
			System.out.println();
		}
	
	
	
	
	
		// bubble sort implementation
		public static double bubbleSort(int array[]) {
			Runtime runtime = Runtime.getRuntime();
			double mem1 = runtime.totalMemory() - runtime.freeMemory();
			double time1 = System.nanoTime();
			
			int n = array.length;
			for (int i = 0; i < n-1; i++) {
				for (int j = 0; j < n-i-1; j++) {
						
					// swap if element on the right is greater
					if (array[j] > array[j+1]) { 
						int temp = array[j];
						array[j] = array[j+1];
						array[j+1] = temp;
					}
				}
			}
			
			// compute time and memory algorithm uses and print
			double time2 = System.nanoTime();
			double totalTime = time2-time1;
			double mem2 = runtime.totalMemory() - runtime.freeMemory();
			System.out.println("Bubble sort completed in " + totalTime/1000000 + " milliseconds and used " + (mem2-mem1) + " bytes");
			
			//return time in milliseconds 
			return totalTime/1000000;

		}
		
		
		
		
		
		// selection sort implementation
		// input unsorted array and output the results of the sort 
		public static double selectionSort(int array[]) {
			Runtime runtime = Runtime.getRuntime();
			double mem1 = runtime.totalMemory() - runtime.freeMemory();
			double time1 = System.nanoTime();
			
			int n = array.length;
			for (int i=0; i<n;i++) {
				int min = array[i];// set to first value of array 
				int minIndex = i;  // set to first unsorted index
				for (int j=i+1; j<n;j++) {
					if (array[j]<min) {
						min = array[j];
						minIndex = j;		
					}
				}
				// swap 1st element of unsorted array with minimum value 
				// only swap if value of minimum < array[i]
				if (min < array[i]) {	
					int temp = array[minIndex];
					array[minIndex]  = array[i];
					array[i] = temp;
				}

					
			}
			
			// compute time and memory used 
			// to output the reuslts 
			double time2 = System.nanoTime();
			double totalTime = time2-time1;
			double mem2 = runtime.totalMemory() - runtime.freeMemory();
			System.out.println("Selection sort completed in " + totalTime/1000000 + " milliseconds and used " + (mem2-mem1) + " bytes");
			// return time in milliseconds 
			return totalTime/1000000;
		}
		
		

		
		// quick-sort implementation
		// input array to be sorted and indexes of stard and end elements 
		public static void quickSort(int arr[], int start, int end) {

			if (start < end) {
				int partition = partition(arr, start, end); // out of bounds
				quickSort (arr, start, partition);
				quickSort (arr, partition+1, end);
				
			}
		}
		

		
		
		// helper method used by quick sort to partition array
		// inputs array and start and end element of array to be partitioned 
		private static int partition(int a[], int start, int end) {
			int pivot = a[start];
			int i = start - 1;
			int j = end + 1;
			
			while (i < j) {
				do {
					j--;
				} while (a[j] > pivot);
				do {
					i++;
				} while (a[i] < pivot);
				
				if (i < j) {
					int temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				} else {
					
				}
				
				
			}
			
			return j;

		}
		

		// merge sort implementation
		// inputs array and index of the start and end element of array
		public static void mergeSort(int arr[], int start, int end) {

			if (start < end) {
				int mid = (start + end)/2;
				mergeSort(arr, start, mid);
				mergeSort(arr, mid+1, end);
				merge(arr, start, mid, end);
			}
			
		}
		
		
		
		
		
		
		// merge Sort calls merge to put the separated lists back together in order
		private static void merge(int arr[], int start, int mid, int end) {
			
			// create 2 arrays (left is left half of original and right is right half)
			int n1 = mid - start + 1;
			int n2 = end - mid;
			int left[] = new int[n1];
			int right[] = new int[n2];
			for (int i = 0; i < n1; i++) {
				left[i] = arr[start+i];
			}
			for (int j = 0; j < n2; j++) {
				right[j] = arr[mid + 1 + j];
			}
			
			// keep track of indexes of the 3 arrays. 
			// i for right, j for left, and k for original
			int i = 0; 
			int j = 0; 
			int k = start;
			
			// compare left and right to place smallest element into original
			while (i < n1 && j < n2) {
				if (left[i] <= right[j]) {
					arr[k] = left[i];
					i++;
				} else {
					arr[k] = right[j];
					j++;
				}
				k++;
			}
			
			// place the rest of the elements of left into original
			while (i < n1) {
				arr[k] = left[i];
				i++;
				k++;
			}
			
			// place the rest of the elements of right into original
			while (j < n2) {
				arr[k] = right[j];
				j++;
				k++;
			}

			
		}

}
