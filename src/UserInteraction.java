import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
//import java.util.ArrayList;
public class UserInteraction {
    public static void fetchUserInput() {
        System.out.println("Welcome to the Pokedex! Choose From The Following.\n" +
                "Would you like to print the National Pokedex [1]\n" +
                        "Search a Regional Pokedex [2]");

        nationalDexMenu();
    }

    //menu1 will display the first layer of responses for either printing
    //the national pokedex or opening menu2 which holds Regional Pokedexs
    static ArrayList<Integer> viableInputs = new ArrayList<>();//can be filled with acceptable inputs for each menu
        private static void nationalDexMenu() {
            try {
                Scanner scanner = new Scanner(System.in);
                int num = scanner.nextInt();
                viableInputs.add(1);
                viableInputs.add(2);
                if (viableInputs.contains(num) && num == 1) {
                    printPokedex(Pokedex.nationalDex.toArray());
                } else if (viableInputs.contains(num) && num == 2) {
                    regionalDexMenu();
                } else {
                    System.out.println("Input Not Valid. Please Try Again");
                    nationalDexMenu();
                }
            }catch (Exception InputMismatchException) {
                System.out.println("Input Not Valid. Please Enter Number");
                nationalDexMenu();
            }
    }
    private static void regionalDexMenu() {
            String pokedexName = "";
            System.out.println("Please select which Regional Pokedex you would like");
            System.out.println("Kanto Pokedex [1] ||  Johto Pokedex [2] || Hoenn Pokedex [3]");
            try{
                Scanner scanner = new Scanner(System.in);
                int num = scanner.nextInt();
                for (int i = 1; i <= 9; i++) {
                    viableInputs.add(i);
                }
                if (viableInputs.contains(num)) {
                    switch (num) {
                        case 1:
                            pokedexName = "Kanto Pokedex";
                            printOrSearchMenu(Pokedex.kantoDex, pokedexName);
                            break;
                        case 2:
                            pokedexName = "Johto Pokedex";
                            printOrSearchMenu(Pokedex.johtoDex, pokedexName);
                            break;
                        case 3:
                            pokedexName = "Hoenn Pokedex";
                            printOrSearchMenu(Pokedex.hoennDex, pokedexName);
                            break;
                    }
                }
            }catch (Exception InputMismatchException) {
                System.out.println("Input Not Valid. Please Enter Number");
                regionalDexMenu();
            }

    }

    private static void printOrSearchMenu(Pokemon[] pokedex, String pokedexName) {
            System.out.println("Search " + pokedexName + " [1] || Print " + pokedexName + " [2]");
            try{
                Scanner scanner = new Scanner(System.in);
                int num = scanner.nextInt();
                viableInputs.add(1);
                viableInputs.add(2);
                if (viableInputs.contains(num) && num == 1) {
                    searchDex(pokedex, pokedexName);
                } else if (viableInputs.contains(num) && num == 2) {
                    printPokedex(pokedex);
                }
            } catch (Exception InputMismatchException) {
                System.out.println("Input Not Valid. Please Enter Number");
                printOrSearchMenu(pokedex, pokedexName);
            }
    }

    private static <T> void printPokedex(T[] pokedex){
        System.out.println(Arrays.toString(pokedex)
                .replace("[", "")
                .replace("]", "")
                .replace(", ", ""));
    }

    private static void searchDex(Pokemon[] dexSearch, String dexName) {
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().toLowerCase();
        for (int i = 0; i < dexSearch.length; i++) {
            if (dexSearch[i].getName().toLowerCase().contains(choice)) {
                Pokemon pokemon;
                pokemon = dexSearch[i];
                //fix this
                new MyFrame();
                //new MyFrame(pokemon, dexName);
            }
        }


    }
}

