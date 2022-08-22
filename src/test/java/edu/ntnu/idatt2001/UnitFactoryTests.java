package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Units.UnitFactory;
import edu.ntnu.idatt2001.Units.RangedUnit;
import edu.ntnu.idatt2001.Units.Unit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UnitFactoryTests{
        @Nested
        class registerUnitsWithUnitFactory{


                @Test
                void registerUnits(){
                    UnitFactory unitFactory = new UnitFactory();
                    Unit newRangedUnit = new RangedUnit("RangedUnit", 100, 15, 8);
                    assertEquals(newRangedUnit,unitFactory.produceNewUnit("RANGEDUNIT","RangedUnit", 100));
                }

                @Test
                void registerNUnits(){
                        UnitFactory unitFactory = new UnitFactory();
                        ArrayList<Unit> testUnits = new ArrayList<>();
                        Unit newRangedUnit = new RangedUnit("RangedUnit", 100, 15, 8);
                        Unit newRangedUnit2 = new RangedUnit("RangedUnit", 100, 15, 8);
                        Unit newRangedUnit3 = new RangedUnit("RangedUnit", 100, 15, 8);

                        testUnits.add(newRangedUnit);
                        testUnits.add(newRangedUnit2);
                        testUnits.add(newRangedUnit3);

                        assertEquals(testUnits,unitFactory.makeAListOfNUnits(3,"RANGEDUNIT","RangedUnit",100));

                }

        }

}
