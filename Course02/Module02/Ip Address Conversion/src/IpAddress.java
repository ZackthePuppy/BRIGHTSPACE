public class IpAddress {
    private String dottedDecimal;
    private int firstOctet, secondOctet, thirdOctet, fourthOctet;

    public IpAddress (String dottedDecimal){

        int firstDot, secondDot, thirdDot; //we declare a 3 variables, it will be used to store the indexes

        this.dottedDecimal = dottedDecimal; //stores the value from the passed parameter to the variable that was declared above

        //getting index numbers
        firstDot = dottedDecimal.indexOf(".", 0); //counts the number of index until first dot occurs
        secondDot = dottedDecimal.indexOf(".", firstDot+1); //we start from the value of firstDot plus 1 so that the first dot will not be scanned
        thirdDot = dottedDecimal.indexOf(".", secondDot+1);

        firstOctet = Integer.parseInt(dottedDecimal.substring(0, firstDot)); //trims the ip string using the index that we store above
        secondOctet = Integer.parseInt(dottedDecimal.substring(firstDot+1, secondDot)); //here, we add +1 to prevent printing the dots
        thirdOctet = Integer.parseInt(dottedDecimal.substring(secondDot+1, thirdDot));
        fourthOctet = Integer.parseInt(dottedDecimal.substring(thirdDot+1)); //here, we didn't include the end index, because it is automatic


    }

    public String getDottedDecimal(){
        return dottedDecimal; //returns the value of the dotteddecimal 
    }

    public int getOctet (int octetPosition) {
        if (octetPosition == 1){ //executes if the mainmethod calls this, and pass a parameter with a value of 1
            return firstOctet;
        }
        else if (octetPosition == 2){
            return secondOctet;
        }
        else if (octetPosition == 3){
            return thirdOctet;
        }
        else if (octetPosition == 4){
            return fourthOctet;
        }
        else {
            return 0;
        }
    }
}