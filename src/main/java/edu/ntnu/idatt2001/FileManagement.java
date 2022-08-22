package edu.ntnu.idatt2001;

import edu.ntnu.idatt2001.Units.*;

import java.io.*;
import java.util.Scanner;

public class FileManagement {


    /**
     * Method used to write one army to a file
     * @param army the army that is being written to the file
     * @param file the file the army is being written to
     * @throws IOException if the file does not end with .csv
     */
    public static void writeArmyToFile(Army army,File file) throws IOException {
        if(!file.getName().endsWith(".csv")){
            throw new IOException("Error! Only .csv-files are supported...");
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(army.getName() + "\n");
            for (Unit unit : army.getAllUnits()) {
                fileWriter.write(unit.getClass().getSimpleName() + "," + unit.getName() + "," + unit.getHealth() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fileWriter != null){
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * Method to read an army from a file
     * @param file the file the wanted army is stored
     * @return the army saved in the file
     * @throws IOException if the file does not end with .csv
     * or if the file is not found for the scanner or if the health is not
     * an integer
     */
    public static Army readArmyFromFile(File file) throws IOException {
        if (!file.getName().endsWith(".csv")) {
            throw new IOException("Error! Only .csv-files are supported...");
        }
        Army army;
        String armyName;
        try (Scanner scanner = new Scanner(file)) {
            armyName = scanner.nextLine();
            army = new Army(armyName);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
                if(tokens.length != 3){
                    throw new IllegalArgumentException("Invalid data!");
                }

                String unitClass = tokens[0];
                String unitName = tokens[1];
                int health;

                try {
                    health = (Integer.parseInt(tokens[2]));
                } catch (NullPointerException e) {
                    throw new IOException("Error! Unit health must be an integer");
                }

                if(unitClass.equalsIgnoreCase("RangedUnit")){
                    army.add(new RangedUnit(unitName,health));
                }else if(unitClass.equalsIgnoreCase("CavalryUnit")){
                    army.add(new CavalryUnit(unitName,health));
                }else if(unitClass.equalsIgnoreCase("CommanderUnit")){
                    army.add(new CommanderUnit(unitName,health));
                }else if(unitClass.equalsIgnoreCase("InfantryUnit")){
                    army.add(new InfantryUnit(unitName,health));
                }else{
                    throw new IllegalArgumentException("No match for the unit class...");
                }
            }
        }
        return army;
    }

    /**
     * Method to read name stored in the file
     * @param file the file the name is being read from
     * @return the name stored in the file
     * @throws IOException if the file does not end with .csv
     * or if the file is not found for the scanner
     */
    public static String readName(File file) throws IOException {
        if (!file.getName().endsWith(".csv")) {
            throw new IOException("Error! Only .csv-files are supported...");
        }
        String name;
        try (Scanner scanner = new Scanner(file)) {
            name = scanner.nextLine();
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Error! Army name was not found");
        }
        return name;
    }

    /**
     * Method to write a single line like a name and save it in a file
     * @param file the file to be written to
     * @param name the string such as name or something else
     * @return the name/string stored in the file
     * @throws IOException if the file does not end with .csv
     */
    public static String writeName(File file, String name)throws IOException {
        if (!file.getName().endsWith(".csv")) {
            throw new IOException("Error! Only .csv-files are supported...");
        }
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(name);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return name;
    }


    /**
     * Method used to clear a file
     * @param file the file that is being cleared
     * @throws IOException if the file does not end with .csv
     */
    public static void clearFile(File file)throws IOException {
        if (!file.getName().endsWith(".csv")) {
            throw new IOException("Error! Only .csv-files are supported...");
        }
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            PrintWriter printWriter = new PrintWriter(fileWriter, false);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}