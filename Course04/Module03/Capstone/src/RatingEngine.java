public class RatingEngine {
    
    public double engine (double vehiclePrice, double vehiclePriceFactor, int driverYear) {
        return (vehiclePrice * vehiclePriceFactor) + ((vehiclePrice/100)/driverYear);

    }
}
