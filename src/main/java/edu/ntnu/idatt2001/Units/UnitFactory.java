package edu.ntnu.idatt2001.Units;


import java.util.ArrayList;

public class UnitFactory {

    /**
     * Method to produce a new unit
     * @param unitType the unit type to produce
     * @param name the name of the unit
     * @param health units health
     * @return Unit or null if there is no match for the UnitType
     */
    public Unit produceNewUnit(String unitType, String name, int health){
        Unit unit = switch (unitType) {
            case "CAVALRYUNIT" -> new CavalryUnit(name, health);
            case "COMMANDERUNIT" -> new CommanderUnit(name, health);
            case "INFANTRYUNIT" -> new InfantryUnit(name, health);
            case "RANGEDUNIT" -> new RangedUnit(name, health);
            default -> null;
        };
        return unit;
    }

    /**
     * Method to produce units when user need n number of units
     * @param numberOfUnits total numbers of units to add
     * @param unitType the unit type to produce
     * @param name the name of the unit
     * @param health units health
     * @return a list with units
     */
    public ArrayList<Unit> makeAListOfNUnits(int numberOfUnits,String unitType,String name,int health){
        ArrayList<Unit> listOfUnits = new ArrayList<>();
        for (int i = 0; i < numberOfUnits; i++){
            listOfUnits.add(produceNewUnit(unitType,name,health));
        }
        return listOfUnits;
    }

}
