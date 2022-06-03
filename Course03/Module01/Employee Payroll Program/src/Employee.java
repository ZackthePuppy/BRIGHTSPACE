import java.text.NumberFormat;
import java.util.Calendar;

public abstract class Employee {
    private String name, sss;
    private int birthMonth, birthWeek;
    private double paycheck;

    public Employee load(String name, String sss, int birthMonth, int birthWeek) { // super constructor, gets the name, sss,
                                                                              // birthmonth and birthweek
        this.name = name;
        this.sss = sss;
        this.birthMonth = birthMonth;
        this.birthWeek = birthWeek;
        return this;
    }

    // public String toString() { // automatically converts object into string
    //     NumberFormat dollar = NumberFormat.getCurrencyInstance();
    //     return "Employee Name: " + name +
    //             "\nSocial Security Number: " + sss +
    //             String.format("\nPaycheck %2s", dollar.format(getBonus()));
    // }
    
    public String toString() { // automatically converts object into string
        NumberFormat dollar = NumberFormat.getCurrencyInstance();
        return "Employee Name: " + name +
                "\nSocial Security Number: " + sss +
                String.format("\nPaycheck %2s", dollar.format(getBonus()));
    }

    public double getBonus() {
        final int bonusPay = 100, maxPaycheck = 1000;
        this.paycheck = getEarnings(); // gets the value from getEarning so that the paycheck will have a value

        Calendar calendar = Calendar.getInstance(); //instantiates calendar so we can use it as a basis for the employee's birthday
        int weekNum = calendar.get(Calendar.WEEK_OF_MONTH); // get the week number from month
        int monthNum = calendar.get(Calendar.MONTH); // get the month number

        if (this.birthMonth == monthNum && this.birthWeek == weekNum) {
            this.paycheck += bonusPay; // adds a bonus if the employee's birthday matches the current date

        }
        if (this.paycheck >= maxPaycheck) { // if paycheck exceeds 1000
            this.paycheck = maxPaycheck;
        }
        return this.paycheck;

    }

    public abstract double getEarnings(); // abstract method


}
