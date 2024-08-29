import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The ArrayUtils class provides utility methods for array manipulation,
 * including merge sort and converting a file of integers to an array.
 */
public class ArrayUtils {

    /**
     * Sorts the given array using merge sort.
     *
     * @param arr the array to be sorted
     */
    public static void mergeSort(int[] arr) {
        mergeSort(arr, arr.length);
    }

    /**
     * Helper method that performs merge sort on a subset of the array.
     *
     * @param a the array to be sorted
     * @param n the number of elements to be sorted
     */
    private static void mergeSort(int[] a, int n) {
        if (n < 2) return;

        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        // Copy elements to left array
        for (int i = 0; i < mid; i++)
            l[i] = a[i];
        // Copy elements to right array
        for (int i = mid; i < n; i++)
            r[i - mid] = a[i];

        mergeSort(l, mid);
        mergeSort(r, n - mid);
        merge(a, l, r, mid, n - mid);
    }

    /**
     * Merges two sorted subarrays into one sorted array.
     *
     * @param a the target array to hold the merged result
     * @param l the left subarray
     * @param r the right subarray
     * @param left the size of the left subarray
     * @param right the size of the right subarray
     */
    private static void merge(int[] a, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;

        // Merge elements from both subarrays into the main array
        while (i < left && j < right) {
            if (l[i] <= r[j])
                a[k++] = l[i++];
            else
                a[k++] = r[j++];
        }
        // Copy any remaining elements from the left subarray
        while (i < left)
            a[k++] = l[i++];
        // Copy any remaining elements from the right subarray
        while (j < right)
            a[k++] = r[j++];
    }

    /**
     * Reads integers from a file and returns them as an array.
     *
     * @param filePath the path to the file containing integers
     * @return an array of integers read from the file, or null if the file is not found or is invalid
     */
    public static int[] fileToArray(String filePath) {
        int count = 0;
        File file = new File(filePath);

        if (!file.exists() || !file.isFile())
            return null;

        // First pass: Count the number of integers in the file
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextInt()) {
                scanner.nextInt();
                count++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Create an array of the appropriate size
        int[] numbersArray = new int[count];
        int index = 0;

        // Second pass: Read the integers into the array
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextInt())
                numbersArray[index++] = scanner.nextInt();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return numbersArray;
    }
}