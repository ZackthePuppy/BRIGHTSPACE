import java.util.InputMismatchException;

public class Hourly extends Employee {
    private double hourlyPay, hoursWorked;

    public void load (int empNumber) {
        DisplayDesign go = new DisplayDesign(); //just for border, pause and clear console
        double hourlyPay, hoursWorked;
        
        super.load(empNumber); //calls the superclass' load method

        while (true){

            try {
                System.out.print("Hourly Pay: ");
                hourlyPay = sc.nextDouble();

                System.out.print("Hours worked during the past week: ");
                hoursWorked = sc.nextDouble();

                if (hourlyPay <= 0 || hoursWorked <= 0){ //executes if user entered zero/negative numbers.
                    System.out.print("Input cannot be zero/negative. Try again.");
                    go.pauseClear();
                    go.printBox("PROFILE FOR EMPLOYEE #" + empNumber); //prints a title so that the user still knows the employee number
                }

                else { //this will only run if no errors occured.
                    this.hourlyPay = hourlyPay;
                    this.hoursWorked = hoursWorked;
                    break; //breaks the endless loop
                }

            } catch (InputMismatchException e){
                System.out.println("Invalid input! Try again.");
                sc.nextLine(); //moves the scanner to the next line so it will not read the earlier values
                go.pauseClear();
                go.printBox("PROFILE FOR EMPLOYEE #" + empNumber);
            }
        }
    }

    public double getEarnings() { 
        double overTime = 40, halfHourly = this.hourlyPay * 0.5,
         hourlyPayCheck = 0;

        if (this.hoursWorked > 40){
            hourlyPayCheck = overTime * hourlyPay; //calculate the regular hours work paycheck. 40 * hourlyPay
            hourlyPayCheck += (this.hoursWorked - overTime) * halfHourly; //adds the overtime hours in paycheck. overtime hour * half of hourlypay
        }
        else {
            hourlyPayCheck = this.hourlyPay * this.hoursWorked; //if no overtime occured
        }
        
        return hourlyPayCheck;
    }


}
