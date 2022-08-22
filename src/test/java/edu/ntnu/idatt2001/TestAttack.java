package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Units.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestAttack {


    @Nested
    public class testingHealthAfterAttack {

        @Test
        public void checkHealthAfterAttackWhenCavalryUnitAttacksCommanderUnit() {
            Unit newCavalryUnit = new CavalryUnit("CavalryUnit", 100, 20, 12);
            Unit newCommanderUnit = new CommanderUnit("CommanderUnit", 100, 25, 15);

            String string = "";
            newCavalryUnit.attack(newCommanderUnit,string);
            assertTrue(newCavalryUnit.getHealth() > newCommanderUnit.getHealth());
        }

        @Test
        public void checkHealthAfterAttackWhenCavalryUnitAttacksInfantryUnit() {
            Unit newCavalryUnit = new CavalryUnit("CavalryUnit", 100, 20, 12);
            Unit newInfantryUnit = new InfantryUnit("InfantryUnit",100,15,10);

            String string = "";
            newCavalryUnit.attack(newInfantryUnit,string);
            assertTrue(newCavalryUnit.getHealth() > newInfantryUnit.getHealth());
        }

        @Test
        public void checkHealthAfterAttackWhenCavalryUnitAttacksRangedUnit(){
            Unit newCavalryUnit = new CavalryUnit("CavalryUnit", 100, 20, 12);
            Unit newRangedUnit = new RangedUnit("RangedUnit",100,15,8);

            String string = "";
            newCavalryUnit.attack(newRangedUnit,string);
            assertTrue(newCavalryUnit.getHealth() > newRangedUnit.getHealth());
        }

        @Test
        public void checkHealthAfterAttackWhenCommanderUnitAttacksCavalryUnit(){
            Unit newCavalryUnit = new CavalryUnit("CavalryUnit", 100, 20, 12);
            Unit newCommanderUnit = new CommanderUnit("CommanderUnit", 100, 25, 15);

            String string = "";
            newCommanderUnit.attack(newCavalryUnit,string);
            assertTrue(newCavalryUnit.getHealth()< newCommanderUnit.getHealth());
        }
    }
}