public class Hourly extends Employee {

    private double hourlyPay, hoursWorked;

    public Hourly(String name, String sss, int birthMonth, int birthWeek, double hourlyPay, double hoursWorked) {
        super(name, sss, birthMonth, birthWeek);
        this.hourlyPay = hourlyPay;
        this.hoursWorked = hoursWorked;
    }

    public double getEarnings(){
        final int overTime = 40;
        if (this.hoursWorked > overTime){
            
            paycheck = (this.hoursWorked -= overTime) * (this.hourlyPay * .5) ; //adds the overtimed salary
            paycheck += (overTime * this.hourlyPay); //adds the regular pay 
        }

        else {
            paycheck = (this.hoursWorked * this.hourlyPay);
        }
        return paycheck;
    }
}
