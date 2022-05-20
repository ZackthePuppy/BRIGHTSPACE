import java.util.Scanner;

/**

* Java Course 2 Module 3
*
* @author Mike Lemuel Chan
* @Title: Vowel Counter
* @Description: prompts the user to input value, then checks for capital and small vowels, 
                then counts it when user exits the program
* @CreatedDate: 5/17/2022
* @ModifiedDate: TBA
*
*/

public class VowelCounterDriver {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        VowelCounter vowel = new VowelCounter();
        String value;
        while (true){
            
            System.out.print("Enter value: ");
            value = sc.nextLine();
            if (value.isEmpty()){
                vowel.printSummary();
                break;
            }
            else {
                vowel.processLine(value);
            }
        }
        sc.close();



    }
}
