public class Pokemon {

        private int dexNum;
        private String name;
        private String type;
        private int hp;
        private int atk;
        private int def;
        private int spAtk;
        private int spDef;
        private int speed;


    Pokemon(int dexNum, String name, String type, int hp, int atk, int def, int spAtk, int spDef, int speed) {
        this.setDexNum(dexNum);
        this.setName(name);
        this.setType(type);
        this.setHp(hp);
        this.setAtk(atk);
        this.setDef(def);
        this.setSpAtk(spAtk);
        this.setSpDef(spDef);
        this.setSpeed(speed);

        //add all this data to the national dex
        Pokedex.nationalDex.add(this);
    }


    public int getDexNum() {
        return dexNum;
    }

    public void setDexNum(int dexNum) {
        this.dexNum = dexNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAtk() {
        return atk;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public int getSpAtk() {
        return spAtk;
    }

    public void setSpAtk(int spAtk) {
        this.spAtk = spAtk;
    }

    public int getSpDef() {
        return spDef;
    }

    public void setSpDef(int spDef) {
        this.spDef = spDef;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }




    @Override
    public String toString() {
        return "\n#" + dexNum + " " + name + "\n" + type
                + "\nStats:\n" + "HP(" + hp + ") " + "Attack(" + atk + ") "
                + "Defense(" + def + ") " + "Special Attack(" + spAtk + ") "
                + "Special Defense(" + spDef + ") " + "Speed(" + speed + ")\n";
     }



}
