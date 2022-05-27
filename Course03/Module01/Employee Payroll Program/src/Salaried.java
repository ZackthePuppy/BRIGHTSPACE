public class Salaried extends Employee {

    private double weeklySalary;

    public Salaried load(String name, String sss, int birthMonth, int birthWeek, double salary) { // same in hourly class,
                                                                                             // gets the name, sss, etc.
                                                                                             // in super class, but gets
                                                                                             // the weeklySalary value
                                                                                             // from here
        super.load(name, sss, birthMonth, birthWeek);
        this.weeklySalary = salary;
        return this;
    }

    public double getEarnings() {
        return this.weeklySalary; //returns weeklySalary
    }

}
