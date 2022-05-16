import java.util.Scanner;

public class OneHundredthBirthday {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String month;
        int day, year, hundredyear;
        System.out.println("ONE-HUNDREDTH BIRTHDAY");

        System.out.print("Enter the month you were born: ");
        month = sc.next();

        System.out.print("Enter the day you were born: ");
        day = sc.nextInt();

        System.out.print("Enter the year you were born: ");
        year = sc.nextInt();

        hundredyear = year + 100;

        System.out.println("You will be 100 on " + month + " " + day + ", " + hundredyear + ".");
        sc.close();
    }
}
