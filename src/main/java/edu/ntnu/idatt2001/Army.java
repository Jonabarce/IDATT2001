package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Units.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Army{
    private String name;
    private ArrayList<Unit> units = new ArrayList<Unit>();

    /**
     * Constructor for army
     * @param name the name of the army
     */
    public Army(String name) {
        this.name = name;
    }


    /**
     * Constructor for army
     * @param name the name of the army
     * @param units a list with units
     */
    public void Army(String name,ArrayList<Unit> units){
        this.name = name;
        this.units = units;
    }

    /**
     * Get the name of the Army
     * @return the name of the Army
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the army to something else
     * @param name the new name for the army
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Method to add a unit to the army
     * @param unit the unit that is being added in the army
     * @return true
     */
    public boolean add(Unit unit){
        return units.add(unit);
    }

    /**
     * addAll function
     * @param units list of units that is being added
     */
    public void addAll(ArrayList<Unit> units){
        for(Unit unit : units)
        this.add(unit);
    }

    /**
     * Method to remove units from an army if the health is 0
     * @param unit the unit that is being removed
     */
    public void remove(Unit unit){
        units.remove(unit);
    }


    /**
     * Method to check if an army this has units
     * @return true if the size of the ArrayList is greater then 0
     else return false
     */
    public boolean hasUnits(){
        if(units.size() > 0){
            return true;
        }
        return false;
    }

    /**
     * Method to get all units registered
     * @return the ArrayList with the units
     */
    public ArrayList<Unit> getAllUnits(){
        return units;
    }

    /**
     * Method to get a random unit from the army
     * @return a random unit from the army
     */
    public Unit getRandom(){
        Random getRandomUnit = new Random();
        int index = getRandomUnit.nextInt(units.size());
        return units.get(index);
    }

    /**
     * Method to get the size of the army as a string
     * It needs to be a string to be able to add to a label
     * in the GUI
     * @return total units in the army as an string
     */
    public String getArmySize(){
        String totalNumber = "";
        return totalNumber + units.size();
    }

    /**
     * Method to get the army size as an int
     * @return total units in the army as an int
     */
    public int getArmySizeAsInt(){
        return units.size();
    }

    /**
     * Method to get infantry units registered in the army
     * @return an arraylist with infantry units if any is found
     */
    public ArrayList<Unit> getInfantryUnit(){
        return (ArrayList<Unit>) units.stream().filter(unit -> unit instanceof InfantryUnit).collect(Collectors.toList());
    }

    /**
     * Method to get cavalry units registered in the army
     * @return an arraylist with cavalry units if any is found
     */
    public ArrayList<Unit> getCavalryUnit(){
        return (ArrayList<Unit>) units.stream().filter(u -> u instanceof CavalryUnit).filter(u -> !(u instanceof CommanderUnit)).collect(Collectors.toList());
    }

    /**
     * Method to get ranged units registered in the army
     * @return an arraylist with ranged units if any is found
     */
    public ArrayList<Unit> getRangedUnit(){
        return (ArrayList<Unit>) units.stream().filter(unit -> unit instanceof RangedUnit).collect(Collectors.toList());
    }

    /**
     * Method to get commander units registered in the army
     * @return an arraylist with commander units if any is found
     */
    public ArrayList<Unit> getCommanderUnit(){
        return (ArrayList<Unit>) units.stream().filter(unit -> unit instanceof CommanderUnit).collect(Collectors.toList());
    }


    /**
     * Method to get the total number of each unit stored in the army
     * as a String
     * @return total number of each unit stored in the army as an string
     */
    public String getTotalNumberOfEachUnit(){
        return ("InfantryUnit: " + getInfantryUnit().size() + "\n" +
                "CavalryUnit: " + (getCavalryUnit().size() + "\n" +
                "RangedUnit: " + getRangedUnit().size() + "\n" +
                "CommanderUnit: " + getCommanderUnit().size()));
    }

    /**
     * Equals method
     * @param o the object
     * @return true or false whether the object is equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Army army = (Army) o;
        return Objects.equals(name, army.name) && Objects.equals(units, army.units);
    }

    /**
     * hashCode method
     * @return the hashcode of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, units);
    }

    /**
     * toString Method
     * @return relevant information about the name of the army and units
     */
    @Override
    public String toString() {
        return "Army - Name: " + name + " Units: " + units;
    }

}
