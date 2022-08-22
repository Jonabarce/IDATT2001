package edu.ntnu.idatt2001.GUI;

import edu.ntnu.idatt2001.*;
import edu.ntnu.idatt2001.Units.Unit;
import edu.ntnu.idatt2001.Units.UnitFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditArmyController implements Initializable {

    @FXML
    public ListView<String> savedArmy;
    public Label currentlySavedArmyName;
    public Label armyName;
    public Label totalOfUnits;
    public Label totalOfEachUnit;
    public TextField totalRandomUnitsToRemove;
    public TextField nameForUnit;
    public TextField health;
    public TextField newNameForArmy;
    public TextField totalUnits;
    public TextField totalUnitsForRemoving;
    public CheckBox rangedUnit;
    public CheckBox infantryUnit;
    public CheckBox commanderUnit;
    public CheckBox cavalryUnit;
    public CheckBox rangedUnitForRemoving;
    public CheckBox infantryUnitForRemoving;
    public CheckBox commanderUnitForRemoving;
    public CheckBox cavalryUnitForRemoving;
    public Army existingArmy;
    public String theArmyBeingChanged;

    private Scene scene;
    private Stage stage;


    /**
     * Method to switch from current page EditArmyOnePage to UnitsPage
     *
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToUnitsPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/UnitsPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to switch from current page EditArmyOnePage to ArmyPage
     *
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToArmiesPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ArmyPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to switch from current page EditArmyOnePage to SimulationPage
     *
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToSimulationPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/SimulationPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to load the existing army stored in the file
     * It displays the name for the army
     * And it calls on the displayTotalUnits method and addArmyToListView
     * which are method used to display
     *
     * @throws IOException if the file is not found
     */
    @FXML
    public void loadExistingArmy() throws IOException {
        theArmyBeingChanged = FileManagement.readName(new File("src/main/resources/Files/TheArmyBeingChanged.csv"));
        existingArmy = FileManagement.readArmyFromFile(new File("src/main/resources/Files/Army"+theArmyBeingChanged+".csv"));
        currentlySavedArmyName.setText(existingArmy.getName());
        armyName.setText(existingArmy.getName());
        displayTotalUnits(existingArmy);
        addArmyToListView(existingArmy);
    }

    /**
     * Method to display total units in army
     * and how many of each units there are in the army
     *
     * @param army the army to display total units from
     */
    public void displayTotalUnits(Army army) {
        totalOfUnits.setText(army.getArmySize());
        totalOfEachUnit.setText(army.getTotalNumberOfEachUnit());
    }

    /**
     * Method to let the user change the name for the army
     * A alert-box will pop up if the user tries to make a blank name
     *
     * @throws IOException if the file is not found
     */
    public void makeNewNameForArmy() throws IOException {
        if (newNameForArmy.getText().isBlank()) {
            AlertBox.displayAlertBox("Error!", "The new name cannot be blank");
        } else if(newNameForArmy.getLength() > 8)
            AlertBox.displayAlertBox("Error!", "The name cannot be longer than 8 characters");
        else {
            existingArmy = FileManagement.readArmyFromFile(new File("src/main/resources/Files/Army"+theArmyBeingChanged+".csv"));
            existingArmy.setName(String.valueOf(newNameForArmy.getText()));
            FileManagement.writeArmyToFile(existingArmy, new File("src/main/resources/Files/Army"+theArmyBeingChanged+".csv"));
            loadExistingArmy();
        }
    }

    /**
     * Method to remove all units stored in the army and
     * then save the army with 0 units stored in it
     * It will also afterwards clear the listView since there is no units stored in
     * the army anymore
     * A alert-box will pop up if there is no units to remove
     *
     * @throws IOException if the file is not found
     */
    public void removeAllUnits() throws IOException {
        boolean done = false;
        existingArmy = FileManagement.readArmyFromFile(new File("src/main/resources/Files/Army"+theArmyBeingChanged+".csv"));

        if (existingArmy.getArmySizeAsInt() <= 0) {
            AlertBox.displayAlertBox("Error!", "There is no units to remove");
        } else {
            while (!done) {
                existingArmy.remove(existingArmy.getRandom());
                if (existingArmy.getArmySizeAsInt() <= 0) {
                    done = true;
                }
            }
            savedArmy.getItems().clear();
            FileManagement.writeArmyToFile(existingArmy, new File("src/main/resources/Files/Army"+theArmyBeingChanged+".csv"));
            displayTotalUnits(existingArmy);
        }
    }

    /**
     * Method to remove x random units
     * A alert-box will pop up if the text-field with
     * how many units the user wants to remove is blank
     * it will also be an alert-box if the input from the user
     * is not an integer
     * It doesn't make sense to remove more units than registered
     * therefor it will also be an alert-box if the user is trying to remove
     * too much of the selected unit or if the integer given by the user
     * is 0 or negative
     * When removing is done it will save and display the army
     *
     * @throws IOException if the file is not found
     */
    public void removeRandomUnits() throws IOException {

        if (totalRandomUnitsToRemove.getText().isBlank()) {
            AlertBox.displayAlertBox("Error!", "Total units cannot be blank...");
        } else {
            existingArmy = FileManagement.readArmyFromFile(new File("src/main/resources/Files/Army"+theArmyBeingChanged+".csv"));
            int totalRandomUnitsForRemove = 0;

            boolean valid;
            try {
                totalRandomUnitsForRemove = Integer.parseInt(String.valueOf(totalRandomUnitsToRemove.getText()));
                valid = true;
            } catch (NumberFormatException e) {
                valid = false;
            }

            if (!valid) {
                AlertBox.displayAlertBox("Error!", "Total units must be an integer");
            } else {
                if (totalRandomUnitsForRemove > existingArmy.getArmySizeAsInt()) {
                    AlertBox.displayAlertBox("Error!", "You are trying to remove too many units");
                } else {
                    if (totalRandomUnitsForRemove <= 0) {
                        AlertBox.displayAlertBox("Error!", "Total units must be greater then 0");
                    } else {
                        for (int i = 0; i < totalRandomUnitsForRemove; i++) {
                            existingArmy.remove(existingArmy.getRandom());
                        }
                        if (existingArmy.getArmySizeAsInt() == 0) {
                            savedArmy.getItems().clear();
                            FileManagement.writeArmyToFile(existingArmy, new File("src/main/resources/Files/Army"+theArmyBeingChanged+".csv"));
                            displayTotalUnits(existingArmy);
                        } else {
                            addArmyToListView(existingArmy);
                            FileManagement.writeArmyToFile(existingArmy, new File("src/main/resources/Files/Army"+theArmyBeingChanged+".csv"));
                            displayTotalUnits(existingArmy);
                        }
                    }
                }
            }
        }
    }

    /**
     * This is a method used in removeChosenUnit to remove units
     * based on what type of unit the user wants to remove
     * alert-boxes will pop up the same way as in the method removeRandomUnits
     *
     * @param units    a list of units to remove
     * @param unitType the unit type that the user is trying to remove
     * @throws IOException if the file is not found
     */
    public void removeUnitsAndSave(ArrayList<Unit> units, String unitType) throws IOException {

        if (totalUnitsForRemoving.getText().isBlank()) {
            AlertBox.displayAlertBox("Error!", "Total units cannot be blank...");
        } else {
            int nUnits = 0;
            boolean valid;
            try {
                nUnits = Integer.parseInt(String.valueOf(totalUnitsForRemoving.getText()));
                valid = true;
            } catch (NumberFormatException e) {
                valid = false;
            }
            if (!valid) {
                AlertBox.displayAlertBox("Error!", "Total units must be an integer");
            } else {

                if (nUnits <= 0) {
                    AlertBox.displayAlertBox("Error!", "Total units must be greater then 0");
                } else {
                    if (nUnits > units.size()) {
                        AlertBox.displayAlertBox("Error!", "You are trying to remove too many " + unitType + "\n" +
                                "You only have a total of " + units.size() + " " + unitType);
                    } else {
                        for (int i = 0; i < nUnits; i++) {
                            existingArmy.remove(units.get(i));

                        }
                        if (existingArmy.getArmySizeAsInt() == 0) {
                            savedArmy.getItems().clear();
                            FileManagement.writeArmyToFile(existingArmy, new File("src/main/resources/Files/Army"+theArmyBeingChanged+".csv"));
                            displayTotalUnits(existingArmy);
                        } else {
                            addArmyToListView(existingArmy);
                            FileManagement.writeArmyToFile(existingArmy, new File("src/main/resources/Files/Army"+theArmyBeingChanged+".csv"));
                            displayTotalUnits(existingArmy);
                        }
                    }
                }
            }
        }
    }

    /**
     * Method also used to remove a specific unit chosen
     * by the user together with the method removeUnitsAndSave
     * Alert-box will pop up if the user haven't chosen any units and are
     * still trying to remove
     *
     * @throws IOException if the file is not found
     */
    public void removeChosenUnit() throws IOException {
        ArrayList<Unit> units;
        existingArmy = FileManagement.readArmyFromFile(new File("src/main/resources/Files/Army"+theArmyBeingChanged+".csv"));
        if (rangedUnitForRemoving.isSelected()) {
            String unitType = "Ranged Units";
            units = existingArmy.getRangedUnit();
            removeUnitsAndSave(units, unitType);
        } else if (cavalryUnitForRemoving.isSelected()) {
            String unitType = "Cavalry Units";
            units = existingArmy.getCavalryUnit();
            removeUnitsAndSave(units, unitType);
        } else if (commanderUnitForRemoving.isSelected()) {
            String unitType = "Commander Units";
            units = existingArmy.getCommanderUnit();
            removeUnitsAndSave(units, unitType);
        } else if (infantryUnitForRemoving.isSelected()) {
            String unitType = "Infantry Units";
            units = existingArmy.getInfantryUnit();
            removeUnitsAndSave(units, unitType);
        } else {
            AlertBox.displayAlertBox("Error!", "Please select a unit");
        }
    }

    /**
     * This method is used to add units based on unitType chosen
     * by the user
     * It will trow alertboxes and let the user know if the input is wrong
     * @param unitType what type of unit the user wants to add
     * @throws IOException if the file is not found
     */
    public void readArmyAndAddUnits(String unitType) throws IOException {
        existingArmy = FileManagement.readArmyFromFile(new File("src/main/resources/Files/Army"+theArmyBeingChanged+".csv"));
        if (existingArmy.getArmySizeAsInt() == 10000) {
            AlertBox.displayAlertBox("Error!", "Maximum size of army is achieved");
        } else {
            int healthForUnit = 0;
            int nUnits = 0;
            boolean valid;
            try {
                healthForUnit = Integer.parseInt(String.valueOf(health.getText()));
                nUnits = Integer.parseInt(String.valueOf(totalUnits.getText()));
                valid = true;
            } catch (Exception e) {
                valid = false;
            }
            if (!valid) {
                AlertBox.displayAlertBox("Error", """
                        Health and total units must be integers
                        Health must be greater then 0 and lower then 2147483648
                        Total units to be added must be greater then 0 and lower then 5000""");
            } else if (healthForUnit <= 0 || nUnits <= 0) {
                AlertBox.displayAlertBox("Error", "Health and total units must be greater then 0");
            } else if (nUnits > 5000) {
                AlertBox.displayAlertBox("Error!", "You can not add more then 5000 units at one time");
            } else if (nUnits + existingArmy.getArmySizeAsInt() > 10000) {
                int bound = 10000-existingArmy.getArmySizeAsInt();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error!");
                alert.setHeaderText("You are trying to add: " + nUnits + " to the army");
                alert.setContentText("Maximum size of army is 10 000" + "\n" + "The army size is already: " + existingArmy.getArmySizeAsInt() +
                        "\n" + "You can add a maximum of: " + bound + " units to the army to achieve 10 000 " + "\n" + "Do you want to do that? ");
                if (alert.showAndWait().get() == ButtonType.OK) {
                    String theNameForTheUnit = String.valueOf(nameForUnit.getText());
                    UnitFactory unitFactory = new UnitFactory();
                    existingArmy.addAll(unitFactory.makeAListOfNUnits(bound, unitType, theNameForTheUnit, healthForUnit));
                    addArmyToListView(existingArmy);
                    FileManagement.writeArmyToFile(existingArmy, new File("src/main/resources/Files/Army"+theArmyBeingChanged+".csv"));
                    displayTotalUnits(existingArmy);
                }
            }else{
                String theNameForTheUnit = String.valueOf(nameForUnit.getText());
                UnitFactory unitFactory = new UnitFactory();
                existingArmy.addAll(unitFactory.makeAListOfNUnits(nUnits, unitType, theNameForTheUnit, healthForUnit));
                addArmyToListView(existingArmy);
                FileManagement.writeArmyToFile(existingArmy, new File("src/main/resources/Files/Army"+theArmyBeingChanged+".csv"));
                displayTotalUnits(existingArmy);
            }
        }
    }

    /**
     * Method also used to add units to the army based on
     * chosen unit type by the user together with the method readArmyAndAddUnits
     * Alert-box will pop up if the user is trying to add units
     * when the name for the unit, the health and total units to add is blank
     * An alert-box will also pop up if the user haven't chosen what type of unit
     * to add in the army
     * @throws IOException if the file is not found
     */
    public void addUnitsToArmy() throws IOException {
        if(nameForUnit.getText().isBlank() || health.getText().isBlank() || totalUnits.getText().isBlank()){
            AlertBox.displayAlertBox("Error!","Name,health and total units cannot be blank");
        }else{
            if (rangedUnit.isSelected()) {
                String unitType = "RANGEDUNIT";
                readArmyAndAddUnits(unitType);
            } else if (infantryUnit.isSelected()) {
                String unitType = "INFANTRYUNIT";
                readArmyAndAddUnits(unitType);
            } else if (commanderUnit.isSelected()) {
                String unitType = "COMMANDERUNIT";
                readArmyAndAddUnits(unitType);
            } else if (cavalryUnit.isSelected()) {
                String unitType = "CAVALRYUNIT";
                readArmyAndAddUnits(unitType);
            }else{
                AlertBox.displayAlertBox("Error", "Please select unit type");
            }
        }
    }

    /**
     * Method to add armyOne to a listView done with a StringBuilder
     * @param army the army that is being added to the listview
     */
    public void addArmyToListView(Army army){
        StringBuilder f = new StringBuilder();
        savedArmy.getItems().clear();
        for(Unit unit : army.getAllUnits()){
            f.append(unit.getClass().getSimpleName()).append(",").append(unit.getName()).append(",").append(unit.getHealth()).append(" health"+"\n");
        }
        savedArmy.getItems().add(String.valueOf(f));
    }

    /**
     * This is what is does each time the page is brought up in
     * the application
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadExistingArmy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}