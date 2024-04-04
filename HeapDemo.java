import java.util.Scanner;

public class HeapDemo {

    public static void main(String[] args) {
        // Create a Scanner object for input
        Scanner scanner = new Scanner(System.in);

        // Create an array for demonstration
        int[] arr = { 4, 10, 3, 5, 1, 8, 6 };

        // Demonstrate heap operations
        System.out.println("\nOriginal Array:");
        printArray(arr);
        printHeapArray(arr);
        waitForInput(scanner);

        // Perform insertion and heapify-up
        insertElement(arr, 7);
        System.out.println("\nArray after inserting 7:");
        printArray(arr);
        printHeapArray(arr);
        waitForInput(scanner);

        // Perform deletion and heapify-down
        deleteElement(arr, 0);
        System.out.println("\nArray after deleting root:");
        printArray(arr);
        printHeapArray(arr);
        waitForInput(scanner);

        // Demonstrate heap sort
        System.out.println("\nHeap Sort:");
        heapSort(arr);
        printArray(arr);
        waitForInput(scanner);

        // Close the scanner
        scanner.close();
    }

    // Method to print array elements
    static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    // Method to print array elements in a heap-like format
    static void printHeapArray(int[] arr) {
        int height = (int) (Math.log(arr.length) / Math.log(2)) + 1;
        int index = 0;
        System.out.println("\nHeap Structure:");
        for (int level = 0; level < height; level++) {
            int itemsInLevel = (int) Math.pow(2, level);
            for (int i = 0; i < itemsInLevel && index < arr.length; i++) {
                System.out.print(arr[index++] + "\t"); // Print the element
            }
            System.out.println(); // Move to the next line
        }
    }

    // Method to perform heapify-up operation for insertion
    static void heapifyUp(int[] heap, int i) {
        while (i > 0 && heap[(i - 1) / 2] > heap[i]) {
            int parent = (i - 1) / 2;
            int temp = heap[parent];
            heap[parent] = heap[i];
            heap[i] = temp;
            i = parent;
        }
    }

    // Method to perform heapify-down operation for deletion
    static void heapifyDown(int[] heap, int i, int n) {
        while (2 * i + 1 < n) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = left;
            if (right < n && heap[right] < heap[left]) {
                smallest = right;
            }
            if (heap[i] <= heap[smallest]) {
                break;
            }
            int temp = heap[i];
            heap[i] = heap[smallest];
            heap[smallest] = temp;
            i = smallest;
        }
    }

    // Method to insert an element into the heap
    static void insertElement(int[] heap, int value) {
        heap[heap.length - 1] = value; // Insert at the end of array
        heapifyUp(heap, heap.length - 1);
    }

    // Method to delete an element from the heap
    static void deleteElement(int[] heap, int index) {
        heap[index] = heap[heap.length - 1]; // Move last element to index position
        heapifyDown(heap, index, heap.length - 1);
    }

    // Method to perform heap sort
    static void heapSort(int[] arr) {
        int n = arr.length;

        // Build min-heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, i, n);
        }

        // Heap sort
        for (int i = n - 1; i > 0; i--) {
            // Swap root (min element) with last element
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Heapify down the root
            heapifyDown(arr, 0, i);
        }
    }

    // Method to wait for user input
    static void waitForInput(Scanner scanner) {
        System.out.println("\nPress any key to continue...");
        scanner.nextLine();
    }
}