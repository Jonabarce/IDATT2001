package edu.ntnu.idatt2001.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;

public class UnitsPageController {


    private Scene scene;
    private Stage stage;

    /**
     * Method used to switch from current page UnitsPage to HomePage
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
     * Method used to switch from current page UnitsPage to ArmyPage
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToArmyPage(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ArmyPage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method used to switch from current page UnitsPage to SimulationPage
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToSimulationPage(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/SimulationPage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Method used when the user wants to close the application
     * If the user presses OK, then it will close down the application
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

}
