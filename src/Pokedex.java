import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Pokedex {
    final String pokeFile = "resources/pokemon.csv";
    static ArrayList<Pokemon> nationalDex = new ArrayList<>(); //Use an ArrayList for the National Pokédex since it can continue to grow
    //Use Arrays for Regional Pokémon and Regional dex since they are finalized with fixed quantities
    //the "gen..." arrays consist of the Pokémon introduced in that region, and may differ from the total Pokémon in that region's dex.
    static Pokemon[] genOne = new Pokemon[151];
    static Pokemon[] kantoDex = new Pokemon[151];
    static Pokemon[] genTwo = new Pokemon[100];
    static Pokemon[] johtoDex = new Pokemon[256];
    //main method
    public static void main(String[] args) throws IOException {
        Pokedex pokedex = new Pokedex();
        //reads from csv to populate national dex
        pokedex.readCSV();
        //Creates Region's Pokémon
        pokedex.createGenOne();
        pokedex.createGenTwo();

        //Creates Regional Dex
        pokedex.createKantoDex();
        pokedex.createJohtoDex();

        //pokedex.printRegionalData(kantoDex);
        //pokedex.printRegionalData(johtoDex);
        //Calls method for user input
        pokedex.userInput();
    }
    private void userInput() {
        Scanner scanner = new Scanner(System.in);
        int input;
        int counter = 0;
        System.out.println("Welcome to the Pokedex");
        System.out.println("Would you like to search the National Dex or a Regional Dex?");
        System.out.println("1. National Dex" + " 2. Regional Dex" + " Q. to quit");
        input = scanner.nextInt();
        if (input == 1) {
            System.out.println("1. Search the National Dex" + " 2. Print the National Dex");
            input = scanner.nextInt();
            if(input == 2){
                printNationalDex();
            }
        } else if (input == 2){
            System.out.println("1. Kanto Dex" + " 2. Johto Dex");
            input = scanner.nextInt();
            if(input == 1){
                System.out.println("1. Search the Kanto Dex" + " 2. Print the Kanto Dex");
                input = scanner.nextInt();
                if(input == 1){
                    //nothing yet
                } else if (input == 2) {
                    printRegionalData(kantoDex);
                }
            } else if (input == 2){
                System.out.println("1. Search the Johto Dex" + " 2. Print the Johto Dex");
                input = scanner.nextInt();
                if (input == 1){
                    //nothing yet
                } else if (input == 2) {
                    printRegionalData(johtoDex);
                }
            }
        }


    }

    private void printRegionalData(Pokemon[] choice){
        System.out.println(Arrays.toString(choice)
                .replace("[", "")
                .replace("]", "")
                .replace(", ", ""));
    }

    private void printNationalDex(){
        System.out.println(Arrays.toString(nationalDex.toArray())
                .replace("[", "")
                .replace("]", "")
                .replace(", ", ""));
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
        }
    }

    private static Pokemon getPokemon(int i, String[] data) {
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

    //Johto Region #152-251
    private void createGenTwo() {
        int counter = 0;
        for(int i = 151; i < 251; i++){
            genTwo[counter] = nationalDex.get(i);
            counter++;
        }
    }

    private void createJohtoDex() throws IOException {
        Pokemon target;
        //all required dex numbers using national dex numbers but in order of regional dex placements
        int[] dexNumsRequired  = {152,153,154,155,156,157,158,159,160,16,17,18,21,22,163,164,19,20,161,162,
                172,25,26,10,11,12,13,14,15,165,166,167,168,74,75,76,41,42,169,173,35,36,174,39,40,175,176,27,28,23,24,206,179,180,181,
                194,195,92,93,94,201,95,208,69,70,71,187,188,189,46,47,60,61,62,186,129,130,118,119,79,80,199,43,44,45,182,96,97,63,64,65,
                132,204,205,29,30,31,32,33,34,193,469,191,192,102,103,185,202,48,49,123,212,127,214,109,110,88,89,81,82,100,101,190,424,209,
                210,37,38,58,59,234,183,184,50,51,56,57,52,53,54,55,66,67,68,236,106,107,237,203,128,241,240,126,238,124,239,125,122,235,83,177,178,211,
                72,73,98,99,213,120,121,90,91,222,223,224,170,171,86,87,108,463,114,465,133,134,135,136,196,197,116,117,230,207,225,220,221,473,216,
                217,231,232,226,227,84,85,77,78,104,105,115,111,112,198,228,229,218,219,215,200,137,233,113,242,131,138,139,140,141,142,143,1,2,3,4,5,6,
                7,8,9,144,145,146,243,244,245,147,148,149,246,247,248,249,250,150,151,251};
        for(int i = 0; i < dexNumsRequired.length; i++){
            target = nationalDex.get(dexNumsRequired[i]-1);
            johtoDex[i] = target;
        }
    }

    //Hoenn Region #252-386


    //#387-493


    //#494-649
    //#650-721
    //#722-809
    //#810-905
    //#906-1025



}