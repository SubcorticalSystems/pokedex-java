public class Pokemon {

        int dexNum;
        String name;
        String type;
        int hp;
        int atk;
        int def;
        int spAtk;
        int spDef;
        int speed;

    Pokemon(int dexNum, String name, String type, int hp, int atk, int def, int spAtk, int spDef, int speed) {
        this.dexNum = dexNum;
        this.name = name;
        this.type = type;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.spAtk = spAtk;
        this.spDef = spDef;
        this.speed = speed;

        //add all this data to the national dex
        Pokedex.nationalDex.add(this);
    }

    @Override
    public String toString() {
        return "\n#" + dexNum + " " + name + "\n" + type
                + "\nStats:\n" + "HP(" + hp + ") " + "Attack(" + atk + ") "
                + "Defense(" + def + ") " + "Special Attack(" + spAtk + ") "
                + "Special Defense(" + spDef + ") " + "Speed(" + speed + ")\n";
     }



}
