import java.util.InputMismatchException;
import java.util.Scanner;
/**

* Java Course 2 Module 1

*

* @author Mike Lemuel Chan

* @Description: getting the plant date, germination date and initial height, then displays it

* @CreatedDate: 5/16/2022

* @ModifiedDate: 5/16/2022

*

*/
public class MapleTree {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        MapleTreeDriver maple = new MapleTreeDriver(); //instantiation of class so that we can call its method later
        String dateFormat = "[0-9]{2}/[0-9]{2}/[0-9]{4}"; //initializes a regex for proper formatting in dates

        System.out.println("MAPLE TREE");
        String heightDate, plantDate;
        double height;

        try {
            System.out.print("Enter Plant Date (DD/MM/YYYY): ");
            plantDate = sc.nextLine(); //Prompts the user to put a plant date

            System.out.print("Enter Germination Date (DD/MM/YYYY): ");
            heightDate = sc.nextLine();

            System.out.print("Enter Observed Height (meters): ");
            height = sc.nextDouble();

            if (!plantDate.matches(dateFormat)
                    || !heightDate.matches(dateFormat)) { // executes if plantDate and germination date is not in proper
                                                          // format DD/MM/YYYY
                System.out.println("The Plant Date or Germination Date doesn't follow the proper format!");
            }

            else {
                maple.plant(plantDate); // calls and sends the parameter in plant method
                maple.germinate(height, heightDate);
                maple.dumpData(); // calls the dumpData() method which prints the value that was entered earlier
            }

            sc.close();
        } catch (InputMismatchException e) { // executes if user entered a character in height
            System.out.println("Invalid height!");
        }
    }
}
