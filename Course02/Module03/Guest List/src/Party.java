import java.util.Arrays;

public class Party {
    private final int maxGuest; //I make it final so that the value will be constant
    private int guestNum;
    private String guestName [], hostName;


    public Party (int maxGuest, String hostName){ //constructor, used to put values on "this." from given parameters
        this.guestName = new String [maxGuest];
        this.maxGuest = maxGuest;
        this.hostName = hostName;
    }

    public void addGuest (String guestName){ //calls by main method, then gives the value of the parameters
        if (isOnList(guestName)) { //calls the isOnList method to pass the parameter, and expects a return, and checks if its true
            System.out.println(guestName + " is already on the guest list.");
        }

        else if (guestNum >= maxGuest){ //executes and prints if the number of guest exceeds the max guests
            System.out.println(guestName + " cannot come to the party. The guest list is full.");
        }

        else {
            for (int x=0; x<this.guestName.length; x++){ //loops depending on the number of guests

                if (this.guestName[x] == null){ //executes if the index number of guestName is null
                    this.guestName[x] = guestName; //puts a value in that specific index that was detected as null
                    guestNum += 1; //increases guest number
                    break;
                }
            }
        }
    }

    public boolean isOnList (String guest){
        if (Arrays.toString(this.guestName).contains(guest)){ //check in all index of guest name if the given parameter's value is already on the array
            return true;
        }
        else {
            return false;
        }
    }

    public void printParty(){ //prints the values of guestName
        System.out.println("\nGuest list for " + hostName + "'s Party:");
        for (int x=0; x<this.guestName.length; x++){
            System.out.println("\t" + this.guestName[x]);
        }
    }

}
