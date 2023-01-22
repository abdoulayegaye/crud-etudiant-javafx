package sn.toto.crudetudiantjavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sn.toto.crudetudiantjavafx.tools.Notification;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EtudiantController implements Initializable {
    private DBConnexion db = new DBConnexion();
    private ResultSet rs;
    @FXML
    private Button effacerBtn;

    @FXML
    private Button enregistrerBtn;

    @FXML
    private TextField matiereTxt;

    @FXML
    private Button modiferBtn;

    @FXML
    private TextField nomTxt;

    @FXML
    private TextField prenomTxt;

    @FXML
    private Button supprimerBtn;

    @FXML
    private TableView<Etudiant> table;

    @FXML
    private TableColumn<Etudiant, Integer> idCol;

    @FXML
    private TableColumn<Etudiant, String> matiereCol;

    @FXML
    private TableColumn<Etudiant, String> nomCol;

    @FXML
    private TableColumn<Etudiant, String> prenomCol;

    private int id;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEtudiants();
    }

    public ObservableList<Etudiant> getEtudiants(){
        ObservableList<Etudiant> etudiants = FXCollections.observableArrayList();
        String sql = "SELECT * FROM etudiant ORDER BY nom ASC";
        try{
            db.initPrepar(sql);
            rs = db.executeSelect();
            while(rs.next()){
                Etudiant et = new Etudiant();
                et.setId(rs.getInt(1));
                et.setPrenom(rs.getString(2));
                et.setNom(rs.getString(3));
                et.setMatiere(rs.getString(4));
                etudiants.add(et);
            }
            db.closeConnection();
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return etudiants;
    }

    public void showEtudiants(){
        ObservableList<Etudiant> list = getEtudiants();
        table.setItems(list);
        idCol.setCellValueFactory(new PropertyValueFactory<Etudiant, Integer>("id"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("prenom"));
        nomCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("nom"));
        matiereCol.setCellValueFactory(new PropertyValueFactory<Etudiant, String>("matiere"));
    }

    @FXML
    void creerEtudiant(ActionEvent event) {
        String sql = "INSERT INTO etudiant VALUES(NULL, ?, ?, ?)";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, prenomTxt.getText());
            db.getPstm().setString(2, nomTxt.getText());
            db.getPstm().setString(3, matiereTxt.getText());
            db.executeMaj();
            db.closeConnection();
            showEtudiants();
            viderChamps();
            Notification.NotifSuccess("Success", "Etudiant enregistré avec succés !");
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @FXML
    void getData(MouseEvent event) {
        Etudiant etudiant = table.getSelectionModel().getSelectedItem();
        id = etudiant.getId();
        prenomTxt.setText(etudiant.getPrenom());
        nomTxt.setText(etudiant.getNom());
        matiereTxt.setText(etudiant.getMatiere());
        enregistrerBtn.setDisable(true);
    }

    public void viderChamps(){
        prenomTxt.setText("");
        nomTxt.setText("");
        matiereTxt.setText("");
    }

    @FXML
    void effacerChamps(ActionEvent event) {
        viderChamps();
    }

    @FXML
    void modifierEtudiant(ActionEvent event) {
        String sql = "UPDATE etudiant SET prenom = ?, nom = ?, matiere = ? WHERE id = ?";
        try {
            db.initPrepar(sql);
            db.getPstm().setString(1, prenomTxt.getText());
            db.getPstm().setString(2, nomTxt.getText());
            db.getPstm().setString(3, matiereTxt.getText());
            db.getPstm().setInt(4, id);
            db.executeMaj();
            db.closeConnection();
            showEtudiants();
            viderChamps();
            enregistrerBtn.setDisable(false);
            Notification.NotifSuccess("Success", "Etudiant modifié avec succés !");
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    @FXML
    void supprimerEtudiant(ActionEvent event) {
        String sql = "DELETE FROM etudiant WHERE id = ?";
        try {
            db.initPrepar(sql);
            db.getPstm().setInt(1, id);
            db.executeMaj();
            db.closeConnection();
            showEtudiants();
            viderChamps();
            enregistrerBtn.setDisable(false);
            Notification.NotifSuccess("Success", "Etudiant supprimé avec succés !");
        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

}
