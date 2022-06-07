import java.util.InputMismatchException;
import java.util.Scanner;

public class MainDriver {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int empNumber;
        String empType;
        DisplayDesign go = new DisplayDesign();

        while (true){
        go.printBox("EMPLOYEE PAYROLL PROGRAM");

        try {
        System.out.print("Number of Employees: ");
        empNumber = sc.nextInt();

        if (empNumber <= 0) {
            System.out.println("Number cannot be zero/negative!");
            go.pauseClear();
        }

        else {
            go.clearConsole();
            Employee emp [] = new Employee [empNumber]; 


            for (int x=0; x<emp.length; x++){
            go.printBox("EMPLOYEE " + (x+1) + "'s TYPE");
            System.out.print("[1] - Hourly \n[2] - Salaried \n[3] - Salaried Plus Commission" + 
            "\n\nChoose: ");
                empType = sc.next();

            if (empType.equals("1")) {
                emp[x] = new Hourly (); //instantiate specific emp index to specified subclass
            }

            if (empType.matches("[1-3]")){ //executes if it's in the range 1-3
                emp[x].load(x+1); //calls the method load in subclass to whichever the index was instantiated
            }

            else {
                System.out.println("Invalid choice! Try again.");
                go.pauseClear();
                x--; //prevents increment of loop, we don't want to increment it if there is an error in values
            }

            }

            go.pauseClear();

        for (int x=0; x<emp.length; x++){
            go.printBox("EMPLOYEE PROFILE #" + (x+1));
            System.out.println(emp[x]);
            System.out.println("=================================");
        }
        sc.close();
            break; //breaks the endless loop if user entered a value properly
           
        }

        } catch (InputMismatchException e){
            System.out.println("Invalid number! Try again.");
            sc.nextLine();
            go.pauseClear();
        }

        }


    }
}
