import java.util.InputMismatchException;

public class Salaried extends Employee{
    private double weeklySalary;

    public void load(int empNumber) {
        DisplayDesign go = new DisplayDesign();
        double weeklySalary;

        super.load(empNumber);

        while (true){
            try {
                System.out.print("Enter salary: ");
                weeklySalary = sc.nextDouble();

                if (weeklySalary <= 0){ //if user put zero/negative number
                    System.out.println("Input cannot be zero/negative. Try again.");
                    go.pauseClear();
                go.printBox("PROFILE FOR EMPLOYEE #" + empNumber);
                }

                else {
                    this.weeklySalary = weeklySalary;
                    break;
                }

            } 
            catch (InputMismatchException e){
                System.out.println("Invalid input! Try again.");
                sc.nextLine();
                go.pauseClear();
                go.printBox("PROFILE FOR EMPLOYEE #" + empNumber);
            }
        }
    }

    public double getEarnings(){
        return this.weeklySalary;
    }

}
