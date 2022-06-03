import java.util.Scanner;

/**
 * @author Mike Lemuel Chan
 * @Description: Divides the phone number into 3 parts, the country code, area
 *               code, and local phone digit
 * @CreatedDate: 5/16/2022
 * @ModifiedDate: 5/16/2022
 */

public class PhoneNumberDisector {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String number; // declares a string type which I named number

        while (true) { // unlimited loop
            System.out.println("PHONE NUMBER DISECTOR");
            System.out.println("Enter a number in the form CC-AREA-LOCAL");
            System.out.printf("%-10s %15s", "WHERE:", "CC - COUNTRY CODE" + "\n");
            System.out.printf("%-10s %15s", "", "AREA - AREA CODE DIGITS" + "\n");
            System.out.printf("%-10s %15s", "", "LOCAL - LOCAL PHONE DIGITS");
            System.out.print("\n\nOr Enter Q to Quit: ");

            try {
                number = sc.nextLine();

                if (number.equalsIgnoreCase("Q")) { // executes IF user typed q OR Q
                    System.out.println("Thank you");
                    sc.close();
                    break; // breaks the loop
                }

                else {
                    int ccstart = number.indexOf("-"); // counts the index of number until a dash (-) is detected.
                    int areastart = number.indexOf("-", ccstart + 1); // counts the index of number up until a 2nd dash
                                                                      // was detected

                    String cc = number.substring(0, ccstart); // get the string of the number starting from index 0 up
                                                              // to the value of ccstart/first dash
                    String area = number.substring(ccstart + 1, areastart); // from index of the ccstart/first dash up
                                                                            // to the value of the areastart/2nd dash
                    String local = number.substring(areastart + 1); // from 2nd dash up to the end/last index of number
                                                                    // variable.

                    System.out.println("Country Code = " + cc +
                            "\nArea Code = " + area +
                            "\nLocal Phone Number = " + local);
                    System.out.print("\nPress ENTER to continue...");
                    sc.nextLine();
                }
            } catch (StringIndexOutOfBoundsException e) { // it will only execute if the user didn't follow the
                                                          // instruction, example 123-4567
                System.out.println("INVALID Number! Try again.");
                System.out.print("\nPress ENTER to continue...");
                sc.nextLine();
            }

        }

    }
}
