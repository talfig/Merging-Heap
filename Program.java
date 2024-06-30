import java.util.Scanner;

/**
 * The Program class provides methods to interact with and manipulate MinHeap and SortedHeap objects through a console interface.
 */
public class Program {
    static Scanner input = new Scanner(System.in);

    /**
     * Consumes invalid input from the scanner until a valid integer is entered.
     */
    private static void consumeInvalidInput() {
        while(!input.hasNextInt()) { // Loop until valid integer input is received
            System.out.println("Invalid input. Please enter a number:");
            input.nextLine(); // Consume the invalid input
        }
    }

    /**
     * Prints the user interface for selecting heap operations.
     */
    private static void printInterface() {
        System.out.println("Which of the following operations you wish to do next?");
        System.out.println("A:\n\t1. Make-Heap\n\t2. Insert\n\t3. Minimum\n\t4. Extract-Minimum\n\t5. Heap-Sort");
        System.out.println("B:\n\t6. Make-Heap\n\t7. Insert\n\t8. Minimum\n\t9. Extract-Minimum\n\t10. Heap-Sort");
        System.out.println("11. Union\n12. Stop");
        System.out.println("Please enter your choice:");
    }

    /**
     * Runs the user interface for manipulating Heap objects.
     */
    public static void heapImplement(boolean isMinHeap) {
        Heap A, B;
        if(isMinHeap) {
            A = new MinHeap();
            B = new MinHeap();
        } else {
            A = new SortedHeap();
            B = new SortedHeap();
        }
        int[] arr;
        int minVal;

        while(true) { // Infinite loop to keep the interface running
            printInterface();
            consumeInvalidInput(); // Ensure valid input is received
            int choice = input.nextInt();
            input.nextLine(); // Consume the newline character

            switch(choice) {
                case 1:
                    System.out.println("Please enter the path of your file:");
                    while((arr = ArrayUtils.fileToArray(input.nextLine())) == null) // Read array from file
                        System.out.println("Invalid file path.\nPlease enter a valid path for your file:");
                    A.makeHeap(arr);
                    break;

                case 2:
                    System.out.println("Please enter the number you wish to add:");
                    consumeInvalidInput(); // Ensure valid number input
                    int numA = input.nextInt();
                    input.nextLine(); // Consume the newline character
                    A.insert(numA);
                    break;

                case 3:
                    minVal = A.getMinVal();
                    if(minVal == Integer.MAX_VALUE) // If the heap is empty
                        System.out.println("The heap is empty. Therefore, there is no minimum value.");
                    else
                        System.out.println("The minimum is:\n" + minVal);
                    break;

                case 4:
                    minVal = A.extractMin();
                    if(minVal == Integer.MAX_VALUE) // If the heap is empty
                        System.out.println("The heap is empty. Therefore, there is no minimum value.");
                    else
                        System.out.println(minVal + " has been extracted.");
                    break;

                case 5:
                    System.out.print("Sorted A = ");
                    A.sortHeap();
                    break;

                case 6:
                    System.out.println("Please enter the path of your file:");
                    while((arr = ArrayUtils.fileToArray(input.nextLine())) == null) // Read array from file
                        System.out.println("Invalid file path.\nPlease enter a valid path for your file:");
                    B.makeHeap(arr);
                    break;

                case 7:
                    System.out.println("Please enter the number you wish to add:");
                    consumeInvalidInput(); // Ensure valid number input
                    int numB = input.nextInt();
                    input.nextLine(); // Consume the newline character
                    B.insert(numB);
                    break;

                case 8:
                    minVal = B.getMinVal();
                    if(minVal == Integer.MAX_VALUE) // If the heap is empty
                        System.out.println("The heap is empty. Therefore, there is no minimum value.");
                    else
                        System.out.println("The minimum is:\n" + minVal);
                    break;

                case 9:
                    minVal = B.extractMin();
                    if(minVal == Integer.MAX_VALUE) // If the heap is empty
                        System.out.println("The heap is empty. Therefore, there is no minimum value.");
                    else
                        System.out.println(minVal + " has been extracted.");
                    break;

                case 10:
                    System.out.print("Sorted B = ");
                    B.sortHeap();
                    break;

                case 11:
                    A.unionHeap(B);
                    break;

                case 12:
                    input.close(); // Close the scanner
                    System.exit(0); // Exit the program

                default:
                    System.out.println("You have entered a wrong number of operation!");
                    System.out.println("Please try again.");
                    break;
            }
            System.out.println("A = " + A);
            System.out.println("B = " + B);
        }
    }
}
