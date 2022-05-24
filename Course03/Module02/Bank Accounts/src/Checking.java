import java.text.NumberFormat;

public class Checking extends BankAccount {
    
    public Checking (double amount){
        final int zeroBalance = 0; //prevents magic number
        if (amount <= 0){ //executes if given amount is negative number
            amount = zeroBalance; //makes the value zero
        }
        else {
            super.balance = amount;
        }
    }

    public void writeACheck (double amount) { //calls to write a check and reduces your balance
        final int serviceFee = 1;
        super.balance -= (amount + serviceFee);
    }

    public void display (){ //display the remaining balance in check account
        NumberFormat dollar = NumberFormat.getCurrencyInstance();
        System.out.println("Checking Account Balance = " + dollar.format(super.balance));
    }
}
