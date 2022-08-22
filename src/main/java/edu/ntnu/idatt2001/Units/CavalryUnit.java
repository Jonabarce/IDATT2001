package edu.ntnu.idatt2001.Units;

import java.util.Locale;

public class CavalryUnit extends Unit {

    private boolean hasAttacked;

    /**
     * Constructor for cavalry unit
     * @param name the name of the unit
     * @param health units health
     * @param attack total attack for the unit
     * @param armour total armour for the unit
     */
    public CavalryUnit(String name,int health,int attack,int armour){
        super(name,health,attack,armour);
        this.hasAttacked = false;
    }

    /**
     * Simplefied constructor for cavalry unit
     attack is set to be 20 and the armour is set to be 12
     * @param name the name of the unit
     * @param health the units health
     */
    public CavalryUnit(String name,int health){
        super(name,health,20,12);
        this.hasAttacked = false;
    }

    /**
     * Attack bonus for cavalry unit if it is attacking another unit
     * @param terrain the terrain the battle is being held on
     * @return If the terrain is PLAINS and it is the first time attack the bonus is 7
     after the first attack it will always be 3
     However if the terrain is not Plains the first attack bonus is 6
     and after the first attack it will always be 2
     */
    public int getAttackBonus(String terrain) {
        if (terrain.toUpperCase(Locale.ROOT).equalsIgnoreCase("plains")){
            if (hasAttacked) {
                return 3;
            } else {
                this.hasAttacked = true;
                return 7;
            }
        } else {
            if (hasAttacked) {
                return 2;
            } else {
                this.hasAttacked = true;
                return 6;
            }
        }
    }

    /**
     * Resist bonus if a cavalry unit is being attacked by another unit
     * @param terrain the terrain the battle is being held on
     * @return resist bonus 0 if the terrain is forest or bonus 1 if the terrain is something else
     */
    @Override
    public int getResistBonus (String terrain){
        int resistBonus;
        if(terrain.toUpperCase(Locale.ROOT).equalsIgnoreCase("forest")){
           resistBonus = 0;
        }else{
            resistBonus = 1;
        }
        return resistBonus;
    }
}
