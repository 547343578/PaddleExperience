/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paddleexperienceentregable;

import DBAcess.ClubDBAccess;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import model.Booking;
import model.Court;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class ControllerDisponibilidad implements Initializable {

    @FXML
    private Label fechaHoy;
    @FXML
    private TableView<Booking> tablaHoy;
    @FXML
    private TableColumn<Booking, String> hora;
    @FXML
    private TableColumn<Booking, String> login;
    @FXML
    private TableColumn<Booking, String> pista;
    @FXML
    private AnchorPane anchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        tablaHoy.widthProperty().addListener((observable) -> {
            hora.setPrefWidth((tablaHoy.getWidth()) / 3);
        });
        tablaHoy.widthProperty().addListener((observable) -> {
            login.setPrefWidth((tablaHoy.getWidth()) / 3);
        });
        tablaHoy.widthProperty().addListener((observable) -> {
            pista.setPrefWidth((tablaHoy.getWidth()) / 3);
        });

        ClubDBAccess clubDBAccess;
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        LocalDateTime today = LocalDateTime.now();
        String fecha = today.format(DateTimeFormatter.ISO_DATE);
        fechaHoy.setText(fecha);

        hora.setCellValueFactory(new PropertyValueFactory("fromTime"));
        login.setCellValueFactory(new PropertyValueFactory("member"));
        pista.setCellValueFactory(new PropertyValueFactory("court"));
        pista.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourt().getName()));
        login.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMember().getLogin()));


        ObservableList<Booking> observableBookings;
        observableBookings = FXCollections.observableList(clubDBAccess.getForDayBookings(LocalDate.now()));

        tablaHoy.setItems(observableBookings);

    }

}
