package edu.ntnu.idatt2001.Units;

import java.util.Objects;


public abstract class Unit {
    private final String name;
    private int health;
    private final int attack;
    private final int armour;

    /**
     * Constructor for Unit
     * @param name the name of the unit
     * @param health units health
     * @param attack total attack for the unit
     * @param armour total armour for the unit
     */
    public Unit(String name, int health, int attack, int armour) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(" Oops! The name seems to be empty. Please try again.");
        }
        if (health < 0) {
            throw new IllegalArgumentException(" Oops! The health must be greater then 0. Please try again.");
        }
        if (attack <= 0) {
            throw new IllegalArgumentException(" Oops! The value of the Attack must be greater then 0. Please try again.");
        }
        if (armour < 0) {
            throw new IllegalArgumentException(" Oops! The value of the armour must be 0 or greater. Please try again ");
        }
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.armour = armour;
    }

    /**
     * Method for units to attack units
     * @param opponent the opponent it is attacking
     */
    public void attack(Unit opponent, String terrain) {
        opponent.setHealth(opponent.getHealth() - (this.getAttack() + this.getAttackBonus(terrain) + (opponent.getArmour() + opponent.getResistBonus(terrain))));
    }


    /**
     * Get the name of the Unit
     * @return Name of Unit
     */
    public String getName() {
        return name;
    }

    /**
     * Get the Health of the Unit
     * @return Health of Unit
     */
    public int getHealth() {
        return health;
    }

    /**
     * Get the value of the Attack from Unit
     * @return Value of attack from Unit
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Get the value of the Armour from Unit
     * @return Value of the Armour from Unit
     */
    public int getArmour() {
        return armour;
    }

    /**
     * Set the health for a Unit
     * If the health goes below 0 then it just sets the health to 0
     * health 0 means dead
     * @param health units health
     */
    public void setHealth(int health) {
        this.health = Math.max(health, 0);
    }

    /**
     * toString Method
     * @return relevant information about name,health,attack,armour for Unit
     */
    @Override
    public String toString() {
        return "Unit -> Name: " + name + " Health: " + health + " Attack: " + attack + " Armour: " + armour;
    }

    /**
     * Attack Bonus when one Unit is fighting another Unit
     * @param terrain the terrain the battle is being held on
     * @return attack bonus added to the attack player when attacking another Unit
     */
    public abstract int getAttackBonus(String terrain);

    /**
     * Resist Bonus when one Unit is being attacked by another Unit
     * @param terrain the terrain the battle is being held on
     * @return resist bonus added to the person who is being attacked by another Unit
     */
    public abstract int getResistBonus(String terrain);


    /**
     * Equals method
     * @param o the object
     * @return true or false whether the object is equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return health == unit.health && attack == unit.attack && armour == unit.armour && Objects.equals(name, unit.name);
    }

}



