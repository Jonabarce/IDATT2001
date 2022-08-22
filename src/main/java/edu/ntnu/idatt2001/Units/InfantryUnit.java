package edu.ntnu.idatt2001.Units;

import java.util.Locale;

public class InfantryUnit extends Unit {

    /**
     * Constructor for infantry unit
     * @param name the name of the unit
     * @param health units health
     * @param attack total attack for the unit
     * @param armour total armour for the unit
     */
    public InfantryUnit(String name, int health, int attack, int armour){
        super(name,health,attack,armour);
    }
    /**
     * Simplified constructor for infantry unit
     * @param name the name of the unit
     * @param health units health
     */
    public InfantryUnit(String name,int health){
        super(name,health,15,10);
    }

    /**
     * Attack bonus for infantry unit if it is attacking by another unit
     * @param terrain the terrain the battle is being held on
     * @return bonus 3 if the terrain is forest,
     * if the terrain is not forest the bonus is 2
     */
    @Override
    public int getAttackBonus(String terrain){
        int attackBonus;
        if(terrain.toUpperCase(Locale.ROOT).equalsIgnoreCase("forest")){
            attackBonus = 3;
        }else{
            attackBonus = 2;
        }
        return attackBonus;
    }

    /**
     * Resist bonus for infantry unit if it is being attacked by another unit
     * @param terrain the terrain the battle is being held on
     * @return bonus 2 if the terrain is forest,
     * if the terrain is not forest the bonus is 1
     */
    @Override
    public int getResistBonus(String terrain) {
        int resistBonus;
        if (terrain.toUpperCase(Locale.ROOT).equalsIgnoreCase("forest")) {
            resistBonus = 2;
        } else {
            resistBonus = 1;
        }
        return resistBonus;
    }

}
