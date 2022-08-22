package edu.ntnu.idatt2001.GUI;

import edu.ntnu.idatt2001.Army;
import edu.ntnu.idatt2001.FileManagement;
import edu.ntnu.idatt2001.Units.Unit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;




public class ArmyController implements Initializable {

    @FXML
    public ListView<String> armyOneList;
    public ListView<String> armyTwoList;
    public Label nameForArmyOneTop;
    public Label nameForArmyTwoTop;
    public Label nameForArmyOneBottom;
    public Label nameForArmyTwoBottom;
    public Label totalUnitsArmyOne;
    public Label totalUnitsArmyTwo;
    public Label eachUnitArmyOne;
    public Label eachUnitArmyTwo;
    public Button buttonArmyOne;
    public Button buttonArmyTwo;

    private Scene scene;
    private Stage stage;
    public Army armyOne;
    public Army armyTwo;
    public Unit unit;

    /**
     * Method to switch from current page ArmyPage to SimulationPage
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
     * Method to switch from current page ArmyPage to HomePage
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToHomePage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to switch from current page ArmyPage to EditArmyOnePage
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToEditArmyOnePage(ActionEvent event) throws IOException {
        FileManagement.writeName(new File("src/main/resources/Files/TheArmyBeingChanged.csv"),"One");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/EditArmy.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to switch from current page ArmyPage to EditArmyTwoPage
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToEditArmyTwoPage(ActionEvent event) throws IOException {
        FileManagement.writeName(new File("src/main/resources/Files/TheArmyBeingChanged.csv"),"Two");
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/EditArmy.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to switch from current page ArmyPage to UnitsPage
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
     * Method to close application bases on confirmation buttons
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void closeProgram(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit game");
        alert.setHeaderText("You're about to exit the game");
        alert.setContentText("Are you sure you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.close();
        }
    }

    /**
     * This method is used to load the army one from the file and display it in the page
     * by showing its name and the units in the army
     * Showing the units is done by calling on the method addArmyOneToListView which displays the
     * units in a listView
     * @throws IOException if the file is not found
     */
    @FXML
    public void loadArmyOne() throws IOException {
        armyOne = FileManagement.readArmyFromFile(new File("src/main/resources/Files/ArmyOne.csv"));
        String nameOfArmy = armyOne.getName();
        nameForArmyOneTop.setText(nameOfArmy);
        nameForArmyOneBottom.setText(nameOfArmy);
        totalUnitsArmyOne.setText(armyOne.getArmySize());
        eachUnitArmyOne.setText(armyOne.getTotalNumberOfEachUnit());
        buttonArmyOne.setText("Edit " + armyOne.getName());
        addArmyToListView(armyOne, "one");
    }

    /**
     * This method is used to load the army one from the file and display it in the page
     * by showing its name and the units in the army
     * Showing the units is done by calling on the method addArmyTwoToListView which displays the
     * units in a listView
     * @throws IOException if the file is not found
     */
    @FXML
    public void loadArmyTwo() throws IOException {
        armyTwo = FileManagement.readArmyFromFile(new File("src/main/resources/Files/ArmyTwo.csv"));
        String nameOfArmy = armyTwo.getName();
        nameForArmyTwoTop.setText(nameOfArmy);
        nameForArmyTwoBottom.setText(nameOfArmy);
        totalUnitsArmyTwo.setText(armyTwo.getArmySize());
        eachUnitArmyTwo.setText(armyTwo.getTotalNumberOfEachUnit());
        buttonArmyTwo.setText("Edit " + armyTwo.getName());
        addArmyToListView(armyTwo, "two");
    }


    /**
     * Method to add army to a listView done with a StringBuilder
     * @param army the army to be added to the listview
     * @param whatArmy army one or army two
     */
    public void addArmyToListView(Army army, String whatArmy) {
        StringBuilder f = new StringBuilder();
        if (whatArmy.equalsIgnoreCase("one")) {
            armyOneList.getItems().clear();
            for (Unit unit : army.getAllUnits()) {
                f.append(unit.getClass().getSimpleName()).append(",").append(unit.getName()).append(",").append(unit.getHealth()).append(" health"+"\n");
            }
            armyOneList.getItems().add(String.valueOf(f));
        } else {
            armyTwoList.getItems().clear();
            for (Unit unit : army.getAllUnits()) {
                f.append(unit.getClass().getSimpleName()).append(",").append(unit.getName()).append(",").append(unit.getHealth()).append(" health"+"\n");
                }
            armyTwoList.getItems().add(String.valueOf(f));
            }
        }


        /**
         * This is what is does each time the page is brought up in
         * the application
         * @param url
         * @param resourceBundle
         */
        @Override
        public void initialize (URL url, ResourceBundle resourceBundle){
            try {
                loadArmyOne();
                loadArmyTwo();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

