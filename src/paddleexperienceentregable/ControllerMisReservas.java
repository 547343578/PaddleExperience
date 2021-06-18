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
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.Booking;
import model.Court;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class ControllerMisReservas implements Initializable {

    @FXML
    private TableView<Booking> tabla;
    @FXML
    private TableColumn<Booking, String> dia;
    @FXML
    private TableColumn<Booking, String> pista;
    @FXML
    private TableColumn<Booking, String> horaInicio;
    @FXML
    private TableColumn<Booking, String> horaFin;
    @FXML
    private TableColumn<Booking, String> pagado;

    protected static String user;
    protected static String password;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Button eliminar;
    private GridPane gridPane;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        tabla.widthProperty().addListener((observable) -> {
            dia.setPrefWidth((tabla.getWidth()) / 5);
        });
        tabla.widthProperty().addListener((observable) -> {
            pista.setPrefWidth((tabla.getWidth()) / 5);
        });
        tabla.widthProperty().addListener((observable) -> {
            horaInicio.setPrefWidth((tabla.getWidth()) / 5);
        });
        tabla.widthProperty().addListener((observable) -> {
            horaFin.setPrefWidth((tabla.getWidth()) / 5);
        });
        tabla.widthProperty().addListener((observable) -> {
            pagado.setPrefWidth((tabla.getWidth()) / 5);
        });

        ClubDBAccess clubDBAccess;
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();

        dia.setCellValueFactory(new PropertyValueFactory("madeForDay"));
        pista.setCellValueFactory(new PropertyValueFactory("court"));
        horaInicio.setCellValueFactory(new PropertyValueFactory("fromTime"));
        horaFin.setCellValueFactory(new PropertyValueFactory("toTime"));
        pagado.setCellValueFactory(new PropertyValueFactory("paid"));
        pista.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCourt().getName()));
        horaFin.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFromTime().plusMinutes(90).toString()));

        ObservableList<Booking> observableBookings;
        if (clubDBAccess.getUserBookings(user).size() < 10) {
            observableBookings = FXCollections.observableList(clubDBAccess.getUserBookings(user));
        } else {
            observableBookings = FXCollections.observableList(clubDBAccess.getUserBookings(user).subList(clubDBAccess.getUserBookings(user).size() - 10, clubDBAccess.getUserBookings(user).size()));
        }

        tabla.setItems(observableBookings);
    }

    public static void getLogin(String login, String password) {
        ControllerMisReservas.user = login;
        ControllerMisReservas.password = password;
    }

    @FXML
    private void eliminar(ActionEvent event) {

        ClubDBAccess clubDBAccess;
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        ObservableList<Booking> reservas;
        reservas = FXCollections.observableList(clubDBAccess.getBookings());

        if (tabla.getSelectionModel().getSelectedItem() == null) {
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setTitle("No has seleccionado ninguno");
            alerta.setContentText("Por favor seleccione uno si quiere reservar");
            alerta.showAndWait();
        } else {
            LocalDate localDate = tabla.getSelectionModel().getSelectedItem().getMadeForDay();
            LocalTime localTime = tabla.getSelectionModel().getSelectedItem().getFromTime();
            LocalDateTime diaAct24 = LocalDateTime.now().plusDays(1);
            LocalDateTime diaReserva = LocalDateTime.of(localDate, localTime);
            System.out.println(diaReserva.compareTo(diaAct24));
            if (diaReserva.compareTo(diaAct24) > 0) {
                Booking b = tabla.getSelectionModel().getSelectedItem();
                reservas.remove(b);

                Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Enhorabuena");
                alerta.setContentText("Se ha cancelado la reserva correctamente");
                alerta.showAndWait();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("No se pudo cancelar la reserva");
                alerta.setContentText("Su reserva es menor de 24h");
                alerta.showAndWait();
            }
        }
        tabla.refresh();
        clubDBAccess.saveDB();
        ObservableList<Booking> observableBookings;
        if (clubDBAccess.getUserBookings(user).size() < 10) {
            observableBookings = FXCollections.observableList(clubDBAccess.getUserBookings(user));
        } else {
            observableBookings = FXCollections.observableList(clubDBAccess.getUserBookings(user).subList(clubDBAccess.getUserBookings(user).size() - 10, clubDBAccess.getUserBookings(user).size()));
        }
        tabla.setItems(observableBookings);
    }

}
