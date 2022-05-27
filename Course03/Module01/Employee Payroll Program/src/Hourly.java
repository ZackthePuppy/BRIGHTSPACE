public class Hourly extends Employee {

    private double hourlyPay, hoursWorked;

    public Hourly load(String name, String sss, int birthMonth, int birthWeek, double hourlyPay, double hoursWorked) { // child
                                                                                                                  // constructor,
                                                                                                                  // gets
                                                                                                                  // the
                                                                                                                  // name,
                                                                                                                  // sss,
                                                                                                                  // birthmonth
                                                                                                                  // and
                                                                                                                  // week
                                                                                                                  // from
                                                                                                                  // super
                                                                                                                  // class
        super.load(name, sss, birthMonth, birthWeek);
        this.hourlyPay = hourlyPay;
        this.hoursWorked = hoursWorked;
        return this;
    }

    public double getEarnings() {
        final int overTime = 40; // final since no change will occur to this
        double earned; //stores the earned money here
        if (this.hoursWorked > overTime) { // if hoursWorked exceeds overtime number

            earned = (this.hoursWorked -= overTime) * (this.hourlyPay * .5); // adds the overtimed salary
            earned += (overTime * this.hourlyPay); // adds the regular pay
        }

        else {
            earned = (this.hoursWorked * this.hourlyPay); // computes earnings if no overtime occured
        }
        return earned;
    }
}
