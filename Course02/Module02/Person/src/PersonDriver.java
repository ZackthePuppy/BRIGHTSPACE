/**
* Java Course 2 Module 2
* @author Mike Lemuel Chan
* @Description: instantiates and object, and setting a value by calling a method and passing a parameter's value
* @CreatedDate: 5/17/2022
* @ModifiedDate: 5/17/2022
*/

public class PersonDriver {
    public static void main(String[] args) throws Exception {
        System.out.println("PERSON");

        Person person1 = new Person();
        person1.printFullName();
        Person person2 = new Person("Matt", "Thebo");
        person2.printFullName();
        person1.setFirst("Paul");
        person1.setLast("John");
        person1.printFullName();
        System.out.println("Total number of people = " +
          Person.getNumOfPeople());

          
    }
}
