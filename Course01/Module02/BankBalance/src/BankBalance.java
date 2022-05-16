import java.text.NumberFormat;
import java.util.Scanner;

/**
 * 
 * @author Mike Lemuel Chan
 * 
 * @Description: calculate the years it take to get a certain amount of money
 * 
 * @CreatedDate: 5/12/2022
 * 
 * @ModifiedDate: 5/12/2022
 * 
 */

public class BankBalance {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        NumberFormat money = NumberFormat.getCurrencyInstance(); // instantiate the Numberformat in order to use it when
                                                                 // we print the money later
        int balance, amount, years = 0;

        System.out.println("BANK BALANCE");
        System.out.print("Enter starting balance: ");
        balance = sc.nextInt(); // get the value from user input

        while (true) { // indefinite loop until you break it
            if (balance >= 100_000) { // executes if the balance reaches more than or equal to 100,000
                amount = balance - (balance - 100_000); // we calculate the exact amount, because the balance is not exactly 100,000
                System.out.println("It takes " + years + " years to reach " + money.format(amount)); // we use  numberformat to print it as money.
                break;
            }
            balance *= 2; // doubles the balance every loop
            years++; // increases the value of years.
        }

        while (true) {
            if (balance >= 1_000_000) { // same in the first while loop above, but the condition is now 1,000,000
                amount = balance - (balance - 1_000_000);
                System.out.println("It takes " + years + " years to reach " + money.format(amount));

                if (years > 15) { // condition to make the customer dead and the bank keeps the money
                    System.out.println(
                            "The customer died due to accident. \nThe money isn't claimed and was kept in the bank.");
                }
                break;
                
            }
            balance *= 2;
            years++;
        }

        sc.close();

    }
}
