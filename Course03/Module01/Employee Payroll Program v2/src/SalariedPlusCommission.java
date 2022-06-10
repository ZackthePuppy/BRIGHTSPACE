import java.util.InputMismatchException;

public class SalariedPlusCommission extends Employee {
    private double weeklySalary, sales, commissionRate;

    public void load(int empNumber) {
        DisplayDesign go = new DisplayDesign(); // instantiation of class, its purpose is to have box in titles, and
                                                // also clears the output
        double weeklySalary, sales, commissionRate;

        super.load(empNumber); // loads the load method in parent/super class

        while (true) {
            try {
                System.out.print("Salary: "); // user input variable for salary
                weeklySalary = sc.nextDouble();

                System.out.print("Sales for this past week: ");
                sales = sc.nextDouble();

                System.out.print("Sales commission rate: ");
                commissionRate = sc.nextDouble();

                if (weeklySalary <= 0 || sales <= 0 || commissionRate <= 0) { // if user put zero/negative number
                    System.out.println("Input cannot be zero/negative. Try again.");
                    go.pauseClear();
                    go.printBox("PROFILE FOR EMPLOYEE #" + empNumber);
                }

                else { //runs if the user input was validated and no errors
                    this.weeklySalary = weeklySalary;
                    this.sales = sales;
                    this.commissionRate = commissionRate;
                    break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Try again.");
                sc.nextLine(); // moves the scanner to the next line so it will not read the earlier values
                go.pauseClear();
                go.printBox("PROFILE FOR EMPLOYEE #" + empNumber);
            }
        }

    }

    public double getEarnings() {
        return weeklySalary + (sales * commissionRate); //computation for salaried plus commission, the value will be returned
    }

}