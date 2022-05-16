import java.util.Scanner;

/**
* @author Mike Lemuel Chan
* @Description: Prints the length of the variable that what entered by user, and it also prints the first character of the value
* @CreatedDate: 5/12/2022
* @ModifiedDate: 5/12/2022
*/
public class NameTell {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner (System.in);
        String name;
        System.out.println("NAME TELL");
        System.out.print("Enter your name: ");
        name = sc.nextLine();  //prompts user to type a name
        System.out.println("Your name has " + name.length() + " letters including spaces"); //prints the length of the name that was entered
        System.out.println("The first letter is: " + name.substring(0, 1)); //prints the first character in the string that was entered
        sc.close();
    }
}
