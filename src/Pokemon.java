public class Pokemon {

        private int dexNum;
        private String name;
        private String typeOne;
        private String typeTwo;
        private int hp;
        private int atk;
        private int def;
        private int spAtk;
        private int spDef;
        private int speed;

    //Empty parameter constructor for reading from csv
    Pokemon(){
        this.dexNum = 0;
        this.name = "";
        this.typeOne = "";
        this.typeTwo = "";
        this.hp = 0;
        this.atk = 0;
        this.def = 0;
        this.spAtk = 0;
        this.spDef = 0;
        this.speed = 0;

    }

//manual creating Pokémon objects
    Pokemon(int dexNum, String name, String typeOne, String typeTwo, int hp, int atk, int def, int spAtk, int spDef, int speed) {
        this.setDexNum(dexNum);
        this.setName(name);
        this.setTypeOne(typeOne);
        this.setTypeTwo(typeTwo);
        this.setHp(hp);
        this.setAtk(atk);
        this.setDef(def);
        this.setSpAtk(spAtk);
        this.setSpDef(spDef);
        this.setSpeed(speed);
        //add 'this' data to the national dex upon instantiation if necessary
        //Pokedex.nationalDex.add(this);
}

    public int getDexNum() {return dexNum;}
    public void setDexNum(int dexNum) {this.dexNum = dexNum;}

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public String getTypeOne() {return typeOne;}
    public void setTypeOne(String typeOne) {this.typeOne = typeOne;}

    public String getTypeTwo() {return typeTwo;}
    public void setTypeTwo(String typeTwo) {this.typeTwo = typeTwo;}

    public int getHp() {return hp;}
    public void setHp(int hp) {this.hp = hp;}

    public int getAtk() {return atk;}
    public void setAtk(int atk) {this.atk = atk;}

    public int getDef() {return def;}
    public void setDef(int def) {this.def = def;}

    public int getSpAtk() {return spAtk;}
    public void setSpAtk(int spAtk) {this.spAtk = spAtk;}

    public int getSpDef() {return spDef;}
    public void setSpDef(int spDef) {this.spDef = spDef;}

    public int getSpeed() {return speed;}
    public void setSpeed(int speed) {this.speed = speed;}

    @Override
    public String toString() {
        return "\n#" + dexNum + " " + name + "\n" + typeOne + " " + typeTwo
                + "\nBase Stats:\n" + "HP(" + hp + ") " + "Attack(" + atk + ") "
                + "Defense(" + def + ") " + "Special Attack(" + spAtk + ") "
                + "Special Defense(" + spDef + ") " + "Speed(" + speed + ")\n";
     }
}
