public class Salaried extends Employee {

    private double weeklySalary;
    public Salaried(String name, String sss, int birthMonth, int birthWeek, double salary) {
        super(name, sss, birthMonth, birthWeek);
        this.weeklySalary = salary;
    }
    
    public double getEarnings(){
        return this.weeklySalary;
    }
    
}
