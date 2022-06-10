import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Java Course 3 Module 1
 *
 * @author Mike Lemuel Chan
 * @Description: Selects which type of employee, then based on selection, it
 *               will polymorph and show different results. Inheritance also
 *               included here
 * @CreatedDate: 5/20/2022
 * @ModifiedDate: 6/08/2022
 *
 */

public class MainDriver {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        Calendar calendar = Calendar.getInstance();
        int empNumber;
        int currentMonth = calendar.get(Calendar.MONTH) + 1; //variable for viewing the current month
        int currentWeek = calendar.get(Calendar.WEEK_OF_MONTH);
        String empType;
        DisplayDesign go = new DisplayDesign();


        while (true){
        go.printBox("EMPLOYEE PAYROLL PROGRAM || MONTH: " + currentMonth + " WEEK: " + currentWeek); //main title, it also shows the current month and week by their number

        try {
        System.out.print("Number of Employees: ");
        empNumber = sc.nextInt(); //determining on how many employees

        if (empNumber <= 0) {
            System.out.println("Number cannot be zero/negative!");
            go.pauseClear();
        }

        else {
            go.clearConsole();
            Employee emp [] = new Employee [empNumber]; 


            for (int x=0; x<emp.length; x++){ //for loops, depends on how many employee
            go.printBox("EMPLOYEE " + (x+1) + "'s TYPE");
            System.out.print("[1] - Hourly \n[2] - Salaried \n[3] - Salaried Plus Commission" + 
            "\n\nChoose: ");
                empType = sc.next(); //user input variable for choosing what type of employee

            if (empType.equals("1")) {
                emp[x] = new Hourly (); //instantiate specific emp index to specified subclass
            }

            else if (empType.equals("2")) {
                emp[x] = new Salaried (); //instantiate specific emp index to specified subclass
            }

            else if (empType.equals("3")) {
                emp[x] = new SalariedPlusCommission (); //instantiate specific emp index to specified subclass
            }

            if (empType.matches("[1-3]")){ //executes if it's in the range 1-3
                emp[x].load(x+1); //calls the method load in subclass to whichever the index was instantiated
            }

            else {
                System.out.println("Invalid choice! Try again.");
                x--; //prevents increment of loop, we don't want to increment it if there is an error in values
            }
            go.pauseClear();
            }

            System.out.println("PROCESS COMPLETE!");
            go.pauseClear();

        for (int x=0; x<emp.length; x++){ //prints the information of all employees that was added earlier
            go.printBox("EMPLOYEE PROFILE #" + (x+1));
            System.out.println(emp[x]);
            System.out.println("=================================");
        }
        sc.close();
            break; //breaks the endless loop
           
        }

        } catch (InputMismatchException e){ //catches error if you put characters on scanner.nextInt()
            System.out.println("Invalid number! Try again.");
            sc.nextLine();
            go.pauseClear();
        }

        }


    }
}
