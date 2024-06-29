import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Which of the following choices do you wish to use to implement the heap?");
        System.out.println("1. Minimum-Heap\n2. Sorted-Heap");

        while(true) { // Infinite loop to keep the interface running
            System.out.println("Please enter your choice:");
            while(!input.hasNextInt()) { // Loop until valid integer input is received
                System.out.println("Invalid input. Please enter a number:");
                input.nextLine(); // Consume the invalid input
            }
            int choice = input.nextInt();
            input.nextLine(); // Consume the newline character

            switch(choice) {
                case 1:
                    Program.heapImplementation(true); // Call the method for Min-Heap implementation
                    break;
                case 2:
                    Program.heapImplementation(false); // Call the method for Sorted-Heap implementation
                    break;
                default:
                    System.out.println("You have entered a wrong number of implementation!");
                    System.out.println("Please try again.");
                    break;
            }

            // Ask the user if they wish to continue
            System.out.println("Do you want to continue? (Y/n)");
            String answer = input.nextLine();
            if(!answer.equals("Y"))
                break;
        }

        input.close(); // Close the Scanner object
    }
}