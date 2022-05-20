public class Person {
    private String firstName, lastName;
    private static int numberOfPeople; //I used static because the main method calls directly, instead of instantiating it

    public Person (){
        this.firstName = "John";
        this.lastName = "Doe";
        numberOfPeople +=1; //increases the number of people
    }

    public Person (String firstname, String lastName){
        this.firstName = firstname;
        this.lastName = lastName;
        numberOfPeople += 1;
    }

    public void setFirst(String firstName){ //gets the value from the main method whenever the main method calls it
        this.firstName = firstName;
    }

    public void setLast(String lastName){ //same as the firstname, but this is for lastname
        this.lastName = lastName;
    }

    public static int getNumOfPeople (){ //returns the value of numberOfPeople
        return numberOfPeople;
    }

    public void printFullName (){
        System.out.println(firstName + " " + lastName); //concatenates and prints the first and last name
    }

}
