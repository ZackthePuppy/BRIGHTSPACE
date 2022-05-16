public class MapleTreeDriver {
    private double height;
    private String plantDate, heightDate;

    public void plant(String plantDate){
        this.plantDate = plantDate;
    }

    public void germinate(double height, String heightDate){
        this.heightDate = heightDate;
        this.height = height;
    }

    public void dumpData (){
        System.out.println("\nPlant Date: " + plantDate);
        System.out.println("Germinate Date: " + heightDate);
        System.out.println("Initial Height: " + height);
    }
}
