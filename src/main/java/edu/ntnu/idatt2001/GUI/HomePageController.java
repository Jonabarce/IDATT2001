package edu.ntnu.idatt2001.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;



public class HomePageController {

    private Scene scene;
    private Stage stage;


    /**
     * Method to switch from current page FrontPage to UnitsPage
     * @param event event by pressing a button
     * @throws IOException if the fxml file is not found
     */
    public void switchToUnitsPage(ActionEvent event)throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/UnitsPage.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Alertbox for when user is pressing the help button
     */
    public void helpButton(){
        AlertBox.displayAlertBox("Help","Press start game to play");
    }

    /**
     * Alertbox for when user is pressing the credit button
     */
    public void creditButton(){
        AlertBox.displayAlertBox("Credits", """
                All terrains used in the application
                are downloaded for free from: https://unsplash.com/
                
                Pictures used for background at homepage, background at unit stats page
                and the pictures for the units are downloaded from:
                https://wowpedia.fandom.com/
                They belong to Blizzard Entertainment Inc and are free to use
                for personal use""");
    }


    /**
     * Method to switch from current page FrontPage to ArmyPage
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
     * Method used when user is hitting the exit button
     * Application will close down if user hits buttonType OK
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
}


