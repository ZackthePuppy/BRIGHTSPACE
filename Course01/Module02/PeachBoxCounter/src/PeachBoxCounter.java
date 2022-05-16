import java.util.Scanner;

/**
* @author Mike Lemuel Chan
* @Description: Printing peach box counter in for loop
* @CreatedDate: 5/12/2022
* @ModifiedDate: 5/12/2022
*/

public class PeachBoxCounter {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int peach;
        System.out.println("PEACH BOX COUNTER");

        System.out.print("Enter the number of peaches picked: ");
        peach = sc.nextInt();

        for (int x = 0; x <= peach; x += 20){ //it loops and depends on how many peach you put, then it increments to 20
            System.out.println("Shipped " + x + " so far.");
        }

        sc.close();
    }
}
