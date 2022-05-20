public class VowelCounter {
    private String [] capitalVowel = {"A", "E", "I", "O", "U"};
    private String [] smallVowel = {"a", "e", "i", "o", "u"};
    private int vowelCount [][] = new int [5][3];

    public void processLine(String value){
        for (int x=0; x<value.length(); x++){

            for (int y=0; y<5; y++){
                String single = Character.toString(value.charAt(x)); //gets the single character depending from the loop index, then converts it to string
                if (capitalVowel[y].equals(single) ){ //for capital letters if the single character is a capital vowel
                    vowelCount[y][0] += 1; //adds a count in given index, then index0(this is where I store my capital)
                    vowelCount[y][2] += 1; //index 2 is for total count for the letter
                }
                else if (smallVowel[y].equals((single))){
                    vowelCount[y][1] += 1; //stores the small letter in index 1
                    vowelCount[y][2] += 1; //adds the count again for total counter
                }
            }

        }
    }

    public void printSummary (){
        System.out.println("\nVOWEL COUNTER"); //prints the total counter
        for (int x=0; x<5; x++){ //for loop for getting all the index for each value to print it
            System.out.println(capitalVowel[x] + ": " + vowelCount[x][1] + " lowercase, " + 
            vowelCount[x][0] + " uppercase, " + vowelCount[x][2] + " total");
        }
    }
}
