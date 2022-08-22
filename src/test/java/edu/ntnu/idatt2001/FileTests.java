package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Units.RangedUnit;
import edu.ntnu.idatt2001.Units.Unit;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileTests {


    @Nested
    class testFileManagement {


        @Test
        void testToSeeIfItThrowsIOExceptionWhenTheFileIsNotACSVFile() throws IOException {
            Army testArmy = new Army("test");
            assertThrows(IOException.class, () -> FileManagement.writeArmyToFile(testArmy, new File("src/main/resources/Files/TestFiles/TestFile.txt")));
            assertThrows(IOException.class, () -> FileManagement.readArmyFromFile(new File("src/main/resources/Files/TestFiles/TestFile.txt")));
        }

        @Test
        void testToTryToWriteAndReadForAFile() throws IOException {
            Unit rangedUnit = new RangedUnit("test", 100, 15, 8);
            Army testArmy = new Army("newArmy");
            testArmy.add(rangedUnit);

            FileManagement.writeArmyToFile(testArmy,new File("src/main/resources/Files/TestFiles/testWriteAndReadFile.csv"));

            assertEquals(testArmy,FileManagement.readArmyFromFile(new File("src/main/resources/Files/TestFiles/testWriteAndReadFile.csv")));
        }


        @Test
        void testToSeeIfItThrowsIOExceptionsWhenThereIsMoreThanCommasThen3(){
            assertThrows(IllegalArgumentException.class,() -> FileManagement.readArmyFromFile(new File("src/main/resources/Files/TestFiles/CommaTest.csv")));
        }

        @Test
        void testToSeeIfItThrowsNullPointerExceptionIfTheHealthIsNotAnInt(){
            assertThrows(IllegalArgumentException.class,() -> FileManagement.readArmyFromFile(new File("src/main/resources/Files/TestFiles/HealthTest.csv")));
        }

        @Test
        void testToWriteAndReadArmyName() throws IOException {
            String testArmyName = "test";
            FileManagement.writeName(new File("src/main/resources/Files/TestFiles/WriteAndReadName.csv"),testArmyName);
            assertEquals(testArmyName,FileManagement.readName(new File("src/main/resources/Files/TestFiles/WriteAndReadName.csv")));
        }


    }
}
