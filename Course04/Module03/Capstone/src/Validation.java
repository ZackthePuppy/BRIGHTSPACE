public class Validation {

    public boolean name (String firstName, String lastName) {
        String numbers = "[0-9]";

        for (int x=0; x<firstName.length(); x++){
            if (Character.toString(firstName.charAt(x)).matches(numbers)){
                System.out.println("INVALID firstname!");
                return false;
            }
        }

        for (int x=0; x<lastName.length(); x++){
            if (Character.toString(lastName.charAt(x)).matches(numbers)){
                System.out.println("INVALID lastname!");
                return false;
            }
        }
        
        if (firstName.isBlank() || lastName.isBlank()){
            System.out.println("INVALID name! Please fill up properly");
            return false;
        }

        else {
            return true;
        }
}


}
