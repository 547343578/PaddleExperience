/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paddleexperienceentregable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class ControllerMenuReserva implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button boton2;
    @FXML
    private Button boton1;
    @FXML
    private GridPane gridPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        anchorPane.heightProperty().addListener((observable) -> {
            gridPane.setPrefHeight(anchorPane.getHeight());
        });
        anchorPane.widthProperty().addListener((observable) -> {
            gridPane.setPrefWidth(anchorPane.getWidth());
        });

        // TODO
    }

    @FXML
    private void reservar(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/paddleexperienceentregable/FXMLreservar.fxml"));
        AnchorPane root = (AnchorPane) miCargador.load();

        FXMLreservarController miControlador = miCargador.getController();

        Scene scene = new Scene(root, 700, 500);
        new animatefx.animation.ZoomIn(root).play();
        String css = this.getClass().getResource("flat.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setScene(scene);   
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void misReservas(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/paddleexperienceentregable/FXMLmisReservas.fxml"));
        AnchorPane root = (AnchorPane) miCargador.load();

        ControllerMisReservas miControlador = miCargador.getController();

        Scene scene = new Scene(root, 600, 450);
        new animatefx.animation.ZoomIn(root).play();
        String css = this.getClass().getResource("flat.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}
