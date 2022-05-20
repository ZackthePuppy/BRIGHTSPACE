public class Automobile {
    private String make, color;

    /**
     * @param make the make to set
     */
    public void setMake(String make) { //gets the value from passed parameters whenever the main method calls this
        this.make = make;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) { //same as the above, but this is for colors value
        this.color = color;
    }

    public Automobile printColor() { //prints the color if the main method calls this method
        System.out.print("\n" + color + " ");
        return this; //return the execution from the main method, since it is an object
    }

    public Automobile printMake() { //same as the print color, but it is for make/model
        System.out.println(make + "\n");
        return this;
    }

}