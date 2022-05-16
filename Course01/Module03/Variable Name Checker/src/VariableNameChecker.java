import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * @author Mike Lemuel Chan
 * @Description: Checks the user input variable name is Illegal, Legal, or in
 *               poor style.
 * @CreatedDate: 5/16/2022
 * @ModifiedDate: 5/16/2022
 */

public class VariableNameChecker {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String specialChar = "[!@#$%&*()_+=|<>?{}\\[\\]~-]";
        String number = "[0-9]";
        String capital = "[A-Z]";
        String variable, first;
        System.out.println("VARIABLE NAME CHECKER");

        while (true) {
            System.out.print("\nEnter a Variable Name (Q to Quit): ");
            variable = sc.nextLine();

            first = Character.toString(variable.charAt(0)); // gets the first character in the user input, then converts
                                                            // it into char, then converts into string in order to use
                                                            // it in regex
            if (variable.contains(" ") ||
                    Pattern.matches(number, first) ||
                    Pattern.matches(specialChar, first)) { // executes IF the string inputted has space, OR the first
                                                           // character is a number, OR the first character is a
                                                           // symbol/special char.
                System.out.println("Illegal variable");
            }

            else if (variable.equalsIgnoreCase("Q")) { // executes if user input typed the q or Q
                System.out.println("Thank you!");
                break;
            }

            else {
                for (int x = 0; x < variable.length(); x++) {
                    if (Pattern.matches(capital, first)
                            || Pattern.matches(specialChar, Character.toString(variable.charAt(x)))) { // executes if
                                                                                                       // the string
                                                                                                       // inputted is
                                                                                                       // capital letter
                                                                                                       // in first
                                                                                                       // character, OR
                                                                                                       // checks the
                                                                                                       // character one
                                                                                                       // by one and
                                                                                                       // searches for
                                                                                                       // symbol
                        System.out.println("Legal variable, but uses poor style.");
                        break;
                    }
                    if (x == variable.length() - 1) {
                        System.out.println("Good variable!");
                    }
                }
            }

        }
        sc.close();
    }
}
