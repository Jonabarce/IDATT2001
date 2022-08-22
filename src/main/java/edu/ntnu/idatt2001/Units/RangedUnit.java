package edu.ntnu.idatt2001.Units;

import java.util.Locale;

public class RangedUnit extends Unit {

    private int numberOfAttacks;

    /**
     * Constructor for ranged unit
     * @param name the name of the unit
     * @param health units health
     * @param attack total attack for the unit
     * @param armour total armour for the unit
     */
    public RangedUnit(String name, int health,int attack, int armour){
        super(name,health,attack,armour);
        this.numberOfAttacks = 0;
    }


    /**
     * Simplified constructor for ranged unit
     attack is set to be 15 and the armour is set to be 8
     * @param name the name of the unit
     * @param health units health
     */
    public RangedUnit(String name,int health){
        super(name,health,15,8);
        this.numberOfAttacks = 0;
    }


    /**
     * Attack bonus for ranged unit if it is attacking another unit
     * @param terrain the terrain the battle is being held on
     * @return attack bonus 4 if the terrain is hill, attack bonus 2 if the terrain is forest,
     * and attack bonus 3 if there is non of those terrains
     */
    @Override
    public int getAttackBonus(String terrain){
        int attackBonus;
        if(terrain.toUpperCase(Locale.ROOT).equalsIgnoreCase("hill")){
            attackBonus = 4;
        }else if(terrain.toUpperCase(Locale.ROOT).equalsIgnoreCase("forest")){
            attackBonus = 2;
        }else{
            attackBonus = 3;
        }
        return attackBonus;
    }

    /**
     * Resist bonus for ranged unit if it is being attacked by another unit
     * @param terrain the terrain the battle is being held on (Doesn't matter for this bonus)
     * @return resist bonus 6 first time it is being attacked
     * resist bonus 4 the second time it is being attacked and then
     * a standard resist bonus 2 from the third attack onwards
     * (using counter to make this work)
     */
    @Override
    public int getResistBonus(String terrain) {
        int resistanceBonus=2;
        if(numberOfAttacks>=2){
            resistanceBonus=2;
        }
        else if(numberOfAttacks==1){
            resistanceBonus=4;
        }
        else if(numberOfAttacks==0){
            resistanceBonus=6;
        }
        numberOfAttacks++;
        return resistanceBonus;
    }
}
