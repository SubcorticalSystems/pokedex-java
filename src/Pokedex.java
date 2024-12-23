import java.util.ArrayList;
import java.util.Arrays;

public class Pokedex {

    //Use an ArrayList for the National Dex since it can continue to grow
    static ArrayList<Pokemon> nationalDex = new ArrayList<>();

    //Use Arrays for regional dex since they are finalized with fixed quantities
    Pokemon[] kantoDex = new Pokemon[151];


    //main method
    public static void main(String[] args) {
        Pokedex pokedex = new Pokedex();
        pokedex.createKantoPokemon();
        pokedex.printKantoDex();
    }

    //Kanto Region
    void createKantoPokemon() {
        Pokemon bulbasaur = new Pokemon(0001, "Bulbasaur", "Grass/Poison", 45, 49, 49, 65, 65, 45);
        Pokemon ivysaur = new Pokemon(0002, "Ivysaur", "Grass/Poison", 60, 62, 63, 80, 80, 60);
        Pokemon venusaur = new Pokemon(0003, "Venusaur", "Grass/Poison", 80, 82, 83, 100, 100, 80);


        int target = 1;
        int j = 0;

        while (target <= 2) {
            for (Pokemon pokemon : nationalDex) {
                if (pokemon.dexNum == target) {
                    kantoDex[j] = pokemon;
                    j++;
                    target++;
                }
            }
        }
    }

    void printKantoDex(){
        System.out.println(Arrays.toString(kantoDex)
                .replace("[", "")
                .replace("]", "")
                .replace(", ", ""));
    }

    //Johto Region


}