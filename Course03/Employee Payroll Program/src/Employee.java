import java.io.IOException;
import java.util.Calendar;

public abstract class Employee {
    private String name, sss;
    private int birthMonth, birthWeek;
    double paycheck;

    public Employee (String name, String sss, int birthMonth, int birthWeek){
        this.name = name;
        this.sss = sss;
        this.birthMonth = birthMonth;
        this.birthWeek = birthWeek;
    }

    public String toString() { //automatically converts object into string
        return "Employee Name: " + name +
                "\nSocial Security Number: " + sss +
                String.format("\nPaycheck %2s", getBonus());
    }

    public double getBonus() {
        final int bonusPay = 100, maxPaycheck = 1000;
        this.paycheck = getEarnings(); //gets the value from getEarning so that the paycheck will have a value
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 5, 20); // sets the current date
        int weekNum = calendar.get(Calendar.WEEK_OF_MONTH); // get the week number from month
        int monthNum = calendar.get(Calendar.MONTH); // get the month number

        if (this.birthMonth == monthNum && this.birthWeek == weekNum) { 
            this.paycheck += bonusPay; //adds a bonus if the employee's birthday matches the current date
            
        }
        if (this.paycheck >= maxPaycheck){ //if paycheck exceeds 1000
            this.paycheck = maxPaycheck;
        }
        return this.paycheck;
    
    }

    public abstract double getEarnings(); //abstract method

    public void PauseClear(){ //use to make the output clean, and prompts user to ENTER to continue that serves as pause
        System.out.println("Press \"ENTER\" to continue...");
        try {
            System.in.read(new byte[2]);
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
