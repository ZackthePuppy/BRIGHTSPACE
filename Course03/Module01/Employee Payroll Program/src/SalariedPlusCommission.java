public class SalariedPlusCommission extends Employee {

    private double salesWeek, commissionRate, salary;

    public SalariedPlusCommission(String name, String sss, int birthMonth, int birthWeek, double salary, double salesWeek, double commissionRate) { // same in hourly class,
                                                                                             // gets the name, sss, etc.
                                                                                             // in super class, but gets
                                                                                             // the weeklySalary value
                                                                                             // from here
        super(name, sss, birthMonth, birthWeek);
        this.salary = salary;
        this.salesWeek = salesWeek;
        this.commissionRate = commissionRate;
    }

    public double getEarnings() {
        return (this.salary) + (salesWeek * commissionRate); //return the salary plus the percentage of how much is their sales
    }

}
