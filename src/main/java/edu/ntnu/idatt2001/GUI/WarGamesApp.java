package edu.ntnu.idatt2001.GUI;

import edu.ntnu.idatt2001.FileManagement;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.File;
import java.io.IOException;

public class WarGamesApp extends Application{

    /**
     * Method used when first starting the application
     * This method will make sure that if the user presses the exit button in the top corner
     * if will make sure the user wants to close the application
     * If the user presses OK, then it will close the application
     * @param stage the stage that is starts on
     * @throws Exception if the fxml file is not found
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("WarGames");
        stage.setResizable(false);
        stage.show();

        stage.setOnCloseRequest(event -> {
            event.consume();
            closeProgram(stage);
            try {
                FileManagement.clearFile(new File("src/main/resources/Files/WinnerArmy.csv"));
                FileManagement.clearFile(new File("src/main/resources/Files/LoserArmy.csv"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    /**
     * Method used if the user wants to close the application
     * If the user presses OK, then it will lose down the application
     * @param stage the relevant stage
     */
    public void closeProgram(Stage stage) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit game");
        alert.setHeaderText("You're about to exit the game");
        alert.setContentText("Are you sure you want to exit?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            stage.close();
        }


    }
}