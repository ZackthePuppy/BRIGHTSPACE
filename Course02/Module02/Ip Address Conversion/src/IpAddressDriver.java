/**
* Java Course 2 Module 2
* @author Mike Lemuel Chan
* @Description: passing a value into an objeect, and then divides it by a pattern, then printing the divided values
* @CreatedDate: 5/17/2022
* @ModifiedDate: 5/17/2022
*
*/

public class IpAddressDriver {
    public static void main(String[] args) throws Exception {
        System.out.println("IP ADDRESS CONVERSION\n");

        IpAddress ip = new IpAddress("216.27.6.136");
        System.out.println(ip.getDottedDecimal());
        System.out.println(ip.getOctet(4));
        System.out.println(ip.getOctet(1));
        System.out.println(ip.getOctet(3));
        System.out.println(ip.getOctet(2));

    }
}
