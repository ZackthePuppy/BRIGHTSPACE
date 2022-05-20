import java.util.InputMismatchException;
import java.util.Scanner;

/**
* Java Course 2 Module 2
*
* @author Mike Lemuel Chan
* @Description: prompts the user the enter how many cars, then chooses model and color, then chain calls the printmake.printcolor
* @CreatedDate: 5/17/2022
* @ModifiedDate: 5/17/2022
*
*/

public class AutomobileDriver {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Automobile auto = new Automobile();
        System.out.println("AUTOMOBILE");
        String model, color;
        int carsCount;

        try {
            System.out.print("How many cars do you want to consider? ");
            carsCount = sc.nextInt();
            sc.nextLine(); // to prevent automatic calling of the first sc.nextLine inside for loop, since
                           // sc.nextInt() doesn't read newline

            for (int x = 0; x < carsCount; x++) { //loops depending on the value that was entered above
                System.out.print("Buick, Chevrolet, or Pontiac?(B, C, P): ");
                model = sc.nextLine(); //gets the value from user input

                if (!model.matches("[BbCcPp]")) { //executes IF the user input value is not B/b, C/c or P/p
                    System.out.println("The only valid selections are B, C, or P\n");
                    x--; //prevents the loop from incrementing if user puts wrong value
                } else {

                    if (model.equalsIgnoreCase("b")){
                        auto.setMake("Buick"); //whenever the user types b/B, this will execute, then pass the value of parameters in the method
                    }
                    else if (model.equalsIgnoreCase("c")){
                        auto.setMake("Chevrolet");
                    }
                    else if (model.equalsIgnoreCase("p")){
                        auto.setMake("Pontiac");
                    }

                    while (true) { // loops the color selection so it will run again if user entered incorrect
                                   // data/value
                        System.out.print("Blue, Green, or Red(B, G, R): ");
                        color = sc.nextLine();
                        if (!color.matches("[BbGgRr]")) {
                            System.out.println("The only valid selections are B, G, or R\n");
                        } else { //executes if no errors occured

                            if (color.equalsIgnoreCase("b")){
                                auto.setColor("Blue"); // calls the method setColor() and gives the value "Blue" to it
                            }
                            else if (color.equalsIgnoreCase("g")){
                                auto.setColor("Green");
                            }
                            else if (color.equalsIgnoreCase("r")){
                                auto.setColor("Red");
                            }

                            auto.printColor().printMake(); //chain call methods from the Automobile class
                            break; //end the while loop since the user entered a correct value
                        }
                    }

                }

            }
            sc.close(); //closes the scanner after the for loop ends
        } catch (InputMismatchException e) { //executes if the user does not put integer
            System.out.println("INVALID input!");
        }
    }
}
