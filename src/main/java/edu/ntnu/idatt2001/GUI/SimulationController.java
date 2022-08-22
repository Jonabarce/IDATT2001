package edu.ntnu.idatt2001.GUI;

import edu.ntnu.idatt2001.Army;
import edu.ntnu.idatt2001.Battle;
import edu.ntnu.idatt2001.FileManagement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SimulationController implements Initializable {

    @FXML
    public CheckBox checkboxHILL;
    public CheckBox checkboxFOREST;
    public CheckBox checkboxPLAIN;
    public CheckBox checkboxNOTERRAIN;
    public Button seeResultButton;
    public AnchorPane forDisplayingTerrain;



    private Scene scene;
    private Stage stage;
    public Army armyOne;
    public Army armyTwo;
    public Label winner;
    public Label winnerTextLabel;
    public Label whoIsFighting;
    public Label currentlyTerrain;

    /**
     * Method to switch from current page SimulationPage to HomePage
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToHomePage(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to switch from current page SimulationPage to UnitsPage
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToUnitsPage(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/UnitsPage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method to switch from current page SimulationPage to ResultPage
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToResultPage(ActionEvent event)throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ResultPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Method used to when the user wants to close the application
     * If the user presses Ok, then it will close the application
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void closeProgram(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit game");
        alert.setHeaderText("You're about to exit the game");
        alert.setContentText("Are you sure you want to exit?");

        if(alert.showAndWait().get() == ButtonType.OK){
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.close();
        }
    }

    /**
     * Method to switch from current page SimulationPage to ArmyPage
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToArmiesPage(ActionEvent event) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ArmyPage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Help button for choosing a terrain
     */
    public void helpButton() {
        AlertBox.displayAlertBox("Terrain", "Some units have more benefits than others on different terrain" + "\n" +
        "See units stats for more info");
    }

    /**
     * Method used to set the currently selected Terrain and then store it in a file
     * for later use
     * @param terrain the terrain chosen by the user
     * @throws IOException if the file is not found
     */
    public void saveTerrain(String terrain) throws IOException {
        forDisplayingTerrain.getStyleClass().clear();
        forDisplayingTerrain.getStyleClass().add(terrain + "Picture");
        FileManagement.writeName(new File("src/main/resources/Files/Terrain.csv"),terrain);
        currentlyTerrain.setText("Currently selected terrain: " + terrain);
    }

    /**
     * Method used to set the terrain the user wants the battle to be on
     * It calls on the method saveTerrain in order to save and store what kind of
     * terrain the user has chosen
     * @throws IOException if the file is not found in the saveTerrain method
     */
    public void setTerrain() throws IOException {
        String terrain;
        if(checkboxFOREST.isSelected()){
            terrain = "forest";
            saveTerrain(terrain);
        }else if(checkboxHILL.isSelected()){
            terrain = "hill";
            saveTerrain(terrain);
        }else if(checkboxPLAIN.isSelected()){
            terrain = "plain";
            saveTerrain(terrain);
        }else if(checkboxNOTERRAIN.isSelected()){
            terrain = "noTerrain";
            saveTerrain(terrain);
        }else if(!checkboxFOREST.isSelected() || !checkboxPLAIN.isSelected() || !checkboxHILL.isSelected() || !checkboxNOTERRAIN.isSelected()){
            AlertBox.displayAlertBox("Error!", "Please select a terrain");
        }
    }


    /**
     * Method used to display the last chosen and stored terrain
     * each time the page is brought up(Since the method is called on in initialize)
     * @throws IOException if the file is not found in both this method and saveTerrain
     */
    public void displayTerrain() throws IOException {
        String terrain = FileManagement.readName(new File("src/main/resources/Files/Terrain.csv"));
        if(terrain.equalsIgnoreCase("forest")){
            terrain = "forest";
            saveTerrain(terrain);
        }else if(terrain.equalsIgnoreCase("plain")){
            terrain = "plain";
            saveTerrain(terrain);
        }else if(terrain.equalsIgnoreCase("hill")){
            terrain = "hill";
            saveTerrain(terrain);
        }else if(terrain.equalsIgnoreCase("noterrain")){
            terrain = "noTerrain";
            saveTerrain(terrain);
        }else{
            AlertBox.displayAlertBox("Error!", "No terrain was found");
        }
    }

    /**
     * Method used to start a simulation
     * WinnerArmy will always be battle.simulate since that method returns
     * the winner of the battle
     * Both WinnerArmy and LoserArmy is stored in files,
     * so it's easy to display them in the result page
     * @throws IOException if the file is not found
     */
    @FXML
    public void startSimulation()throws IOException {
        armyOne = FileManagement.readArmyFromFile(new File("src/main/resources/Files/ArmyOne.csv"));
        armyTwo = FileManagement.readArmyFromFile(new File("src/main/resources/Files/ArmyTwo.csv"));
        String terrain = FileManagement.readName(new File("src/main/resources/Files/Terrain.csv"));
        Battle battle = new Battle(armyOne, armyTwo);
        Army winnerArmy;


        winnerArmy = battle.simulate(terrain);
        Army loserArmy;
        if(winnerArmy.getName().equals(armyOne.getName())){
            loserArmy = new Army(armyTwo.getName());
            armyTwo = loserArmy;
        }else{
            loserArmy = new Army(armyOne.getName());
            armyOne = loserArmy;
        }
        winner.setText(winnerArmy.getName());
        winnerTextLabel.setText("The winner is: ");
        saveWinnerAndLoser(winnerArmy,loserArmy);
        seeResultButton.setVisible(true);
    }

    /**
     * Method used to save the winner and loser army
     * @param winnerArmy the army that won the battle
     * @param loserArmy the army that lost the battle
     * @throws IOException if the file is not found
     */
    public void saveWinnerAndLoser(Army winnerArmy,Army loserArmy) throws IOException {
        FileManagement.writeArmyToFile(winnerArmy,new File("src/main/resources/Files/WinnerArmy.csv"));
        FileManagement.writeArmyToFile(loserArmy,new File("src/main/resources/Files/LoserArmy.csv"));
    }

    /**
     * Each time the page is brought up it reads both army one and army two
     * It does also call on the method displayTerrain to display the last chosen terrain
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            armyOne = FileManagement.readArmyFromFile(new File("src/main/resources/Files/ArmyOne.csv"));
            armyTwo = FileManagement.readArmyFromFile(new File("src/main/resources/Files/ArmyTwo.csv"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        seeResultButton.setVisible(false);
        whoIsFighting.setText(armyOne.getName() + " VS " + armyTwo.getName());
        winnerTextLabel.setText("");
        try {
            currentlyTerrain.setText("Currently selected terrain: " + "" + FileManagement.readName(new File("src/main/resources/Files/Terrain.csv")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            displayTerrain();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}