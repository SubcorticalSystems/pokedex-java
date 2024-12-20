public class Pokedex {
    public static void main(String[] args) {
        Pokemon bulbasaur = new Pokemon(0001, "Bulbasaur", "Grass/Poison", 45,49,49,65,65,45);
        Pokemon ivysaur = new Pokemon(0002,"Ivysaur", "Grass/Poison", 60,62,63,80,80,60);
        Pokemon venusaur = new Pokemon(0003,"Venusaur", "Grass/Poison", 80,82,83,100,100,80);
        Pokemon[] pokedex = {bulbasaur, ivysaur, venusaur};
        /*
        Pokemon[] pokedex = new Pokemon[3];
        pokedex[0] = bulbasaur;
        pokedex[1] = ivysaur;
        pokedex[2] = venusaur;
        */
        for(Pokemon pokemon : pokedex) {
            System.out.println(pokemon);
        }

    }

}
