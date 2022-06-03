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
 * @ModifiedDate: 5/30/2022
 *
 */

public class EmployeeDriver {
    public static void main(String[] args) throws Exception { // main method
        DisplayDesign go = new DisplayDesign(); // instantiating just for the sole purpose of design
        Scanner sc = new Scanner(System.in);
        int empNum; // will be used for looping
        String name, sss, monthCheck, weekCheck, number = "[0-9]{1,}";
        int birthMonth, birthWeek;
        double hourlyPay, hoursWorked, salesWeek, commissionRate, salary;


        while (true) {
            try {
                go.printBox("EMPLOYEE PAYROLL PROGRAM");
                System.out.print("Number of Employee: ");
                empNum = sc.nextInt();
                Employee employee[] = new Employee[empNum]; // instantiates employee class in array, and the length of
                                                            // array depends on user input

                for (int x = 0; x < employee.length; x++) { // loops depending on the value of user entered earlier
                    go.clearConsole(); // clears console for cleanliness :)
                    go.printBox("PROFILE FOR EMPLOYEE #" + (x + 1));
                    System.out.println("Type Hourly[1], Salaried[2], Salaried + Commission[3]");
                    System.out.print("Enter 1, 2, or 3: ");
                    String choice = sc.next();
                    sc.nextLine(); // prevents the next scanner to read ahead since we will use nextLine for input
                                   // of name

                    if (!(choice.equals("1") || choice.equals("2") || choice.equals("3"))) { // executes if user typed a
                                                                                             // wrong input
                        System.out.println("Invalid choice, try again!");
                        x--;
                        go.PauseClear();

                    } else { // otherwise, execute this if no errors occured
                        System.out.print("Name: ");
                        name = sc.nextLine();
                        System.out.print("SSS Number: ");
                        sss = sc.next();
                        System.out.print("Birthday Month [1-12]: ");
                        monthCheck = sc.next();
                        System.out.print("Birthday Week: [1-4]: ");
                        weekCheck = sc.next();

                        if (!monthCheck.matches(number) || !weekCheck.matches(number)) { // runs if
                                                                                           // monthcheck/weekcheck
                                                                                           // doesn't contain number, or
                                                                                           // has a number that was
                                                                                           // mixed with letters/symbols
                            System.out.println("Invalid birthday! Try again.");
                            go.PauseClear();
                            x--;
                        }

                        else { // runs if no symbol/letters found on month and week
                            birthMonth = Integer.parseInt(monthCheck); // gives birthmonth a value from converted
                                                                       // monthcheck, since it is a String, and we need
                                                                       // integer
                            birthWeek = Integer.parseInt(weekCheck);
                            if ((birthMonth > 12 || birthMonth < 1) || (birthWeek > 4 || birthWeek < 1)) { // if user
                                                                                                           // didn't
                                                                                                           // follow
                                                                                                           // instruction,
                                                                                                           // this will
                                                                                                           // execute
                                                                                                           // and loops
                                                                                                           // from the
                                                                                                           // start
                                System.out.println("Invalid birthday! Try again.");
                                x--;
                            }

                            else if (choice.equals("1")) { // executes if user choose hourly
                                System.out.print("Hourly Pay: ");
                                hourlyPay = sc.nextDouble();
                                System.out.print("Hours worked this past week: ");
                                hoursWorked = sc.nextDouble();

                                employee[x] = new Hourly().load(name, sss, birthMonth, birthWeek, hourlyPay,
                                        hoursWorked); // calls the constructor and passes the value from here
                            }

                            else if (choice.equals("2")) { // executes if user choose salaried
                                System.out.print("Salary: ");
                                salary = sc.nextDouble();

                                employee[x] = new Salaried().load(name, sss, birthMonth, birthWeek, salary);
                            }

                            else if (choice.equals("3")) {
                                System.out.print("Salary: ");
                                salary = sc.nextDouble();
                                System.out.print("Sales for this past week: ");
                                salesWeek = sc.nextDouble();
                                System.out.print("Sales Commission Rate: ");
                                commissionRate = sc.nextDouble();

                                employee[x] = new SalariedPlusCommission().load(name, sss, birthMonth, birthWeek,
                                        salary, salesWeek, commissionRate);
                            }
                            go.PauseClear();
                        }
                    }
                }

                for (int x = 0; x < employee.length; x++) { // prints all the employee that was inputted earlier
                    go.printBox("PROFILE OF EMPLOYEE #" + (x + 1));
                    System.out.println(employee[x]);
                    go.lines();
                }
                break; // breaks the while loop

            } catch (InputMismatchException e) { // if user puts character instead of numeric
                System.out.println("Invalid input! Try again.");
                sc.nextLine();
                go.PauseClear(); // enter to continue then clears output for cleanliness
            }
        }
        sc.close();

    }

}
