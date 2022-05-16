import java.util.Scanner;
public class StockChangeCalculator {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String symbol;
        double percentChange, day1, day2;
        System.out.println("Stock Change Calculator");

        System.out.print("Enter the stock symbol: ");
        symbol = sc.next().toUpperCase();
        System.out.print("Enter " + symbol + "'s day 1 value: ");
        day1 = sc.nextDouble();
        System.out.print("Enter " + symbol + "'s day 2 value: ");
        day2 = sc.nextDouble();

        percentChange = (( (day1 - day2) / (-day1) ) * 100);

        System.out.print(symbol + " has changed "
         + String.format("%.02f", percentChange) + "% in one day." );

        sc.close();
    }
}
