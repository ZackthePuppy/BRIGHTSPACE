public class Salaried extends Employee {

    private double weeklySalary;

    public Salaried(String name, String sss, int birthMonth, int birthWeek, double salary) { // same in hourly class,
                                                                                             // gets the name, sss, etc.
                                                                                             // in super class, but gets
                                                                                             // the weeklySalary value
                                                                                             // from here
        super(name, sss, birthMonth, birthWeek);
        this.weeklySalary = salary;
    }

    public double getEarnings() {
        return this.weeklySalary; //returns weeklySalary
    }

}
