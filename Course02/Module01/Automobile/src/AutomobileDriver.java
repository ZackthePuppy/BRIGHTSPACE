import java.util.Scanner;

public class AutomobileDriver {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("AUTOMOBILE");
        String model, color;
        int carsCount;
        
        System.out.print("How many cars do you want to consider? ");
        carsCount = sc.nextInt();

        for (int x=0; x<carsCount; x++){
            System.out.print("");
        }
    }
}
