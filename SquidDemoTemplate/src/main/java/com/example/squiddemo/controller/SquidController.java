/**
 * @author : 1972008 Adrian Octavius
 */

package com.example.squiddemo.controller;

import com.example.squiddemo.dao.HutangDaoImpl;
import com.example.squiddemo.dao.PlayerDaoImpl;
import com.example.squiddemo.entity.Hutang;
import com.example.squiddemo.entity.Player;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SquidController implements Initializable {
    @FXML
    private TextField txtPemberiUtang;
    @FXML
    private TextField txtJumlah;
    @FXML
    private ComboBox<Player> cmbPeserta;
    @FXML
    private TableView<Player> tablePemain;
    @FXML
    private TableColumn<Player, String> columnId;
    @FXML
    private TableColumn<Player, String> columnNama;
    @FXML
    private TableColumn<Player, String> columnUmur;
    @FXML
    private TableColumn<Player, String> columnKemampuan;
    @FXML
    private TableView<Hutang> tableHutang;
    @FXML
    private TableColumn<Hutang, String> columnIdPemain;
    @FXML
    private TableColumn<Hutang, String> columnHutang;
    @FXML
    private TableColumn<Hutang, String> columnSejumlah;


    private ObservableList<Hutang> hutangs;
    private ObservableList<Player> players;
    private HutangDaoImpl hutangDaoImpl;
    private PlayerDaoImpl playerDaoImpl;

    private StageModalController controller;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hutangDaoImpl= new HutangDaoImpl();
        playerDaoImpl = new PlayerDaoImpl();
        hutangs = FXCollections.observableArrayList();
        players = FXCollections.observableArrayList();

        try {
            players.addAll(playerDaoImpl.fetchAllList());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SquidController.class.getName()).log(Level.SEVERE, null, ex);

        }
        try {
            hutangs.addAll(hutangDaoImpl.fetchAllList());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(SquidController.class.getName()).log(Level.SEVERE, null, ex);

        }

        tablePemain.setItems(players);
        columnId.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getId())));
        columnNama.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNama()));
        columnUmur.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getUmur())));
        columnKemampuan.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getKeahlian()));

        cmbPeserta.setItems(players);
        tableHutang.setItems(hutangs);
        columnIdPemain.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getPlayer_id())));
        columnHutang.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPemberiUtang()));
        columnSejumlah.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getJumlah())));

    }

    public void editPemainPressed(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../ModalPage.fxml"));
        Parent root = loader.load();

    }

    public void addPemainPressed(ActionEvent actionEvent) {
    }

    public void deletePemainPressed(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Player selected;
        selected = tablePemain.getSelectionModel().getSelectedItem();
        int result = playerDaoImpl.deleteData(selected);
        ObservableList<Player> players = (ObservableList<Player>) playerDaoImpl.fetchAllList();
        tablePemain.setItems(players);
    }

    public void deleteHutangPressed(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        Hutang selected;
        selected = tableHutang.getSelectionModel().getSelectedItem();
        int result = hutangDaoImpl.deleteData(selected);
        ObservableList<Hutang> hutangs = (ObservableList<Hutang>) hutangDaoImpl.fetchAllList();
        tableHutang.setItems(hutangs);
    }

    public void tambahHutangPressed(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        HutangDaoImpl hutangDao =new HutangDaoImpl();
        try {
            Hutang hutang = new Hutang();
            hutang.setPemberiUtang(txtPemberiUtang.getText());
            hutang.setJumlah(Double.valueOf(txtJumlah.getText()));
            hutang.setPlayer_id(cmbPeserta.getValue());
            hutangDao.addData(hutang);
            hutangs.clear();
            hutangs.addAll(hutangDao.fetchAllList());
            txtPemberiUtang.setText("");
            txtJumlah.setText("");
        }catch (NumberFormatException e){
            e.getMessage();
        }

    }


}
