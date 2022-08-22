package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Units.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BonusTest {

    @Nested
    public class testBonus{

        @Test
        public void checkResistBonusForInfantryUnit(){
            Unit newCavalryUnit = new CavalryUnit("CavalryUnit", 100, 20, 12);
            Unit newInfantryUnit = new InfantryUnit("InfantryUnit",100,15,10);

            String terrain = "forest";
            newCavalryUnit.attack(newInfantryUnit,terrain);
            assertTrue(newInfantryUnit.getResistBonus(terrain) == 2);
        }

        @Test
        public void checkResistBonusForCavalryUnit(){
            Unit newCavalryUnit = new CavalryUnit("CavalryUnit", 100, 20, 12);
            Unit newCommanderUnit = new CommanderUnit("CommanderUnit", 100, 25, 15);

            String terrain = "forest";
            newCommanderUnit.attack(newCavalryUnit,terrain);
            assertTrue(newCavalryUnit.getResistBonus(terrain) == 0);
        }

        @Test
        public void checkResistBonusForCommanderUnit(){
            Unit newCavalryUnit = new CavalryUnit("CavalryUnit", 100, 20, 12);
            Unit newCommanderUnit = new CommanderUnit("CommanderUnit", 100, 25, 15);

            String terrain = "forest";
            newCavalryUnit.attack(newCommanderUnit,terrain);
            assertTrue(newCommanderUnit.getResistBonus(terrain) == 0);
        }


        @Test
        public void checkAttackBonusForInfantryUnit(){
            Unit newCavalryUnit = new CavalryUnit("CavalryUnit", 100, 20, 12);
            Unit newInfantryUnit = new InfantryUnit("InfantryUnit",100,15,10);

            String terrain = "forest";
            newInfantryUnit.attack(newCavalryUnit,terrain);
            assertTrue(newInfantryUnit.getAttackBonus(terrain) == 3);
        }

        @Test
        public void checkAttackBonusForCavalryUnit(){
            Unit newCavalryUnit = new CavalryUnit("CavalryUnit", 100, 20, 12);
            Unit newCommanderUnit = new CommanderUnit("CommanderUnit", 100, 25, 15);

            String terrain = "plain";
            newCavalryUnit.attack(newCommanderUnit,terrain);
            assertTrue(newCavalryUnit.getAttackBonus(terrain) == 2);
        }


        @Test
        public void checkResistBonusForRangedUnit(){
            Unit newCavalryUnit = new CavalryUnit("CavalryUnit", 100, 20, 12);
            Unit newRangedUnit = new RangedUnit("RangedUnit",100,15,8);


            String terrain = "";
            assertTrue(newRangedUnit.getResistBonus(terrain) == 6);
            assertTrue(newRangedUnit.getResistBonus(terrain) == 4);
            newCavalryUnit.attack(newRangedUnit,terrain);
            assertTrue(newRangedUnit.getResistBonus(terrain) == 2);


        }

        @Test
        public void checkAttackBonusForCommanderUnit(){
            Unit newCavalryUnit = new CavalryUnit("CavalryUnit", 100, 20, 12);
            Unit newCommanderUnit = new CommanderUnit("CommanderUnit", 100, 25, 15);

            String terrain = "";
            newCommanderUnit.attack(newCavalryUnit,terrain);
            assertTrue(newCavalryUnit.getAttackBonus(terrain) == 6);
        }

        @Test
        public void checkAttackBonusForRangedUnit(){
            Unit newCavalryUnit = new CavalryUnit("CavalryUnit", 100, 20, 12);
            Unit newRangedUnit = new RangedUnit("RangedUnit",100,15,8);

            String terrain = "hill";
            newRangedUnit.attack(newCavalryUnit,terrain);
            assertTrue(newRangedUnit.getAttackBonus(terrain) == 4);
        }
    }
}
