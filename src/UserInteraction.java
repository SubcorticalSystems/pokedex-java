
/*
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

    static ArrayList<Integer> viableInputs = new ArrayList<>();

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
        } catch (Exception InputMismatchException) {
            System.out.println("Input Not Valid. Please Enter Number");
            nationalDexMenu();
        }

    }

    private static <T> void printPokedex(T[] pokedex) {
        System.out.println(Arrays.toString(pokedex)
                .replace("[", "")
                .replace("]", "")
                .replace(", ", ""));
    }

    private static void regionalDexMenu() {
        String pokedexName = "";
        System.out.println("Please select which Regional Pokedex you would like");
        System.out.println("Kanto Pokedex [1] ||  Johto Pokedex [2] || Hoenn Pokedex [3]");
        try {
            Scanner scanner = new Scanner(System.in);
            int num = scanner.nextInt();
            for (int i = 1; i <= 9; i++) {
                viableInputs.add(i);
            }
            if (viableInputs.contains(num)) {
                switch (num) {
                    case 1:
                        pokedexName = "Kanto Pokedex";
                        setUpRegionalDexGUI(Pokedex.kantoDex, pokedexName);
                        break;
                    case 2:
                        pokedexName = "Johto Pokedex";
                        setUpRegionalDexGUI(Pokedex.johtoDex, pokedexName);
                        break;
                    case 3:
                        pokedexName = "Hoenn Pokedex";
                        setUpRegionalDexGUI(Pokedex.hoennDex, pokedexName);
                        break;
                }
            } else{
                System.out.println("Input Not Valid. Please Try Again");
                regionalDexMenu();
            }
        } catch (Exception InputMismatchException) {
            System.out.println("Input Not Valid. Please Enter Number");
            regionalDexMenu();
        }

    }

    private static void setUpRegionalDexGUI(Pokemon[] dexContents, String dexName) {

        new RegionalDexFrame(dexName, dexContents).setVisible(true);

    }
}

 */