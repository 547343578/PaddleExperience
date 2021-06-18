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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import DBAcess.ClubDBAccess;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class FXMLMainController implements Initializable {

    @FXML
    private ImageView imageView;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private GridPane gridPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image("/icons/fondoMain3.jpeg");
        imageView.setImage(image);

    }

    @FXML
    private void disponibilidad(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/paddleexperienceentregable/FXMLdisponibilidad.fxml"));
        AnchorPane root = (AnchorPane) miCargador.load();
        ControllerDisponibilidad miControlador = miCargador.getController();
        Scene scene = new Scene(root, 600, 400);
        new animatefx.animation.FadeIn(root).play();
        String css = this.getClass().getResource("flat.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void autenticarse(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/paddleexperienceentregable/FXMLautenticarse.fxml"));
        AnchorPane root = (AnchorPane) miCargador.load();
        ControllerAutenticarse miControlador = miCargador.getController();
        Scene scene = new Scene(root, 400, 300);
        new animatefx.animation.FadeIn(root).play();
        String css = this.getClass().getResource("flat.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private void registrarse(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/paddleexperienceentregable/FXMLregistrarse.fxml"));
        AnchorPane root = (AnchorPane) miCargador.load();
        ControllerRegistrarse miControlador = miCargador.getController();
        Scene scene = new Scene(root, 500, 630);
        new animatefx.animation.ZoomIn(root).play();
        String css = this.getClass().getResource("flat.css").toExternalForm();
        scene.getStylesheets().add(css);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}
