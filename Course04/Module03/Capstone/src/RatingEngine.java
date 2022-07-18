public class RatingEngine {
    
    public double calculate (double vehiclePrice, double year, int driverYear) { //method for calculating the vehicle premium, then returns it
        double vehiclePriceFactor = 0, premium;

        //Conditions for the vehicle price factor
        if (year <= 1) {
            vehiclePriceFactor = 0.01;
        } else if (year <= 3) {
            vehiclePriceFactor = 0.008;
        } else if (year <= 5) {
            vehiclePriceFactor = 0.007;
        } else if (year <= 10) {
            vehiclePriceFactor = 0.006;
        } else if (year <= 15) {
            vehiclePriceFactor = 0.004;
        } else if (year <= 20) {
            vehiclePriceFactor = 0.002;
        } else if (year <= 40) {
            vehiclePriceFactor = 0.001;
        }

        premium = ((vehiclePrice * vehiclePriceFactor) + ((vehiclePrice/100)/driverYear)); //computation of premium
        return Math.round (premium * 100.0) / 100.0 ; //rounds of the premium to prevent infinite decimals
    }

}
