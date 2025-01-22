import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class UserInteraction {

    public static void fetchUserInput() {
        System.out.println("Welcome to the Pokedex! Choose From The Following.\n" +
                "Would you like to print the National Pokedex [1]\n" +
                        "Search a Regional Pokedex [2]");

        menu1();
    }

    //static ArrayList<Integer> viableInputs = new ArrayList<>();

    static ArrayList<Integer> viableInputs = new ArrayList<>();
        int k = 0;
        private static void menu1 () {
            try {
                Scanner scanner = new Scanner(System.in);
                int num = scanner.nextInt();
                viableInputs.add(1);
                viableInputs.add(2);
                if (viableInputs.contains(num) && num == 1) {
                    System.out.println("Hello Menu 1 Block 1");
                    printPokedex(Pokedex.nationalDex.toArray());
                } else if (viableInputs.contains(num) && num == 2) {
                    System.out.println("Hello Menu 1 Block 2");
                    menu2();
                } else {
                    System.out.println("Input Not Valid. Please Try Again");
                    menu1();
                }
            }catch (Exception InputMismatchException) {
                System.out.println("Input Not Valid. Please Enter Number");
                menu1();
            }
    }
    private static void menu2() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        for (int i = 1; i <= 9; i++) {
            viableInputs.add(i);
        }
        if (viableInputs.contains(num)) {
            switch (num) {
                case 1:
                    System.out.println("Hello menu 2 block 1");
                    break;
                case 2:
                    System.out.println("Hello menu 2 block 2");
                    break;
                case 3:
                    System.out.println("Hello menu 2 block 3");
                    break;
            }
        }
    }

        /*
        if (viableInputs.contains(num) && num == 1) {
            System.out.println("Hello menu 2 block 1");
            //printPokedex(Pokedex.nationalDex.toArray());
        } else if (viableInputs.contains(num) && num == 2) {
            System.out.println("Hello menu 2 block 1");
            //menu2();
        } else i{
            System.out.println("Input Not Valid. Please Try Again");
            menu2();
        }
         */





    /*
    private static void userInput(){
        int input = 0;
        while(input != 1){
            Scanner scanner = new Scanner(System.in);
            input = scanner.nextInt();
            if(input != 1){
                System.out.println("Please try again");
            }

        }
        menu1();
    }

     */

    /*
    private static void userInput() {
        Scanner scanner = new Scanner(System.in);
        int input;
        System.out.println("Welcome to the Pokedex");
        System.out.println("Would you like to view the National Dex or a search a Regional Dex?");
        System.out.println("1. View National Dex" + " 2. Regional Dex" + " Q. to quit");
        input = scanner.nextInt();
        if (input == 1) {
                printPokedex(Pokedex.nationalDex.toArray());
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

     */

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

