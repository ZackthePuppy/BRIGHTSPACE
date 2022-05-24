import java.util.ArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");

        ArrayList<String> addressList = new ArrayList<String>();
        addressList.add("1600 Pennsylvania Avenue");
        addressList.add("221B Baker Street");
        addressList.add("8700 N.W. River Park Drive");
        addressList.forEach((list) -> System.out.println(list));
    }
}
