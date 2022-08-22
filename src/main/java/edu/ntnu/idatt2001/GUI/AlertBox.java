package edu.ntnu.idatt2001.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class AlertBox{

    /**
     *
     * @param titleOfAlert
     * @param messageOfAlert
     */
    public static void displayAlertBox(String titleOfAlert, String messageOfAlert) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(titleOfAlert);
        window.setMinWidth(251);
        window.setMinHeight(251);

        Label label = new Label();
        label.setText(messageOfAlert);

        Button close = new Button("Ok");
        close.setOnAction(e -> window.close());

        VBox layout = new VBox(11);
        layout.getChildren().addAll(label, close);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}

