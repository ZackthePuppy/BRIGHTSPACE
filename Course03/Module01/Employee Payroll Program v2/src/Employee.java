import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Scanner;

public abstract class Employee {
    private String name, sss;
    private int birthMonth, birthWeek;
    int empNumber;
    Scanner sc = new Scanner(System.in); //instantiated outside so child classes can use it

    public void load(int empNumber) {
        DisplayDesign go = new DisplayDesign(); // instantiation, its sole purpose is to print a string with box. It
        // can also pause and clear output.
        

        String name, sss;
        int birthMonth, birthWeek;
        this.empNumber = empNumber; // for employee number, this will also be used in child class

        while (true) {

            try  {
                System.out.print("Name: ");
                name = sc.next();
                name += sc.nextLine(); //if user input has spaces, the next value will add in the variable

                System.out.print("SSS: ");
                sss = sc.next();
                sss += sc.nextLine(); //prevents reading ahead if ever the user puts a string with spaces

                System.out.print("Birthday Month [1-12]: ");
                birthMonth = sc.nextInt();

                System.out.print("Birthday Week [1-5]: "); //instead of 1-4, the calendar detects a week 5, so I made it 1-5
                birthWeek = sc.nextInt();

                if (birthMonth > 12 || birthWeek > 5 || birthMonth < 1 || birthWeek < 1) { // if birthMonth / birthweek
                                                                                           // is not in range
                    System.out.println("Invalid birthday! Try again.");
                    go.pauseClear();
                    go.printBox("PROFILE FOR EMPLOYEE #" + empNumber); // prints the title so that user can still see
                                                                       // the employee number after clearing the console
                                                                       // because of input error
                }

                else { // finally stores in instance variables IF there aren't any errors
                    this.name = name;
                    this.sss = sss;
                    this.birthMonth = birthMonth;
                    this.birthWeek = birthWeek;
                    break; // breaks the endless while
                }

            }

            catch (Exception e) {
                System.out.println("Invalid input! Try again.");
                sc.nextLine();
                go.pauseClear();
                go.printBox("PROFILE FOR EMPLOYEE #" + empNumber);
            }
        }

    }

    public String toString() {
        NumberFormat dollar = NumberFormat.getCurrencyInstance(Locale.US);
        return String.format("%-15s %-15s\n", "Employee Name: ", this.name) +
                String.format("%-15s %-15s\n", "SSS Number: ", this.sss) +
                String.format("%-15s %-15s", "Paycheck: ", dollar.format(getBonus()));
    }

    public double getBonus() {
        Calendar calendar = Calendar.getInstance(Locale.US);
        double bonus = 100;
        int currentMonth = calendar.get(Calendar.MONTH) +1; // gets the month number, +1 because the java starts with 0 in january
        int currentWeek = calendar.get(Calendar.WEEK_OF_MONTH); // gets the week of month's number

        if (this.birthMonth == currentMonth && this.birthWeek == currentWeek) { // compares given birthday if it matches
            return getEarnings() + bonus; // to the current date
        }

        else { // if it's not his/her birthday, then it will return the paycheck without
               // modifying the value
            }
            return getEarnings();

    }

    public abstract double getEarnings();

}
