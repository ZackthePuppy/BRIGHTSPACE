import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Town {
    private int numberOfAdults, numberOfChildren; // i declared it as a private to prevent other class to directly
                                                  // access them
    private Scanner sc = new Scanner(System.in);

    public void promptEnterKey() { // custom method in order to simulate the "enter to continue" and clears the
                                   // console for cleanliness
        System.out.println("Press \"ENTER\" to continue...");
        try {
            System.in.read(new byte[2]);
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        try {
            System.out.print("Enter population of adults: ");
            numberOfAdults = sc.nextInt(); // gets the population of adults

            System.out.print("Enter population of children: ");
            numberOfChildren = sc.nextInt(); // gets the population of children
        } catch (InputMismatchException e) {
            System.out.print("ERROR! Invalid input!");
        }
    }

    public void simulateBirth(int num) { // gets the value of num from passed parameters in main method, which contain
                                         // either 1 or 2
        try {
            System.out.print("Enter how many child/twin: ");
            numberOfChildren += sc.nextInt() * num; // adds a value to the variable numberofchildren, and multiplies it
                                                    // by either 1 or 2, depending on the passed parameter
            System.out.println("Successfully added!");
        } catch (InputMismatchException e) {
            System.out.print("Invalid input!");
        }
    }

    public void printStatistics() {
        System.out.println("STATISTICS\n");
        System.out.println("Adult's Population: " + numberOfAdults);
        System.out.println("Children's Population: " + numberOfChildren);
    }
}
