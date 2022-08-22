package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Units.CommanderUnit;
import edu.ntnu.idatt2001.Units.RangedUnit;
import edu.ntnu.idatt2001.Units.Unit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;



public class BattleTests {


    @Nested
    class battleTest {


        @Test
        void testWhenOneArmyAttackAnotherArmy(){
            Unit rangedUnit = new RangedUnit("InfantryUnit", 100, 10, 8);
            Army armyOne = new Army("newArmy");
            armyOne.add(rangedUnit);


            Unit commanderUnit = new CommanderUnit("CommanderUnit", 100, 25, 15);
            Unit commanderUnit2 = new CommanderUnit("CommanderUnit", 100, 25, 15);
            Unit commanderUnit3 = new CommanderUnit("CommanderUnit", 100, 25, 15);
            Unit commanderUnit4 = new CommanderUnit("CommanderUnit", 100, 25, 15);
            Unit commanderUnit5 = new CommanderUnit("CommanderUnit", 100, 25, 15);
            Army armyTwo = new Army("newArmy");
            armyTwo.add(commanderUnit);
            armyTwo.add(commanderUnit2);
            armyTwo.add(commanderUnit3);
            armyTwo.add(commanderUnit4);
            armyTwo.add(commanderUnit5);

            String string = "";
            Battle battle = new Battle(armyOne,armyTwo);
            assertSame(armyTwo, battle.simulate(string));
        }

    }


}
