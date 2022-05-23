public abstract class BankAccount {
    protected double balance; //protected modifier so that the child can access this variable

    public void deposit (double amount){ //for depositing money
        this.balance += amount;
    }

    public void withdraw (double amount){ //for withdrawing money
        final int zeroBalance = 0; //avoidance of magic number
        if (amount > this.balance){ //executes if you withdraw with more than of your remaining balance;
            this.balance = zeroBalance;
        }
        else {
            this.balance -= amount;
        }
    }

    public abstract void display(); //abstract method, will be overrided by its child classes
    
}
