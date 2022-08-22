package edu.ntnu.idatt2001.Units;


public class CommanderUnit extends CavalryUnit {

    /**
     * Constructor for commander unit
     * @param name the name of the unit
     * @param health units health
     * @param attack total attack for the unit
     * @param armour total armour for the unit
     */
    public CommanderUnit(String name, int health, int attack, int armour) {
        super(name, health, attack, armour);
    }

    /**
     * Simplified constructor for commander unit
    attack is set to be 25 and the armour is set to be 15
     * @param name the name of the unit
     * @param health units health
     */
    public CommanderUnit(String name, int health) {
        super(name, health,25,15);
    }
}


