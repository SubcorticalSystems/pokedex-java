import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Pokedex {
    final String pokeFile = "resources/pokemon.csv";
    //Use an ArrayList for the National Pokédex since it can continue to grow
    static ArrayList<Pokemon> nationalDex = new ArrayList<>();
    //Use Arrays for Regional Pokémon and Regional dex since they are finalized with fixed quantities
    static Pokemon[] genOne = new Pokemon[151];
    static Pokemon[] kantoDex = new Pokemon[151];
    static Pokemon[] genTwo = new Pokemon[100];
    static Pokemon[] johtoDex = new Pokemon[256];
    //main method
    public static void main(String[] args) throws IOException {
        Pokedex pokedex = new Pokedex();
        pokedex.readCSV();
        //pokedex.printNationalDex();
        pokedex.createGenOne();
        pokedex.createGenTwo();
        //pokedex.printGenTwo();
        //pokedex.printGenOne();
        //pokedex.printKantoDex();
        pokedex.printRegionalData(genTwo);
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

    private void readCSV() throws IOException {
        BufferedReader reader = null;
        String line = "";
        //use dexTracker to account for the 0 index in the arrays otherwise every dex number would be off by -1
        int dexTracker = 1;

        try{
            reader = new BufferedReader(new FileReader(pokeFile));
            while((line = reader.readLine()) != null){
                String[] data = line.split(",");
                for(int i = 0; i < data.length; i+= 10){
                    Pokemon pokemon = getPokemon(i, data, dexTracker);
                    nationalDex.add(pokemon);
                    dexTracker++;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } finally{
            reader.close();
        }
    }

    private static Pokemon getPokemon(int i, String[] data, int dexTracker) {
        Pokemon pokemon = new Pokemon();
        pokemon.setDexNum(dexTracker);
        pokemon.setName(data[i]);
        pokemon.setTypeOne(data[i + 1]);
        pokemon.setTypeTwo(data[i + 2]);
        pokemon.setHp(Integer.parseInt((data[i +3])));
        pokemon.setAtk(Integer.parseInt(data[i +4]));
        pokemon.setDef(Integer.parseInt(data[i +5]));
        pokemon.setSpAtk(Integer.parseInt(data[i +6]));
        pokemon.setSpDef(Integer.parseInt(data[i +7]));
        pokemon.setSpeed(Integer.parseInt(data[i +8]));
        return pokemon;
    }

    //Kanto Region #1-151
    private void createGenOne() {
        for(int i = 0; i <= 150; i++){
            genOne[i] = nationalDex.get(i);
        }

    }
    private void createKantoDex() {

    }

    //Johto Region #152-251
    private void createGenTwo() {
        int counter = 0;
        for(int i = 151; i < 251; i++){
            genTwo[counter] = nationalDex.get(i);
            counter++;
        }
    }
    //we need an algorithm that will search through an array and see if the dexNum is needed. If so we pull it and add it to our array.
    private void createJohtoDex() {


    }
    //Hoenn Region #252-386
    //#387-493
    //#494-649
    //#650-721
    //#722-809
    //#810-905
    //#906-1025



}