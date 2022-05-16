import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Mike Lemuel Chan
 * @Description: Generating an isosceles triangle based on user input
 * @CreatedDate: 5/13/2022
 * @ModifiedDate: 5/13/2022
 */

public class Triangle {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int size;
        System.out.println("ISOSCELES TRIANGLE");
        System.out.print("Enter the size of the equal sides in an Isosceles Triangle: ");

        try {
            size = sc.nextInt();
            if (size <= 0) { //if user puts a zero or negative number
                System.out.println("Number must be greater than 0 to print a triangle.");
            } else {
                for (int x = 0; x <= size; x++) { //loops depending on the value of size that was entered
                    for (int y = 0; y < x; y++) { //loops inside the x, and it runs depending on the value of the x
                        System.out.print("*"); // prints an asterisk, and the number of asterisk increases as the value of y increase
                    }
                    System.out.println(); // to print next line after the print of asterisk ends
                }
            }
        } catch (InputMismatchException e) { // catching the error if user typed a character instead of a number
            System.out.println("That is not a number!");
        }
        sc.close();

    }
}
