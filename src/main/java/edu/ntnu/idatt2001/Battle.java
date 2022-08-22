package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Units.Unit;

import java.util.Random;

public class Battle {

    private final Army armyOne;
    private final Army armyTwo;

    /**
     * Constructor for Battle with 2 armies
     * @param armyOne one army
     * @param armyTwo the second army
     */
    public Battle(Army armyOne, Army armyTwo) {
        this.armyOne = armyOne;
        this.armyTwo = armyTwo;
    }

    /**
     * Method to simulate a battle between two armies
     Random number generator so it switches between who is attacking and who is defending
     Checks if health is zero, because then it removes that unit,
     and it checks if the armies still has units in them.
     If one of them has 0 units left, the other army wins
     * @return the winner of the battle
     */
    public Army simulate(String terrain) {

        while (true) {

            Random rand = new Random();
            int upperbound = 2;
            int int_random = rand.nextInt(upperbound);

            if(int_random == 1){
                Unit attacker = armyOne.getRandom();
                Unit defender = armyTwo.getRandom();

                attacker.attack(defender,terrain);

                if(attacker.getHealth() == 0){
                    armyOne.remove(attacker);
                }else if(defender.getHealth() == 0){
                    armyTwo.remove(defender);
                }
                if(armyOne.hasUnits() && !armyTwo.hasUnits()){
                    return armyOne;
                }else if(armyTwo.hasUnits() && !armyOne.hasUnits()){
                    return armyTwo;
                }

            }else{
                Unit attacker = armyTwo.getRandom();
                Unit defender = armyOne.getRandom();

                attacker.attack(defender,terrain);

                if(attacker.getHealth() == 0){
                    armyTwo.remove(attacker);
                }else if(defender.getHealth() == 0){
                    armyOne.remove(defender);
                }
                if(armyOne.hasUnits() && !armyTwo.hasUnits()){
                    return armyOne;
                }else if(armyTwo.hasUnits() && !armyOne.hasUnits()){
                    return armyTwo;
                }
            }
        }
    }

    /**
     * toString method
     * @return relevant information the battle including armyOne and armyTwo
     */
    @Override
    public String toString() {
        return "Battle -> Army One: " + armyOne + " Army Two: " + armyTwo;
    }
}


