import java.util.Arrays;
import java.util.Scanner;

public class UserInteraction {
    public static void fetchUserInput(){
        userInput();
    }

    private static void userInput() {
        Scanner scanner = new Scanner(System.in);
        int input;
        System.out.println("Welcome to the Pokedex");
        System.out.println("Would you like to search the National Dex or a Regional Dex?");
        System.out.println("1. National Dex" + " 2. Regional Dex" + " Q. to quit");
        input = scanner.nextInt();
        if (input == 1) {
            System.out.println("1. Search the National Dex" + " 2. Print the National Dex");
            input = scanner.nextInt();
            if(input == 1){
                //nothing yet
            } else if(input == 2){
                //printNationalDex();
                printPokedex(Pokedex.nationalDex.toArray());
            }
        } else if (input == 2) {
            String dexName;
            System.out.println("1. Kanto Pokédex" + " 2. Johto Pokédex" + " 3. Hoenn Pokédex");
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    System.out.println("1. Search the Kanto Dex" + " 2. Print the Kanto Dex" +
                            " 3. See what Pokemon Originated from this Generation");
                    input = scanner.nextInt();
                    if (input == 1) {
                        dexName = "Kanto Pokédex";
                       searchDex(Pokedex.kantoDex, dexName);
                    } else if (input == 2) {
                        printPokedex(Pokedex.kantoDex);
                    } else if (input == 3) {
                        printPokedex(Pokedex.genOne);
                    }
                    break;
                case 2:
                    System.out.println("1. Search the Johto Dex" + " 2. Print the Johto Dex"
                            + " 3. See what Pokemon Originated from this Generation");
                    input = scanner.nextInt();
                    if (input == 1) {
                        dexName = "Johto Pokédex";
                        searchDex(Pokedex.johtoDex, dexName);
                    } else if (input == 2) {
                        printPokedex(Pokedex.johtoDex);
                    } else if (input == 3) {
                        printPokedex(Pokedex.genTwo);
                    }
                    break;
                case 3:
                    System.out.println("1. Search the Hoenn Dex" + " 2. Print the Hoenn Dex"
                            + " 3. See what Pokemon Originated from this Generation");
                    input = scanner.nextInt();
                    if (input == 1) {
                        dexName = "Hoenn Pokédex";
                        searchDex(Pokedex.hoennDex, dexName);
                    } else if (input == 2) {
                       printPokedex(Pokedex.hoennDex);
                    } else if (input == 3) {
                        printPokedex(Pokedex.genThree);
                    }
                    break;

            }
        }
    }

    private static <T> void printPokedex(T[] pokedex){
        System.out.println(Arrays.toString(pokedex)
                .replace("[", "")
                .replace("]", "")
                .replace(", ", ""));
    }

    private static void searchDex(Pokemon[] dexSearch, String dexName) {
        //System.out.println("Hello" + Arrays.toString(dexSearch));
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toLowerCase();
        for (int i = 0; i < dexSearch.length; i++) {
            if (dexSearch[i].getName().toLowerCase().contains(choice)) {
                Pokemon pokemon;
                pokemon = dexSearch[i];
                new MyFrame(pokemon, dexName);
            }
        }


    }
}

