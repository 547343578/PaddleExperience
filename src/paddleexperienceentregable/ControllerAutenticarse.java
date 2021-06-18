/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paddleexperienceentregable;

import DBAcess.ClubDBAccess;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class ControllerAutenticarse implements Initializable {

    @FXML
    private PasswordField contraseña;
    @FXML
    private TextField usuario;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }

    @FXML
    private void login(ActionEvent event) throws IOException {

        String user = usuario.getText();
        String password = contraseña.getText();
        ClubDBAccess clubDBAccess;
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();

        if (clubDBAccess.existsLogin(user)) {

            if (clubDBAccess.getMemberByCredentials(user, password) != null) {
                FXMLreservarController.getLogin(usuario.getText(), contraseña.getText());
                ControllerMisReservas.getLogin(usuario.getText(), contraseña.getText());
                FXMLLoader miCargador = new FXMLLoader(getClass().getResource("/paddleexperienceentregable/FXMLMenuReserva.fxml"));
                AnchorPane root = (AnchorPane) miCargador.load();
                ControllerMenuReserva miControlador = miCargador.getController();
                Scene scene = new Scene(root, 600, 400);
                new animatefx.animation.ZoomIn(root).play();
                String css = this.getClass().getResource("flat.css").toExternalForm();
                scene.getStylesheets().add(css);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Contraseña incorrecta");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("No existe este usuario");
            alert.showAndWait();

        }
    }

}
