/**
* Java Course 3 Module 1
*
* @author Mike Lemuel Chan
* @Description: Sets an account number and balance in 2 different objects, then prints it in looping
* @CreatedDate: 5/24/2022
* @ModifiedDate: 5/24/2022
*
*/

public class SavingsAccountDriver {
    public static void main(String[] args) throws Exception {
        SavingsAccount saver1 = new SavingsAccount(10002, 2000); //instantiation
        SavingsAccount saver2 = new SavingsAccount(10003, 3000);
        double totalBalance = 0;

        System.out.println("MONTHLY BALANCES FOR 1 YEAR WITH 5% ANNUAL INTEREST");

        System.out.printf("%-8s %-10s %7s %5s %-10s %7s", "MONTH", "ACCOUNT#", "BALANCE", "", "ACCOUNT#",
                "BALANCE" + "\n");

        SavingsAccount.setAnnualIntRate(.05); //sets/gives a value to the annualIntRate

        for (int x = 0; x <= 12; x++) { //for printing the output
            System.out.printf("%-8s %-1s %1s", x, saver1, saver2 + "\n"); //prints current balance
            saver1.addMonthlyInterest(); //adds an interest in balance for every loop
            saver2.addMonthlyInterest();
            if (x == 11) {
                totalBalance = saver1.getBal() + saver2.getBal(); // I set it to month 11 because if you get the total
                                                                  // in month 12, different total balance will occured
            }
        }
        System.out.printf("\nFinal balance of both accounts combined: %.02f", totalBalance); //prints the total balance
    }
}
