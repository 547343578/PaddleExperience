/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paddleexperienceentregable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.*;
import DBAcess.ClubDBAccess;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class ControllerRegistrarse implements Initializable {

    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField telefono;
    @FXML
    private TextField login;
    @FXML
    private TextField contraseña;
    @FXML
    private TextField numeroTarjeta;
    @FXML
    private TextField svc;
    @FXML
    private ImageView imagen;
    @FXML
    private AnchorPane anchorPane;

    private Stage primarystage;
    @FXML
    private GridPane gridPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void aceptar(ActionEvent event) {

        boolean comprobar = true;

        try {
            String name = nombre.getText();
            String surname = apellidos.getText();
            String telephone = telefono.getText();
            String login = this.login.getText();
            String password = contraseña.getText();
            String creditCard = numeroTarjeta.getText();
            String svc = this.svc.getText();
            Image image = imagen.getImage();

            if (!telephone.matches("[0-9]+")) {
                comprobar = false;
                System.out.println("no es un numero");
            } else {
                System.out.println("es un numero");
            }

            if (!login.matches("[a-zA-Z0-9]+")) {
                comprobar = false;
                System.out.println("no es valido");
            } else {
                System.out.println("es valido");
            }

            if (ClubDBAccess.getSingletonClubDBAccess().existsLogin(login)) {
                comprobar = false;
                System.out.println("existe miembro");
            }

            if (!password.matches("[a-zA-Z0-9]+") || password.length() < 6) {
                comprobar = false;
                System.out.println("no es valido contra");
            } else {
                System.out.println("es valido contra");
            }

            if ((creditCard.matches("[0-9]+") && creditCard.length() == 16) || creditCard.length() == 0) {
                System.out.println("es valido tarjeta");
            } else {
                comprobar = false;
                System.out.println("no es valido tarjeta");
            }

            if ((svc.matches("[0-9]+") && svc.length() == 3) || creditCard.length() == 0) {
                System.out.println("es valido svc" + "\n");
            } else {
                comprobar = false;
                System.out.println("no es valido svc" + "\n");
            }

            if (comprobar) {
                ClubDBAccess clubDBAccess;
                clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
                clubDBAccess.getMembers().add(new Member(name, surname, telephone, login, password, creditCard, svc, image));

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Informacion");
                alert.setContentText("se ha registrado correctamente, para reservar necesita autenticarse");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Campos incorrectos");
                alert.showAndWait();
            }


        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Campos incorrectos");
            alert.showAndWait();
        }
    }

    @FXML
    private void subirImagen(ActionEvent event) throws MalformedURLException, FileNotFoundException {

        FileChooser filechooser = new FileChooser();
        filechooser.setTitle("Image");
        File file = filechooser.showOpenDialog(primarystage);

        if (file != null && (file.getName().contains(".jpg") || file.getName().contains(".png"))) {
            Image image = new Image(file.toURI().toURL().toString());
            imagen.imageProperty().setValue(image);
        }

    }

    @FXML
    private void borrarLogin(MouseEvent event) {
        login.setText("");
    }

    @FXML
    private void borrarContraseña(MouseEvent event) {
        contraseña.setText("");
    }

    @FXML
    private void borrarTarjeta(MouseEvent event) {
        numeroTarjeta.setText("");
    }

    @FXML
    private void borrarSvc(MouseEvent event) {
        svc.setText("");
    }

}
