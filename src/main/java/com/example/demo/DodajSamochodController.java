package com.example.demo;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import samochód.*;


public class DodajSamochodController {
    private HelloController parent;
    @FXML
    private TextField nazwaTextField;
    @FXML
    private TextField NrRejestracyjnyTextField;
    @FXML
    private Button anulujButton;
    @FXML
    private Button dodajButton;
    @FXML
    private ComboBox<Silnik> silnikiComboBox;
    @FXML
    private ComboBox<SkrzyniaBiegow> skrzyniaComboBox;
    private ObservableList<Silnik> silniki = FXCollections.observableArrayList(); //tworzenie list obserwowalnych
    private ObservableList<SkrzyniaBiegow> skrzynie = FXCollections.observableArrayList();

    public void setparent(HelloController parent) {this.parent = parent;}

    @FXML
    public void initialize() {
        silniki.addAll(new Silnik(4500, 500, "1.4 TSI", 3000, 450), new Silnik(5000, 450, "1.5 TSI", 3500, 400), new Silnik(6600, 400, "1.8 TSI", 6800, 350), new Silnik(9000, 300, "2.0 TSI", 18000, 350));
        skrzynie.addAll(new SkrzyniaBiegow(4, "4-biegowa", 1000, 70), new SkrzyniaBiegow(5, "5-biegowa", 1500, 80), new SkrzyniaBiegow(6, "6-biegowa", 2500, 90), new SkrzyniaBiegow(7, "7-biegowa", 5000, 120));
        silnikiComboBox.setItems(silniki); //dodawanie list obserwowalnych do Comboboxa, przez co combo box obserwuje zmiane listy i ją aktualizuje
        skrzyniaComboBox.setItems(skrzynie);
    }

    @FXML
    private void DodajSamochod(ActionEvent actionEvent) {
        String nazwaSamochodu = nazwaTextField.getText();
        String rejestracjaSamochodu = NrRejestracyjnyTextField.getText();
        Silnik Silnik = silnikiComboBox.getValue();
        SkrzyniaBiegow Skrzynia = skrzyniaComboBox.getValue();

        if (Silnik != null && Skrzynia != null && nazwaSamochodu.isEmpty() ==false && rejestracjaSamochodu.isEmpty() == false) {
            parent.dodajSamochodDoListy(nazwaSamochodu, rejestracjaSamochodu, Silnik, Skrzynia);
            Stage stage = (Stage) dodajButton.getScene().getWindow();
            stage.close();
        }else {Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Wprowadź odpowiednie parametry!");
            alert.showAndWait();}
    }

    @FXML
    private void onAnuluj() {
        Stage stage = (Stage) anulujButton.getScene().getWindow();
        stage.close();
    }
}