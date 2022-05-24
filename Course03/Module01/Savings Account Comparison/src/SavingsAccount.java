public class SavingsAccount {
    private double balance; //instance variable/regular variable
    private final int accountNumber; //instance constant
    private static double annualIntRate; //class variable

    public SavingsAccount (int accountNumber, double balance){ //constructor
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void addMonthlyInterest(){ //adds an interest to the balance
        balance += (balance * annualIntRate / 12);
    }

    public static void setAnnualIntRate (double intRate) { //sets the value of annual Interest Rate
        annualIntRate = intRate;
    }

    public double getBal (){ //to get the value so we can add the balance of 2 accounts later
        return balance;
    }

    public String toString (){ //prints the account and balance number
        return String.format("%-10s %7s %5s", accountNumber, String.format("%.02f", balance), "");
    }

}
