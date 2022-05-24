import java.text.NumberFormat;

public class Savings extends BankAccount {
    private double intRate;

    public Savings(double amount, double interest) { //constructor for creating savings acc
        super.balance = amount;
        this.intRate = interest;
    }

    public void addInterest() { //for interest 
        super.balance = (intRate * super.balance) + super.balance;
    }

    public void display() { //displays savings account balance
        NumberFormat dollar = NumberFormat.getCurrencyInstance();
        System.out.println("Savings Account Balance = " + dollar.format(super.balance));

    }
}
