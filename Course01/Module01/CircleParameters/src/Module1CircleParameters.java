import java.util.Scanner;

/**
* @author: Mike Lemuel Chan
* @Description: Calculating Circle-Related Values
* @CreatedDate: 5/12/2022
* @ModifiedDate: 5/12/2022
*/

public class Module1CircleParameters {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        double radius, diameter, circumference, area;

        System.out.println("CIRCLE PARAMETERS");
        System.out.print("Enter a radius value: ");
        radius = sc.nextDouble();

        diameter = (2 * radius);
        circumference = 2 * (Math.PI) * (radius);
        area = Math.PI * (radius * radius);

        System.out.println("\nDiameter = " + diameter);
        System.out.println("Circumference = " + circumference);
        System.out.println("Area = " + area);

        sc.close();
    }
}
