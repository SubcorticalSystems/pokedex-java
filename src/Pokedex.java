import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Pokedex {
    static Pokedex pokedex = new Pokedex();
    //Use an ArrayList for the National Dex since it can continue to grow
    static ArrayList<Pokemon> nationalDex = new ArrayList<>();

    //Use Arrays for regional dex since they are finalized with fixed quantities
    Pokemon[] kantoDex = new Pokemon[151];


    //main method
    public static void main(String[] args) {
        pokedex.createKantoPokemon();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome Trainer! Please select from the following:");
        System.out.println("1. Enter Kanto Dex");
        int n = scanner.nextInt();
        String choice;

        if(n == 1){
            System.out.println("P [Prints entire dex] " + "||" + " S [Search the dex for specific pokemon]" );
            choice = scanner.next().toLowerCase();
            if(choice.equals("p")){
                pokedex.printKantoDex();
            } else if(choice.equals("s")) {

            }

        }



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
                if (pokemon.getDexNum() == target) {
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