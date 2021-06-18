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
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Booking;
import model.Court;
import model.Member;

/**
 * FXML Controller class
 *
 * @author Alex
 */
public class FXMLreservarController implements Initializable {

    private DatePicker dpBookingDay;
    @FXML
    private ImageView imagen;
    private TableView<Booking> tabla;
    private TableColumn<Booking, String> horaInicio;
    private TableColumn<Booking, String> horaFin;
    private TableColumn<Booking, String> pista;
    private TableColumn<Booking, String> login;
    @FXML
    private DatePicker dpBookingDay1;
    @FXML
    private TableView<Booking> tabla1;
    @FXML
    private Button reservar1;
    @FXML
    private DatePicker dpBookingDay2;
    @FXML
    private TableView<Booking> tabla2;
    @FXML
    private Button reservar2;
    @FXML
    private DatePicker dpBookingDay3;
    @FXML
    private TableView<Booking> tabla3;
    @FXML
    private Button reservar3;
    @FXML
    private DatePicker dpBookingDay4;
    @FXML
    private TableView<Booking> tabla4;
    @FXML
    private Button reservar4;
    @FXML
    private TableColumn<Booking, String> horaInicio1;
    @FXML
    private TableColumn<Booking, String> horaFin1;
    @FXML
    private TableColumn<Booking, String> login1;
    @FXML
    private TableColumn<Booking, String> horaInicio2;
    @FXML
    private TableColumn<Booking, String> horaFin2;
    @FXML
    private TableColumn<Booking, String> login2;
    @FXML
    private TableColumn<Booking, String> horaInicio3;
    @FXML
    private TableColumn<Booking, String> horaFin3;
    @FXML
    private TableColumn<Booking, String> login3;
    @FXML
    private TableColumn<Booking, String> horaInicio4;
    @FXML
    private TableColumn<Booking, String> horaFin4;
    @FXML
    private TableColumn<Booking, String> login4;

    protected static String user;
    protected static String password;
    Stage window;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private ImageView imagen2;
    @FXML
    private ImageView imagen3;
    @FXML
    private ImageView imagen4;
    @FXML
    private TabPane tab1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image image = new Image("/icons/pistaAmarilla.jpg");
        imagen.setImage(image);
        Image image2 = new Image("/icons/pistaRoja.jpg");
        imagen2.setImage(image2);
        Image image3 = new Image("/icons/pistaVioleta.jpg");
        imagen3.setImage(image3);
        Image image4 = new Image("/icons/pistaverde.jpg");
        imagen4.setImage(image4);

        tabla1.widthProperty().addListener((observable) -> {
            horaInicio1.setPrefWidth((tabla1.getWidth()) / 3);
        });
        tabla1.widthProperty().addListener((observable) -> {
            horaFin1.setPrefWidth((tabla1.getWidth()) / 3);
        });
        tabla1.widthProperty().addListener((observable) -> {
            login1.setPrefWidth((tabla1.getWidth()) / 3);
        });
        //--------------------------------------------------------
        //pista 2
        tabla2.widthProperty().addListener((observable) -> {
            horaInicio2.setPrefWidth((tabla2.getWidth()) / 3);
        });
        tabla2.widthProperty().addListener((observable) -> {
            horaFin2.setPrefWidth((tabla2.getWidth()) / 3);
        });
        tabla2.widthProperty().addListener((observable) -> {
            login2.setPrefWidth((tabla2.getWidth()) / 3);
        });
        //---------------------------------------------------------------------------
        //pista 3

        tabla3.widthProperty().addListener((observable) -> {
            horaInicio3.setPrefWidth((tabla3.getWidth()) / 3);
        });
        tabla3.widthProperty().addListener((observable) -> {
            horaFin3.setPrefWidth((tabla3.getWidth()) / 3);
        });
        tabla3.widthProperty().addListener((observable) -> {
            login3.setPrefWidth((tabla3.getWidth()) / 3);
        });
        //------------------------------------------------------------------------
        //pista 4

        tabla4.widthProperty().addListener((observable) -> {
            horaInicio4.setPrefWidth((tabla4.getWidth()) / 3);
        });
        tabla4.widthProperty().addListener((observable) -> {
            horaFin4.setPrefWidth((tabla4.getWidth()) / 3);
        });
        tabla4.widthProperty().addListener((observable) -> {
            login4.setPrefWidth((tabla4.getWidth()) / 3);
        });
        //---------------------------------------------------------------------------------------------------

        dpBookingDay1.setDayCellFactory((DatePicker picker) -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) < 0);
                }
            };
        });

        ClubDBAccess clubDBAccess;
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();

        horaInicio1.setCellValueFactory(new PropertyValueFactory("fromTime"));
        horaFin1.setCellValueFactory(new PropertyValueFactory("toTime"));
        login1.setCellValueFactory(new PropertyValueFactory("member"));
        horaFin1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFromTime().plusMinutes(90).toString()));
        login1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMember().getLogin()));

        ObservableList<Booking> reservas;
        reservas = FXCollections.observableList(clubDBAccess.getCourtBookings(clubDBAccess.getCourts().get(0).getName(), LocalDate.now()));
        dpBookingDay1.setValue(LocalDate.now());
        
        ObservableList<Booking> observableBookings;
        observableBookings = FXCollections.observableList(new ArrayList<>());

        LocalTime timeInicio = LocalTime.of(9, 0);

        for (int i = 0; i < clubDBAccess.getClubBookingSlots(); i++) {

            if (!reservas.isEmpty()) {
                Booking booking = reservas.get(0);
                if (booking.getFromTime().equals(timeInicio.plusMinutes(i * 90))) {
                    observableBookings.add(booking);
                    reservas.remove(0);

                } else {
                    observableBookings.add(new Booking(null, LocalDate.now(), timeInicio.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(0), new Member("", "", "", "disponible", "", "", "", null)));
                }
            } else {
                observableBookings.add(new Booking(null, LocalDate.now(), timeInicio.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(0), new Member("", "", "", "disponible", "", "", "", null)));
            }
        }

        tabla1.setItems(observableBookings);

        //------------------------------------------------------------------------------------------------------------------------------------------------------
      
        dpBookingDay2.setDayCellFactory((DatePicker picker) -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) < 0);
                }
            };
        });

        horaInicio2.setCellValueFactory(new PropertyValueFactory("fromTime"));
        horaFin2.setCellValueFactory(new PropertyValueFactory("toTime"));
        login2.setCellValueFactory(new PropertyValueFactory("member"));
        horaFin2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFromTime().plusMinutes(90).toString()));
        login2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMember().getLogin()));

        ObservableList<Booking> reservas2;
        reservas2 = FXCollections.observableList(clubDBAccess.getCourtBookings(clubDBAccess.getCourts().get(1).getName(), LocalDate.now()));
        dpBookingDay2.setValue(LocalDate.now());
        
        ObservableList<Booking> observableBookings2;
        observableBookings2 = FXCollections.observableList(new ArrayList<>());

        LocalTime timeInicio2 = LocalTime.of(9, 0);

        for (int i = 0; i < clubDBAccess.getClubBookingSlots(); i++) {

            if (!reservas2.isEmpty()) {
                Booking booking = reservas2.get(0);
                if (booking.getFromTime().equals(timeInicio2.plusMinutes(i * 90))) {
                    observableBookings2.add(booking);
                    reservas2.remove(0);

                } else {
                    observableBookings2.add(new Booking(null, LocalDate.now(), timeInicio2.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(1), new Member("", "", "", "disponible", "", "", "", null)));
                }
            } else {
                observableBookings2.add(new Booking(null, LocalDate.now(), timeInicio2.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(1), new Member("", "", "", "disponible", "", "", "", null)));
            }
        }

        tabla2.setItems(observableBookings2);
        //------------------------------------------------------------------------------------------------------------------------------------------------------
        
        dpBookingDay3.setDayCellFactory((DatePicker picker) -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) < 0);
                }
            };
        });

        horaInicio3.setCellValueFactory(new PropertyValueFactory("fromTime"));
        horaFin3.setCellValueFactory(new PropertyValueFactory("toTime"));
        login3.setCellValueFactory(new PropertyValueFactory("member"));
        horaFin3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFromTime().plusMinutes(90).toString()));
        login3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMember().getLogin()));

        ObservableList<Booking> reservas3;
        reservas3 = FXCollections.observableList(clubDBAccess.getCourtBookings(clubDBAccess.getCourts().get(2).getName(), LocalDate.now()));
        dpBookingDay3.setValue(LocalDate.now());
       
        ObservableList<Booking> observableBookings3;
        observableBookings3 = FXCollections.observableList(new ArrayList<>());

        LocalTime timeInicio3 = LocalTime.of(9, 0);

        for (int i = 0; i < clubDBAccess.getClubBookingSlots(); i++) {

            if (!reservas3.isEmpty()) {
                Booking booking = reservas3.get(0);
                if (booking.getFromTime().equals(timeInicio3.plusMinutes(i * 90))) {
                    observableBookings3.add(booking);
                    reservas3.remove(0);

                } else {
                    observableBookings3.add(new Booking(null, LocalDate.now(), timeInicio3.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(2), new Member("", "", "", "disponible", "", "", "", null)));
                }
            } else {
                observableBookings3.add(new Booking(null, LocalDate.now(), timeInicio3.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(2), new Member("", "", "", "disponible", "", "", "", null)));
            }
        }

        tabla3.setItems(observableBookings3);

        //------------------------------------------------------------------------------------------------------------------------------------------------------
        
        dpBookingDay4.setDayCellFactory((DatePicker picker) -> {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);
                    LocalDate today = LocalDate.now();
                    setDisable(empty || date.compareTo(today) < 0);
                }
            };
        });

        horaInicio4.setCellValueFactory(new PropertyValueFactory("fromTime"));
        horaFin4.setCellValueFactory(new PropertyValueFactory("toTime"));
        login4.setCellValueFactory(new PropertyValueFactory("member"));
        horaFin4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFromTime().plusMinutes(90).toString()));
        login4.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMember().getLogin()));

        ObservableList<Booking> reservas4;
        reservas4 = FXCollections.observableList(clubDBAccess.getCourtBookings(clubDBAccess.getCourts().get(3).getName(), LocalDate.now()));

        
        ObservableList<Booking> observableBookings4;
        observableBookings4 = FXCollections.observableList(new ArrayList<>());
        dpBookingDay4.setValue(LocalDate.now());
        LocalTime timeInicio4 = LocalTime.of(9, 0);

        for (int i = 0; i < clubDBAccess.getClubBookingSlots(); i++) {

            if (!reservas4.isEmpty()) {
                Booking booking = reservas4.get(0);
                if (booking.getFromTime().equals(timeInicio4.plusMinutes(i * 90))) {
                    observableBookings4.add(booking);
                    reservas4.remove(0);

                } else {
                    observableBookings4.add(new Booking(null, LocalDate.now(), timeInicio4.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(3), new Member("", "", "", "disponible", "", "", "", null)));
                }
            } else {
                observableBookings4.add(new Booking(null, LocalDate.now(), timeInicio4.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(3), new Member("", "", "", "disponible", "", "", "", null)));
            }
        }

        tabla4.setItems(observableBookings4);

    }

    @FXML
    private void reservar(ActionEvent event) {

        Button button = (Button) event.getSource();
        String aux = button.getId();
        ClubDBAccess clubDBAccess;
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        ObservableList<Booking> reservas;
        reservas = FXCollections.observableList(clubDBAccess.getBookings());
        switch (aux) {
            case ("reservar1"):

                if (tabla1.getSelectionModel().getSelectedItem() == null) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("No has seleccionado ninguno");
                    alerta.setContentText("Por favor seleccione uno si quiere reservar");
                    alerta.showAndWait();
                } else {
                    LocalDateTime bookingDate1 = tabla1.getSelectionModel().getSelectedItem().getBookingDate();
                    LocalDateTime bookingDate2 = LocalDateTime.now();
                    Court court = tabla1.getSelectionModel().getSelectedItem().getCourt();
                    LocalTime fromTime = tabla1.getSelectionModel().getSelectedItem().getFromTime();
                    LocalDate madeForDay1 = tabla1.getSelectionModel().getSelectedItem().getMadeForDay();
                    LocalDate madeForDay2 = dpBookingDay1.getValue();
                    Member member = tabla1.getSelectionModel().getSelectedItem().getMember();
                    Boolean paid = tabla1.getSelectionModel().getSelectedItem().getPaid();
                    Booking booking = new Booking(bookingDate1, madeForDay1, fromTime, paid, court, member);
                    Booking bookingModificar = new Booking(bookingDate2, madeForDay2, fromTime, paid, court, clubDBAccess.getMemberByCredentials(user, password));

                    //esta reservado por user mismo
                    if (member.getLogin().equals(user)) {
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Information");
                        alerta.setContentText("Ya habia reservado esta pista");
                        alerta.showAndWait();
                    } else {
                        //esta reservado por otros users
                        if (!booking.getMember().getLogin().equals("disponible")) {
                            Alert alerta = new Alert(Alert.AlertType.ERROR);
                            alerta.setTitle("Pista reservada");
                            alerta.setContentText("Por favor,selecciona otro");
                            alerta.showAndWait();

                        } else {
                            //esta disponible                    
                            reservas.add(bookingModificar);
                            if (clubDBAccess.hasCreditCard(user)) {
                                reservas.get(reservas.indexOf(bookingModificar)).setPaid(true);
                            }
                            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                            alerta.setTitle("Enhorabuena");
                            alerta.setContentText("Se ha reservado la pista correctamente");
                            alerta.showAndWait();
                            tabla1.getSelectionModel().getSelectedItem().setMember(clubDBAccess.getMemberByCredentials(user, password));
                        }
                    }
                }
                clubDBAccess.saveDB();
                tabla1.refresh();
                break;

            case ("reservar2"):

                if (tabla2.getSelectionModel().getSelectedItem() == null) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("No has seleccionado ninguno");
                    alerta.setContentText("Por favor seleccione uno si quiere reservar");
                    alerta.showAndWait();
                } else {
                    LocalDateTime bookingDate1 = tabla2.getSelectionModel().getSelectedItem().getBookingDate();
                    LocalDateTime bookingDate2 = LocalDateTime.now();
                    Court court = tabla2.getSelectionModel().getSelectedItem().getCourt();
                    LocalTime fromTime = tabla2.getSelectionModel().getSelectedItem().getFromTime();
                    LocalDate madeForDay1 = tabla2.getSelectionModel().getSelectedItem().getMadeForDay();
                    LocalDate madeForDay2 = dpBookingDay2.getValue();
                    Member member = tabla2.getSelectionModel().getSelectedItem().getMember();
                    Boolean paid = tabla2.getSelectionModel().getSelectedItem().getPaid();
                    Booking booking = new Booking(bookingDate1, madeForDay1, fromTime, paid, court, member);
                    Booking bookingModificar = new Booking(bookingDate2, madeForDay2, fromTime, paid, court, clubDBAccess.getMemberByCredentials(user, password));

                    //esta reservado por user mismo
                    if (member.getLogin().equals(user)) {
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Information");
                        alerta.setContentText("Ya habia reservado esta pista");
                        alerta.showAndWait();
                    } else {
                        //esta reservado por otros users
                        if (!booking.getMember().getLogin().equals("disponible")) {
                            Alert alerta = new Alert(Alert.AlertType.ERROR);
                            alerta.setTitle("Pista reservada");
                            alerta.setContentText("Por favor,selecciona otro");
                            alerta.showAndWait();

                        } else {
                            //esta disponible                    
                            reservas.add(bookingModificar);
                            if (clubDBAccess.hasCreditCard(user)) {
                                reservas.get(reservas.indexOf(bookingModificar)).setPaid(true);
                            }
                            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                            alerta.setTitle("Enhorabuena");
                            alerta.setContentText("Se ha reservado la pista correctamente");
                            alerta.showAndWait();
                            tabla2.getSelectionModel().getSelectedItem().setMember(clubDBAccess.getMemberByCredentials(user, password));
                        }
                    }
                }
                clubDBAccess.saveDB();
                tabla2.refresh();
                break;
            case ("reservar3"):

                if (tabla3.getSelectionModel().getSelectedItem() == null) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("No has seleccionado ninguno");
                    alerta.setContentText("Por favor seleccione uno si quiere reservar");
                    alerta.showAndWait();
                } else {
                    LocalDateTime bookingDate1 = tabla3.getSelectionModel().getSelectedItem().getBookingDate();
                    LocalDateTime bookingDate2 = LocalDateTime.now();
                    Court court = tabla3.getSelectionModel().getSelectedItem().getCourt();
                    LocalTime fromTime = tabla3.getSelectionModel().getSelectedItem().getFromTime();
                    LocalDate madeForDay1 = tabla3.getSelectionModel().getSelectedItem().getMadeForDay();
                    LocalDate madeForDay2 = dpBookingDay3.getValue();
                    Member member = tabla3.getSelectionModel().getSelectedItem().getMember();
                    Boolean paid = tabla3.getSelectionModel().getSelectedItem().getPaid();
                    Booking booking = new Booking(bookingDate1, madeForDay1, fromTime, paid, court, member);
                    Booking bookingModificar = new Booking(bookingDate2, madeForDay2, fromTime, paid, court, clubDBAccess.getMemberByCredentials(user, password));

                    //esta reservado por user mismo
                    if (member.getLogin().equals(user)) {
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Information");
                        alerta.setContentText("Ya habia reservado esta pista");
                        alerta.showAndWait();
                    } else {
                        //esta reservado por otros users
                        if (!booking.getMember().getLogin().equals("disponible")) {
                            Alert alerta = new Alert(Alert.AlertType.ERROR);
                            alerta.setTitle("Pista reservada");
                            alerta.setContentText("Por favor,selecciona otro");
                            alerta.showAndWait();

                        } else {
                            //esta disponible                    
                            reservas.add(bookingModificar);
                            if (clubDBAccess.hasCreditCard(user)) {
                                reservas.get(reservas.indexOf(bookingModificar)).setPaid(true);
                            }
                            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                            alerta.setTitle("Enhorabuena");
                            alerta.setContentText("Se ha reservado la pista correctamente");
                            alerta.showAndWait();
                            tabla3.getSelectionModel().getSelectedItem().setMember(clubDBAccess.getMemberByCredentials(user, password));
                        }
                    }
                }
                clubDBAccess.saveDB();
                tabla3.refresh();
                break;
            case ("reservar4"):

                if (tabla4.getSelectionModel().getSelectedItem() == null) {
                    Alert alerta = new Alert(Alert.AlertType.ERROR);
                    alerta.setTitle("No has seleccionado ninguno");
                    alerta.setContentText("Por favor seleccione uno si quiere reservar");
                    alerta.showAndWait();
                } else {
                    LocalDateTime bookingDate1 = tabla4.getSelectionModel().getSelectedItem().getBookingDate();
                    LocalDateTime bookingDate2 = LocalDateTime.now();
                    Court court = tabla4.getSelectionModel().getSelectedItem().getCourt();
                    LocalTime fromTime = tabla4.getSelectionModel().getSelectedItem().getFromTime();
                    LocalDate madeForDay1 = tabla4.getSelectionModel().getSelectedItem().getMadeForDay();
                    LocalDate madeForDay2 = dpBookingDay4.getValue();
                    Member member = tabla4.getSelectionModel().getSelectedItem().getMember();
                    Boolean paid = tabla4.getSelectionModel().getSelectedItem().getPaid();
                    Booking booking = new Booking(bookingDate1, madeForDay1, fromTime, paid, court, member);
                    Booking bookingModificar = new Booking(bookingDate2, madeForDay2, fromTime, paid, court, clubDBAccess.getMemberByCredentials(user, password));

                    //esta reservado por user mismo
                    if (member.getLogin().equals(user)) {
                        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
                        alerta.setTitle("Information");
                        alerta.setContentText("Ya habia reservado esta pista");
                        alerta.showAndWait();
                    } else {
                        //esta reservado por otros users
                        if (!booking.getMember().getLogin().equals("disponible")) {
                            Alert alerta = new Alert(Alert.AlertType.ERROR);
                            alerta.setTitle("Pista reservada");
                            alerta.setContentText("Por favor,selecciona otro");
                            alerta.showAndWait();

                        } else {
                            //esta disponible                    
                            reservas.add(bookingModificar);
                            if (clubDBAccess.hasCreditCard(user)) {
                                reservas.get(reservas.indexOf(bookingModificar)).setPaid(true);
                            }
                            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
                            alerta.setTitle("Enhorabuena");
                            alerta.setContentText("Se ha reservado la pista correctamente");
                            alerta.showAndWait();
                            tabla4.getSelectionModel().getSelectedItem().setMember(clubDBAccess.getMemberByCredentials(user, password));
                        }
                    }
                }
                clubDBAccess.saveDB();
                tabla4.refresh();
                break;
        }

    }

    @FXML
    private void cambiarFecha(ActionEvent event) {
        DatePicker datePicker = (DatePicker) event.getSource();
        String aux = datePicker.getId();
        ClubDBAccess clubDBAccess;
        clubDBAccess = ClubDBAccess.getSingletonClubDBAccess();
        System.out.println(aux);

        switch (aux) {
            case "dpBookingDay1":
               
                
                 ObservableList<Booking> reservas;
        reservas = FXCollections.observableList(clubDBAccess.getCourtBookings(clubDBAccess.getCourts().get(0).getName(), dpBookingDay1.getValue()));

                ObservableList<Booking> observableBookings;
                observableBookings = FXCollections.observableList(new ArrayList<>());

                LocalTime timeInicio = LocalTime.of(9, 0);

                for (int i = 0; i < clubDBAccess.getClubBookingSlots(); i++) {

                    if (!reservas.isEmpty()) {
                        Booking booking = reservas.get(0);
                        if (booking.getFromTime().equals(timeInicio.plusMinutes(i * 90))) {
                            observableBookings.add(booking);
                            reservas.remove(0);

                        } else {
                            observableBookings.add(new Booking(null, dpBookingDay1.getValue(), timeInicio.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(0), new Member("", "", "", "disponible", "", "", "", null)));
                        }
                    } else {
                        observableBookings.add(new Booking(null, dpBookingDay1.getValue(), timeInicio.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(0), new Member("", "", "", "disponible", "", "", "", null)));
                    }
                }

                tabla1.setItems(observableBookings);
                break;

            case "dpBookingDay2":
                ObservableList<Booking> reservas2;
                reservas2 = FXCollections.observableList(clubDBAccess.getCourtBookings(clubDBAccess.getCourts().get(1).getName(), dpBookingDay2.getValue()));

                ObservableList<Booking> observableBookings2;
                observableBookings2 = FXCollections.observableList(new ArrayList<>());

                LocalTime timeInicio2 = LocalTime.of(9, 0);

                for (int i = 0; i < clubDBAccess.getClubBookingSlots(); i++) {

                    if (!reservas2.isEmpty()) {
                        Booking booking = reservas2.get(0);
                        if (booking.getFromTime().equals(timeInicio2.plusMinutes(i * 90))) {
                            observableBookings2.add(booking);
                            reservas2.remove(0);

                        } else {
                            observableBookings2.add(new Booking(null, dpBookingDay2.getValue(), timeInicio2.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(1), new Member("", "", "", "disponible", "", "", "", null)));
                        }
                    } else {
                        observableBookings2.add(new Booking(null, dpBookingDay2.getValue(), timeInicio2.plusMinutes(i * 90), false,clubDBAccess.getCourts().get(1), new Member("", "", "", "disponible", "", "", "", null)));
                    }
                }

                tabla2.setItems(observableBookings2);
                break;

            case "dpBookingDay3":
                ObservableList<Booking> reservas3;
                reservas3 = FXCollections.observableList(clubDBAccess.getCourtBookings(clubDBAccess.getCourts().get(2).getName(), dpBookingDay3.getValue()));

                ObservableList<Booking> observableBookings3;
                observableBookings3 = FXCollections.observableList(new ArrayList<>());

                LocalTime timeInicio3 = LocalTime.of(9, 0);

                for (int i = 0; i < clubDBAccess.getClubBookingSlots(); i++) {

                    if (!reservas3.isEmpty()) {
                        Booking booking = reservas3.get(0);
                        if (booking.getFromTime().equals(timeInicio3.plusMinutes(i * 90))) {
                            observableBookings3.add(booking);
                            reservas3.remove(0);

                        } else {
                            observableBookings3.add(new Booking(null, dpBookingDay3.getValue(), timeInicio3.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(2), new Member("", "", "", "disponible", "", "", "", null)));
                        }
                    } else {
                        observableBookings3.add(new Booking(null, dpBookingDay3.getValue(), timeInicio3.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(2), new Member("", "", "", "disponible", "", "", "", null)));
                    }
                }

                tabla3.setItems(observableBookings3);
                break;

            case "dpBookingDay4":
                ObservableList<Booking> reservas4;
                reservas4 = FXCollections.observableList(clubDBAccess.getCourtBookings(clubDBAccess.getCourts().get(3).getName(), dpBookingDay4.getValue()));

                ObservableList<Booking> observableBookings4;
                observableBookings4 = FXCollections.observableList(new ArrayList<>());

                LocalTime timeInicio4 = LocalTime.of(9, 0);

                for (int i = 0; i < clubDBAccess.getClubBookingSlots(); i++) {

                    if (!reservas4.isEmpty()) {
                        Booking booking = reservas4.get(0);
                        if (booking.getFromTime().equals(timeInicio4.plusMinutes(i * 90))) {
                            observableBookings4.add(booking);
                            reservas4.remove(0);

                        } else {
                            observableBookings4.add(new Booking(null, dpBookingDay4.getValue(), timeInicio4.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(3), new Member("", "", "", "disponible", "", "", "", null)));
                        }
                    } else {
                        observableBookings4.add(new Booking(null, dpBookingDay4.getValue(), timeInicio4.plusMinutes(i * 90), false, clubDBAccess.getCourts().get(3), new Member("", "", "", "disponible", "", "", "", null)));
                    }
                }

                tabla4.setItems(observableBookings4);
                break;

        }

    }

    public static void getLogin(String login, String password) {
        FXMLreservarController.user = login;
        FXMLreservarController.password = password;
    }

}
