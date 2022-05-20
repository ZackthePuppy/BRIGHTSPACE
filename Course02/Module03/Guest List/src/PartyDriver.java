/**
* Java Course 2 Module 3
*
* @author Mike Lemuel Chan
* @Title: GUEST LIST
* @Description: adds a value in string array, then prints it
* @CreatedDate: 5/17/2022
*
*/

public class PartyDriver {
    public static void main(String[] args) throws Exception {
        System.out.println("GUEST LIST");

        Party party = new Party(3, "David Beckham");
        party.addGuest("Roberto Baggio");
        party.addGuest("Zinedine Zidane");
        party.addGuest("Roberto Baggio");
        party.addGuest("Johan Cruyff");
        party.addGuest("Diego Maradona");
        party.printParty();
        
    }
}
