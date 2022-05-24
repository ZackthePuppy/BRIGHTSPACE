/**
* Java Course 3 Module 3
*
* @author Mike Lemuel Chan
* @Description: views a table and distincts/view a value without duplicates
* @CreatedDate: 5/24/2022
* @ModifiedDate: 5/24/2022
*
*/
public class Driver {
    public static void main(String[] args) throws Exception {
        MySQL mysql = new MySQL(); //instantiation for MySQL class

        System.out.println("ALL VALUES FROM PERSON TABLE");
        mysql.viewAll(); //calls the viewall method which connects to mysql table and returns a result based on query
        System.out.println("=======================================");
        System.out.println("VALUES WITHOUT REPETITION OF FIRST NAME");
        mysql.distinctView();

    }
}
