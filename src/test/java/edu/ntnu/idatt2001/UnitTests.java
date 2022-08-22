package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Units.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UnitTests{

    @Nested
    class registerNewWrongUnit {

        @Test
        void registerWrongNewRangedUnitByAddingWrongValues() {
            assertThrows(IllegalArgumentException.class, () -> {
                Unit newRangedUnit = new RangedUnit("", -1, -1, -1);
            });
        }

        @Test
        void testSetHealthMethod(){
            Unit newRangedUnit = new RangedUnit("RangedUnit", 100, 15, 8);
            newRangedUnit.setHealth(-300);
            assertTrue(newRangedUnit.getHealth() == 0);
        }


    }

    @Nested
    class testsForArmyClass{

        @Test
        void addNewArmyAndAddNewUnitToArrayListAndCheckIfItHasUnitsRegistered(){
            Unit newRangedUnit = new RangedUnit("RangedUnit", 100, 10, 8);
            Army newArmy = new Army("newArmy");
            newArmy.add(newRangedUnit);
            assertTrue(newArmy.hasUnits());
        }

        @Test
        void addNewArmyAndAddNewUnitThenRemoveThatUnit(){
            Unit newRangedUnit = new RangedUnit("RangedUnit", 100, 15, 8);
            Army newArmy = new Army("newArmy");
            newArmy.add(newRangedUnit);
            assertTrue(newArmy.hasUnits());
            newArmy.remove(newRangedUnit);
            assertFalse(newArmy.hasUnits());
        }

        @Test
        void addNewArmyAndAddNewUnitThenCheckGetRandomFunction(){
            Unit newRangedUnit = new RangedUnit("RangedUnit", 100, 15, 8);
            Army newArmy = new Army("newArmy");
            newArmy.add(newRangedUnit);
            assertEquals(newArmy.getRandom(), newRangedUnit);
        }

        @Test
        void addNewArmyAndAddNewUnitThenTryTheAddAllFunction(){

        }

        @Test
        void addNewArmyAndAddNewUnitThenTryToGetAllUnits(){
            Unit newRangedUnit = new RangedUnit("RangedUnit", 100, 15, 8);
            ArrayList<Unit> gAU = new ArrayList<>();
            gAU.add(newRangedUnit);
            Army newArmy = new Army("newArmy");
            newArmy.add(newRangedUnit);
            assertEquals(gAU,newArmy.getAllUnits());

        }

        @Test
        void addNewArmyAndGetInfantryUnitOutOfIt(){
            Unit newInfantryUnit = new InfantryUnit("InfantryUnit",100,15,10);

            Army newArmy = new Army("newArmy");
            newArmy.add(newInfantryUnit);

            ArrayList<Unit> g = new ArrayList<>();
            g.add(newInfantryUnit);

            assertEquals(g,newArmy.getInfantryUnit());


        }
        @Test
        void addNewArmyAndGetCavalryUnitOutOfIt(){
            Unit newCavalryUnit = new CavalryUnit("CavalryUnit", 100, 20, 12);

            Army newArmy = new Army("newArmy");
            newArmy.add(newCavalryUnit);

            ArrayList<Unit> g = new ArrayList<>();
            g.add(newCavalryUnit);

            assertEquals(g,newArmy.getCavalryUnit());
        }

        @Test
        void addNewArmyAndGetRangedUnitOutOfIt(){
            Unit newRangedUnit = new RangedUnit("InfantryUnit", 100, 10, 8);

            Army newArmy = new Army("newArmy");
            newArmy.add(newRangedUnit);

            ArrayList<Unit> g = new ArrayList<>();
            g.add(newRangedUnit);

            assertEquals(g,newArmy.getRangedUnit());
        }

        @Test
        void addNewArmyAndGetCommanderUnitOutOfIt(){
            Unit newCommanderUnit = new CommanderUnit("CommanderUnit", 100, 25, 15);

            Army newArmy = new Army("newArmy");
            newArmy.add(newCommanderUnit);

            ArrayList<Unit> g = new ArrayList<>();
            g.add(newCommanderUnit);

            assertEquals(g,newArmy.getCommanderUnit());
        }

        @Test
        void getSizeOfArmyAndHowManyOfEachUnitThereIs(){
            Unit newCommanderUnit = new CommanderUnit("CommanderUnit", 100, 25, 15);


            Army newArmy = new Army("newArmy");
            newArmy.add(newCommanderUnit);


            assertEquals("1",newArmy.getArmySize());
            assertEquals("""
                    InfantryUnit: 0
                    CavalryUnit: 0
                    RangedUnit: 0
                    CommanderUnit: 1""",newArmy.getTotalNumberOfEachUnit());


        }


    }
}






