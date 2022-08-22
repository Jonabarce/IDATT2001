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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResultController implements Initializable {

    @FXML
    public ListView<String> theWinnerArmy;
    public ListView<String> theLoserArmy;
    public Label nameForArmyWinner;
    public Label nameForArmyLoser;
    public Label nameForWinnerForDisplayingInfo;
    public Label totalUnits;
    public Label totalUnitsOfEachType;



    private Scene scene;
    private Stage stage;
    public Army winnerArmy;
    public String loserArmy;


    /**
     * Method to switch from current page ResultPage to SimulationPage
     * If the used presses ok it will switch page, and it will clear the result from the last battle
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToSimulationPage(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit result page");
        alert.setHeaderText("You're about to leave the result page");
        alert.setContentText("Leaving this page will delete the result");

        if (alert.showAndWait().get() == ButtonType.OK) {
            FileManagement.clearFile(new File("src/main/resources/Files/WinnerArmy.csv"));
            FileManagement.clearFile(new File("src/main/resources/Files/LoserArmy.csv"));
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/SimulationPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
    /**
     * Method to switch from current page ResultPage to HomePage
     * If the used presses ok it will switch page, and it will clear the result from the last battle
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToHomePage(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit result page");
        alert.setHeaderText("You're about to leave the result page");
        alert.setContentText("Leaving this page will delete the result");

        if (alert.showAndWait().get() == ButtonType.OK) {
            FileManagement.clearFile(new File("src/main/resources/Files/WinnerArmy.csv"));
            FileManagement.clearFile(new File("src/main/resources/Files/LoserArmy.csv"));
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Method to switch from current page ResultPage to ArmyPage
     * If the used presses ok it will switch page, and it will clear the result from the last battle
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToArmiesPage(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit result page");
        alert.setHeaderText("You're about to leave the result page");
        alert.setContentText("Are you sure you want to leave? " + "\n" + "Leaving this page will delete the result");

        if (alert.showAndWait().get() == ButtonType.OK) {
            FileManagement.clearFile(new File("src/main/resources/Files/WinnerArmy.csv"));
            FileManagement.clearFile(new File("src/main/resources/Files/LoserArmy.csv"));
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/ArmyPage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


    /**
     * Method to used if the user wants to close the application
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void closeProgram(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit game");
        alert.setHeaderText("You're about to exit the game");
        alert.setContentText("Exiting will delete the result");

        if (alert.showAndWait().get() == ButtonType.OK) {
            Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.close();
        }
    }

    /**
     * Method used to load and display the winner army after the battle
     * Displaying the army in a listView is done with the method displayWinnerArmy
     * @throws IOException if the file is not found
     */
    @FXML
    public void loadWinnerArmy() throws IOException {
        winnerArmy = FileManagement.readArmyFromFile(new File("src/main/resources/Files/WinnerArmy.csv"));
        nameForArmyWinner.setText(winnerArmy.getName());
        displayWinnerArmy(winnerArmy);
        nameForWinnerForDisplayingInfo.setText(winnerArmy.getName());
        totalUnits.setText(winnerArmy.getArmySize());
        totalUnitsOfEachType.setText(winnerArmy.getTotalNumberOfEachUnit());
    }

    /**
     * Method used to load and display the loser army after the battle
     * @throws IOException if the file is not found
     */
    @FXML
    public void loadLoserArmy() throws IOException {
        loserArmy = FileManagement.readName(new File("src/main/resources/Files/LoserArmy.csv"));
        nameForArmyLoser.setText(loserArmy);
        String noUnits = "All dead...";
        theLoserArmy.getItems().clear();
        theLoserArmy.getItems().add(noUnits);
    }

    /**
     * Method used to display the army in the ListView
     * @param army the army that is being displayed
     */
    public void displayWinnerArmy(Army army){
        StringBuilder f = new StringBuilder();
        theWinnerArmy.getItems().clear();
        for (Unit unit : army.getAllUnits()) {
            f.append(unit.getClass().getSimpleName()).append(",").append(unit.getName()).append(",").append(unit.getHealth()).append(" health"+"\n");
        }
        theWinnerArmy.getItems().addAll(String.valueOf(f));
    }

    /**
     * Each time this page is brought up
     * loadWinnerArmy and loadLoserArmy is called on
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadWinnerArmy();
            loadLoserArmy();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
