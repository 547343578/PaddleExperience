/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paddleexperienceentregable;

import DBAcess.ClubDBAccess;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Alex
 */
public class PaddleExperienceEntregable extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ClubDBAccess clubDBAccess;
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();

        Parent root = FXMLLoader.load(getClass().getResource("FXMLMain.fxml"));
        Scene scene = new Scene(root, 580, 400);
        stage.setScene(scene);
        new animatefx.animation.ZoomIn(root).play();
        String css = this.getClass().getResource("flat.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setMaxHeight(400);
        stage.setMaxWidth(580);
        stage.show();
        stage.setOnCloseRequest((WindowEvent event) -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(clubDBAccess.getClubName());
            alert.setHeaderText("Guardando datos en DB");
            alert.setContentText("La aplicacion esta guradando los cambios en la base de datos. Esta accion puede tardar algunos minutos");
            alert.show();
            clubDBAccess.saveDB();
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
