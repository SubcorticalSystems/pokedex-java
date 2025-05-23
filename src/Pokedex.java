import javax.swing.*;//for swing utilities
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Pokedex {
    final String pokeFile = "resources/pokemon.csv";
    static ArrayList<Pokemon> nationalDex = new ArrayList<>();//Use an ArrayList for the National Pokédex since it can continue to grow
    static ArrayList<Pokemon> placeholderDex = new ArrayList<>();//Used to store Pokémon if adjustments need to be made from National Dex compared to a Regional dex
    //Use Arrays for Regional Pokémon and Regional Pokédex since they are finalized with fixed quantities
    //the "gen___" arrays consist of the Pokémon introduced in that region,
    //and may differ from the total Pokémon in that region's Pokédex.
    static Pokemon[] genOne = new Pokemon[151];
    static Pokemon[] kantoDex = new Pokemon[151];
    static Pokemon[] genTwo = new Pokemon[100];
    static Pokemon[] johtoDex = new Pokemon[256];
    static Pokemon[] genThree = new Pokemon[135];
    static Pokemon[] hoennDex = new Pokemon[211];
    static Pokemon[] genFour = new Pokemon[107];
    static Pokemon[] sinnohDex = new Pokemon[210];
    //main method
    public static void main(String[] args) throws IOException {
        //Creates a new pokedex Object to store all the pokemon into
        Pokedex pokedex = new Pokedex();
        //reads from csv to populate national dex
        pokedex.readCSV();

        //Creates Region's Pokémon
        pokedex.createGenOne();
        pokedex.createGenTwo();
        pokedex.createGenThree();
        pokedex.createGenFour();

        //Creates Regional Dex
        pokedex.createKantoDex();
        pokedex.createJohtoDex();
        pokedex.createHoennDex();
        pokedex.createSinnohDex();

        //Calls method for user input
        //UserInteraction.fetchUserInput();
         // example method to populate dex arrays

                SwingUtilities.invokeLater(() -> {
                    new MainMenuFrame().setVisible(true);
                });


    }

    //Reads from .csv to populate National Dex ArrayList
    private void readCSV() throws IOException {
        BufferedReader reader = null;
        String line;
        try{
            reader = new BufferedReader(new FileReader(pokeFile));
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                for(int i = 0; i < data.length; i+= 10){
                    Pokemon pokemon = getPokemon(i, data);
                    nationalDex.add(pokemon);
                }}}catch(FileNotFoundException e) {
            throw new RuntimeException(e);
        }finally{
            reader.close();
            //System.out.println(nationalDex);
        }
    }
//prints dex for bug testing
    private static <T> void printPokedex(T[] pokedex) {
        System.out.println(Arrays.toString(pokedex)
                .replace("[", "")
                .replace("]", "")
                .replace(", ", ""));
    }
//recieves input from readCSV in order to create a Pokemon Object
    private Pokemon getPokemon(int i, String[] data) {
        //uses i plus a number to adjust what order the data is being put into pokemon object
        Pokemon pokemon = new Pokemon();
        pokemon.setName(data[i]);
        pokemon.setDexNum(Integer.parseInt(data[i+1]));
        pokemon.setTypeOne(data[i + 2]);
        pokemon.setTypeTwo(data[i + 3]);
        pokemon.setHp(Integer.parseInt((data[i +4])));
        pokemon.setAtk(Integer.parseInt(data[i +5]));
        pokemon.setDef(Integer.parseInt(data[i +6]));
        pokemon.setSpAtk(Integer.parseInt(data[i +7]));
        pokemon.setSpDef(Integer.parseInt(data[i +8]));
        pokemon.setSpeed(Integer.parseInt(data[i +9]));
        return pokemon;
    }

    private void populateRegionalDex(int[] dexNumsRequired, Pokemon[] dex){
        Pokemon target;//creates a new object to temporarily store a national pokemon info
        try {
            for (int i = 0; i < dexNumsRequired.length; i++) {
                target = nationalDex.get(dexNumsRequired[i] - 1);//fills
                new Pokemon(target);//sends that targeted pokemon to be copied and put in placeholder array
                dex[i] = placeholderDex.get(i); //fills regional dex array
                dex[i].setDexNum(i + 1); //account for zero-index of array
            }
        }catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(dexNumsRequired.length);
        }
        //makes sure that the dex is cleared for when it is reused by next Generation's Pokedex
        placeholderDex.clear();
    }

    //Kanto Region #1-151
    private void createGenOne() {
        for(int i = 0; i <= 150; i++){
            genOne[i] = nationalDex.get(i);
        }

    }
    private void createKantoDex() {
        for(int i = 0; i < kantoDex.length; i++){
            kantoDex[i] = nationalDex.get(i);
        }

    }

    //Johto Region #152-251 but +5 in Johto dex for evolutions added in generation 4
    private void createGenTwo() {
        int counter = 0;
        for(int i = 151; i < 251; i++){
            genTwo[counter] = nationalDex.get(i);
            counter++;
        }
    }

    private void createJohtoDex() throws IOException {
        //all required dex numbers using national dex numbers but in order of regional dex placements
        int[] johtoDexNumsRequired = {152,153,154,155,156,157,158,159,160,16,17,18,21,22,163,164,19,20,161,162,
                172,25,26,10,11,12,13,14,15,165,166,167,168,74,75,76,41,42,169,173,35,36,174,39,40,175,176,27,28,23,24,206,179,180,181,
                194,195,92,93,94,201,95,208,69,70,71,187,188,189,46,47,60,61,62,186,129,130,118,119,79,80,199,43,44,45,182,96,97,63,64,65,
                132,204,205,29,30,31,32,33,34,193,469,191,192,102,103,185,202,48,49,123,212,127,214,109,110,88,89,81,82,100,101,190,424,209,
                210,37,38,58,59,234,183,184,50,51,56,57,52,53,54,55,66,67,68,236,106,107,237,203,128,241,240,126,238,124,239,125,122,235,83,177,178,211,
                72,73,98,99,213,120,121,90,91,222,223,224,170,171,86,87,108,463,114,465,133,134,135,136,196,197,116,117,230,207,225,220,221,473,216,
                217,231,232,226,227,84,85,77,78,104,105,115,111,112,198,228,229,218,219,215,200,137,233,113,242,131,138,139,140,141,142,143,1,2,3,4,5,6,
                7,8,9,144,145,146,243,244,245,147,148,149,246,247,248,249,250,150,151,251
        };

        populateRegionalDex(johtoDexNumsRequired,johtoDex);
    }

    //Hoenn Region #252-386
    private void createGenThree(){
        int counter = 0;
        for(int i = 251; i < 386; i++){
            genThree[counter] = nationalDex.get(i);
            counter++;
        }
    }
    private void createHoennDex(){
        int[] hoennDexNumsRequired = {
                252,253,254,255,256,257,258,259,260,261,262,263,264,265,266,267,268,269,270,
                271,272,273,274,275,276,277,278,279,280,281,282,475,283,284,285,286,287,288,289,
                63,64,65,290,291,292,293,294,295,296,297,118,119,129,130,298,183,184,
                74,75,76,299,476,300,301,41,42,169,72,73,302,303,304,305,306,66,67,68,307,
                308,309,310,311,312,81,82,462,100,101,313,314,43,44,45,182,84,85,406,
                315,407,316,317,318,319,320,321,322,323,218,219,324,88,89,109,110,325,
                326,27,28,327,227,328,329,330,331,332,333,334,335,336,337,338,339,340,341,342,
                343,344,345,346,347,348,174,39,40,349,350,351,120,121,352,
                353,354,355,356,477,357,433,358,359,37,38,172,25,26,54,55,360,202,
                177,178,203,231,232,127,214,111,112,464,361,362,478,363,364,365,366,367,
                368,369,222,170,171,370,116,117,230,371,372,373,374,375,376,377,378,379,380,
                381,382,383,384,385,386
        };
        //System.out.println(hoennDexNumsRequired.length);
        populateRegionalDex(hoennDexNumsRequired, hoennDex);
    }

    //#387-493
        private void createGenFour(){
        int counter = 0;
        for(int i = 386; i < 493; i++){
            genFour[counter] = nationalDex.get(i);
            counter++;
        }
            //printPokedex(genFour);
        }

        private void createSinnohDex(){
        //#479 rotom has multiple forms
            //412 and 413 have three versions each
            //gastrodon at 423 m&f count as two pokemon same with 422 shellos
            //487 has two forms
            /*
            int[] sinnohDexNumsRequired = {
                    387,388,389,390,391,392,393,394,395,396,397,398,399,400,401,402,403,404,405,63,64,65,129,130,
                    406,315,407,41,42,169,74,75,76,95,208,408,409,410,411,66,67,68,54,55,412,412,413,
                    413,414,265,266,267,268,269,415,416,417,418,419,420,421,422,422,423,214,190,424,425,
                    426,427,428,92,93,94,200,429,198,430,431,432,118,119,
                    339,340,433,358,434,435,307,308,436,437,77,78,438,185,439,122,440,113,242,173,35,36,
                    441,172,25,26,163,164,442,443,444,445,446,143,201,447,448,194,195,278,279,203,449,450,
                    298,183,184,451,452,453,454,455,223,224,456,457,72,73,349,350,458,226,459,460,215,461,480,
                    481,482,483,484,490,479,207,472,299,476,280,281,282,475,108,463,133,134,135,136,196,197,470,
                    471,333,334,175,176,468,228,229,81,82,462,114,465,193,469,357,111,112,464,355,356,477,137,233,
                    474,123,212,239,125,466,240,126,467,220,221,473,361,362,478,359,487
            };

             */
            int[] sinnohDexNumsRequired = {
                    387,388,389,390,391,392,393,394,395,396,397,398,399,400,401,402,403,404,405,63,64,65,129,130,
                    406,315,407,41,42,169,74,75,76,95,208,408,409,410,411,66,67,68,54,55,412,413,414,265,266,267,268,269,
                    415,416,417,418,419,420,421,422,423,214,190,424,425,426,427,428,92,93,94,200,429,198,430,431,432,
                    118,119,339,340,433,358,434,435,307,308,436,437,77,78,438,185,439,122,440,113,242,173,35,36,
                    441,172,25,26,163,164,442,443,444,445,446,143,201,447,448,194,195,278,279,203,449,450,298,
                    183,184,451,452,453,454,455,223,224,456,457,72,73,349,350,458,226,459,460,215,461,480,481,
                    482,483,484,490,479,207,472,299,476,280,281,282,475,108,463,133,134,135,136,196,197,470,
                    471,333,334,175,176,468,228,229,81,82,462,114,465,193,469,357,111,112,464,355,356,477,137,233,
                    474,123,212,239,125,466,240,126,467,220,221,473,361,362,478,359,487
            };

            populateRegionalDex(sinnohDexNumsRequired, sinnohDex);
        }

    //#494-649

    //#650-721
    //#722-809
    //#810-905
    //#906-1025



}