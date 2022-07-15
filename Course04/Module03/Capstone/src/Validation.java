import java.sql.SQLException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Validation extends DatabaseConnection {
    DisplayDesign go = new DisplayDesign();
    String numbers = "[0-9]"; // regex, this will be used for checking input if the input has numbers
    String special = "[!@#$%&*()_+=|<>?{}\\[\\]~-]"; // regex for special character
    String letters = "[A-z]";

    public boolean name(String firstName, String lastName) {

        for (int x = 0; x < firstName.length(); x++) { // iterates strings to check if it has numbers
            if (Character.toString(firstName.charAt(x)).matches(numbers)) {
                System.out.println("INVALID firstname! Try again later.");
                return false;
            }
        }

        for (int x = 0; x < lastName.length(); x++) {
            if (Character.toString(lastName.charAt(x)).matches(numbers)) {
                System.out.println("INVALID lastname! Try again later.");
                return false;
            }
        }

        if (firstName.isBlank() || lastName.isBlank()) { // checks if input is empty/blank
            System.out.println("INVALID name! Please fill up properly");
            return false;
        }

        else {
            return true;
        }
    }

    public boolean customerAccount(String firstName, String lastName, String address) {
        for (int x = 0; x < firstName.length(); x++) { // iterates strings to check if it has numbers
            if (Character.toString(firstName.charAt(x)).matches(numbers)) {
                System.out.println("INVALID firstname! Try again later.");
                return false;
            }
        }

        for (int x = 0; x < lastName.length(); x++) {
            if (Character.toString(lastName.charAt(x)).matches(numbers)) {
                System.out.println("INVALID lastname! Try again later.");
                return false;
            }
        }

        for (int x = 0; x < address.length(); x++) {
            if (Character.toString(address.charAt(x)).matches(numbers)) {
                System.out.println("INVALID address! Try again later.");
                return false;
            }
        }

        if (firstName.isBlank() || lastName.isBlank() || address.isBlank()) { // if user doesn't put anything
            System.out.println("INVALID input! Don't leave it blank!");
            return false;
        }

        else { // returns true to make the input validated if there was no error in input
            return true;
        }
    }

    public boolean policyHolderName(String firstName, String lastName,
            String birthDate, String address, String licenseNum) {
        int firstDash, secondDash, year, month, day;

        try {
            // we will split the birthDate values in order to validate the date that was
            // given by the user
            firstDash = birthDate.indexOf("-", 0);
            secondDash = birthDate.indexOf("-", firstDash + 1);
            year = Integer.parseInt(birthDate.substring(0, firstDash));
            month = Integer.parseInt(birthDate.substring(firstDash + 1, secondDash));
            day = Integer.parseInt(birthDate.substring(secondDash + 1));

            String dt = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.of(year, month, day));
            LocalDate givenBday = LocalDate.parse(dt);

            if (givenBday.isAfter(LocalDate.now())) {
                System.out.println("INVALID! A birthday cannot be in the future! ");
                go.pauseClear();
                return false;
            }

            if (firstName.isBlank() || lastName.isBlank() || birthDate.isBlank() || address.isBlank()
                    || licenseNum.isBlank()) { // validation if there are no inputs
                System.out.println("INVALID! Please don't leave it blank!");
                go.pauseClear();
                return false;
            }

            else {
                return true;
            }

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Birthday is invalid! ");
            go.pauseClear();
            return false;
        } catch (DateTimeException e) {
            System.out.println("INVALID birthday. " + e.getMessage());
            go.pauseClear();
            return false;
        }
    }

    public boolean licenseCheck(int year, int month, int day) {
        try {
            String dt = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.of(year, month + 1, day));
            LocalDate licenseDate = LocalDate.parse(dt);

            if (licenseDate.isAfter(LocalDate.now())) {
                System.out.println("INVALID! License date cannot be issued in future!");
                go.pauseClear();
                return false;
            }

            else {
                return true;
            }

        } catch (DateTimeException e) {
            System.out.println("INVALID license date. " + e.getMessage());
            go.pauseClear();
            return false;
        }
    }

    public boolean licenseNumCheck(String licenseNum) { // this method will check if there's existing license number on
                                                        // the table
        dbConnect();
        try {
            rs = stmt.executeQuery("SELECT licensenumber from policyholder WHERE licensenumber = '" + licenseNum + "'");

            if (rs.isBeforeFirst()) {
                System.out.println("INVALID! License number is existing!");
                go.pauseClear();
                return false;
            }

            else {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            go.pauseClear();
            return false;
        }
    }

    public int searchAccount(int searchAcc) { // this method will search the id of policy holder
        dbConnect();
        try {
            rs = stmt.executeQuery("SELECT id from policyholder WHERE id = "
                    + searchAcc + " and policy is NULL"); // search the account number from query

            if (rs.next() == true) {
                return rs.getInt(1);
            }

        } catch (SQLException e) { // handles errors
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public boolean vehicleCount(int vehicleNum) {
        if (vehicleNum <= 0) {
            System.out.println("INVALID! Input cannot be zero/negative!");
            go.pauseClear();
            return false;
        } else {
            return true;
        }
    }

    public boolean date(int year, int month, int day) { // this method checks for date if it is valid. Ex. feb 31 isn't
                                                        // valid
        try {
            String dt = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.of(year, month + 1, day));
            LocalDate givenDate = LocalDate.parse(dt);

            if (String.valueOf(year).length() != 4) {
                System.out.println("INVALID! Year is incorrect!");
                go.pauseClear();
                return false;
            }

            if (givenDate.isBefore(LocalDate.now())) { // checks if user input date is not later than the current date
                                                       // today
                System.out.println("INVALID! You must put a date later than the current day!");
                go.pauseClear();
                return false;
            } else {
                return true;
            }
        } catch (DateTimeException e) {
            System.out.println("INVALID date! " + e.getMessage());
            go.pauseClear();
            return false;
        }
    }

    public boolean dateRange(int month, int day, int year, int policyID) { //this method checks whether the date is in range of the policy
        dbConnect();
        boolean validated = false;
        try {
            String dtExpiryDate, dtEffectDate, dtNewExpiry;
            rs = stmt.executeQuery("SELECT MONTH (effectdate), DAY (effectdate), YEAR (effectdate)," +
                    " MONTH (expiredate), DAY (expiredate), YEAR (expiredate) from policy WHERE policynumber = "
                    + policyID);

            while (rs.next()) {

                dtEffectDate = DateTimeFormatter.ISO_LOCAL_DATE
                        .format(LocalDate.of(rs.getInt(3), rs.getInt(1), rs.getInt(2)));
                LocalDate effectDate = LocalDate.parse(dtEffectDate);

                dtExpiryDate = DateTimeFormatter.ISO_LOCAL_DATE
                        .format(LocalDate.of(rs.getInt(6), rs.getInt(4), rs.getInt(5)));
                LocalDate oldExpiry = LocalDate.parse(dtExpiryDate);

                dtNewExpiry = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.of(year, month, day));
                LocalDate newExpiry = LocalDate.parse(dtNewExpiry);

                if (newExpiry.isBefore(effectDate)) {
                    System.out.println(
                            "INVALID! Given date must be within the range of policy date. \nTry again.");
                    validated = false;
                } else if (newExpiry.isAfter(oldExpiry)) {
                    System.out.println("INVALID! Given date must be within the range of policy date. \nTry again.");
                    validated = false;
                }

                else {
                    validated = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("INVALID! " + e.getMessage());
            validated = false;
        }
        return validated;

    }

    public boolean searchPolicyID(String query) { // search policynumber in policy table, then returns true if there is
                                                  // policynumber on the given input by the user
        dbConnect();
        try {

            rs = stmt.executeQuery(query); // search the account
            // number from query

            if (rs.next() == true) {
                return true;
            }

        } catch (SQLException e) { // handles errors
            System.out.println(e);
        }
        return false;

    }

    public boolean accidentDateCheck (String accidentDate, int policyID){ //this method checks if given date is proper
        int firstDash, secondDash, year, month, day;
        boolean validated = false;

        try {
        firstDash = accidentDate.indexOf("-", 0); //gets the index for the first dash in the string
        secondDash = accidentDate.indexOf("-", firstDash + 1);
        year = Integer.parseInt(accidentDate.substring(0, firstDash)); //trims the string to get just the year only
        month = Integer.parseInt(accidentDate.substring(firstDash + 1, secondDash)); //trims to get the month
        day = Integer.parseInt(accidentDate.substring(secondDash + 1)); //for day. Note that while trimming, we also converting it to string

        String dt = DateTimeFormatter.ISO_LOCAL_DATE.format(LocalDate.of(year, month, day)); //formatter for date from the trimmed strings
        LocalDate.parse(dt); //validates if the date is real

        validated = dateRange(month, day, year, policyID); //gets the value from a boolean method which checks
                                                            //if given date is in range of policy date

        } catch (StringIndexOutOfBoundsException e){ //catching errors, also returns false because date is not proper
            System.out.println("Invalid date. " + e.getMessage());
            validated = false;
        }
         catch (DateTimeException e){
            System.out.println("Invalid date. " + e.getMessage());
            validated = false;
        }
        return validated;

    }

}
