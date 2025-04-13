package com.example.demo;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import samochód.*;
import java.io.IOException;



public class HelloController implements Samochód.Listener {


    @Override
    public void update() {
        Platform.runLater(() -> {
            refresh();
        });
    }


    Samochód samochod;
    @FXML
    private TextField masasilnikaTextField;
    @FXML
    private TextField aktualnyBiegTextField;
    @FXML
    private TextField aktualnapozycjayTextField;
    @FXML
    private TextField jedzdoxTextField;
    @FXML
    private TextField AktualneObrotyTextField;
    @FXML
    private TextField jedzdoyTextField;
    @FXML
    private TextField nazwaskrzyniTextField;
    @FXML
    private TextField cenaskrzyniTextField;
    @FXML
    private TextField nazwasamochoduTextField;
    @FXML
    private TextField nrrejestracyjnyTextField;
    @FXML
    private TextField predkoscTextField;
    @FXML
    private TextField aktualnapozycjaxTextField;
    @FXML
    private TextField nazwasilnikaTextField;
    @FXML
    private TextField maxobTextField;
    @FXML
    private TextField minobTextField;
    @FXML
    private TextField wagaskrzyniTextField;
    @FXML
    private TextField masasprzeglaTextField;
    @FXML
    private ImageView CarImageView;
    @FXML
    private Pane mapa;


    @FXML
    private ComboBox<Samochód> samochodComboBox;
    private ObservableList<Samochód> samochody = FXCollections.observableArrayList();


    public void initialize() {
        String path = getClass().getResource("/images/car.png").toExternalForm();
        Image CarImage = new Image(path);

        CarImageView.setImage(CarImage);
        CarImageView.setFitWidth(60);
        CarImageView.setFitHeight(60);
        CarImageView.setTranslateX(0);
        CarImageView.setTranslateY(0);
        samochodComboBox.setItems(samochody);

        samochodComboBox.setOnAction(event -> {
            samochod = samochodComboBox.getSelectionModel().getSelectedItem();
            refresh();
        });

        mapa.setOnMouseClicked(event -> {
            double a = event.getX() - 70;
            double b = event.getY() - 70;
            jedzdoxTextField.setText(String.valueOf(a));
            jedzdoyTextField.setText(String.valueOf(b));
            if (samochod != null) {
                samochod.jedzDo(a, b);
            }
            refresh();

        });
    }


    public void dodajSamochodDoListy(String model, String nrRejestracyjny, Silnik silnik, SkrzyniaBiegow skrzyniaBiegow) {
        Samochód nowy = new Samochód(model, nrRejestracyjny, silnik, skrzyniaBiegow);
        nowy.setPozycja(new Pozycja(CarImageView.getX(), CarImageView.getY()));
        nowy.addListener(this);
        samochody.add(nowy);
        samochod = nowy;
        samochodComboBox.setItems(samochody);
        samochodComboBox.getSelectionModel().select(nowy);
        refresh();
    }

    @FXML
    public void onDodajSamochód(ActionEvent actionEvent) throws IOException {
        FXMLLoader DodajSamochodd = new FXMLLoader(HelloApplication.class.getResource("DodajSamochod.fxml"));
        Parent oknoDodajSamochod = DodajSamochodd.load();
        DodajSamochodController dodajSamochodController = DodajSamochodd.getController();
        dodajSamochodController.setparent(this);
        Stage window = new Stage();
        Scene newscene = new Scene(oknoDodajSamochod, 400, 350);
        window.setTitle("Dodaj Samochód");
        window.setScene(newscene);
        window.show();
    }

    public void onUsun(ActionEvent actionEvent) {
        if (samochody.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Dodaj samochód");
            alert.showAndWait();
        }

        if (samochody.size() != 0 && samochod != null) {
            samochod.removeListener(this);
            samochody.remove(samochod);

            if (!samochody.isEmpty()) {
                samochodComboBox.setItems(samochody);
                samochod = samochody.get(0);
                samochodComboBox.setValue(samochod);
            } else {
                samochod = null;
                samochodComboBox.setItems(null);
            }


            refresh();
        }
    }

    public void onWlacz(ActionEvent actionEvent) {
        if (samochody.size() != 0) {
            samochod.wlacz();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Dodaj samochód");
            alert.showAndWait();
        }
    }


    public void onWylacz(ActionEvent actionEvent) {
        if (samochody.size() != 0) {
            samochod.wylacz();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Dodaj samochód");
            alert.showAndWait();
        }
    }

    public void onZmniejszBieg(ActionEvent actionEvent) {
        if (samochody.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Dodaj samochód");
            alert.showAndWait();
        } else {
            try {
                samochod.ZmniejszBieg();
            } catch (SkrzyniaBiegowException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText("Osiągnięto minimalny bieg");
                alert.showAndWait();
            }
        }
        if ((samochody.size() == 0)) {
        } else {
            if (samochod.sprzeglo.getStan() == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText("Nie można zmienić biegu przy nie wciśniętym sprzęgle");
                alert.showAndWait();
            }
        }
    }

    public void onZwiększBieg(ActionEvent actionEvent) {
        if (samochody.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Dodaj samochód");
            alert.showAndWait();
        } else {
            try {
                samochod.ZwiekszBieg();
            } catch (SkrzyniaBiegowException e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText("Osiągnięto maksymalny bieg");
                alert.showAndWait();
            }
        }
        if ((samochody.size() == 0)) {
        } else {
            if (samochod.sprzeglo.getStan() == false) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText(null);
                alert.setContentText("Nie można zmienić biegu przy nie wciśniętym sprzęgle");
                alert.showAndWait();
            }
        }
    }

    public void onNacisnij(ActionEvent actionEvent) {
        if (samochody.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Dodaj samochód");
            alert.showAndWait();
        } else {
            samochod.sprzeglo.wcisnij();
            refresh();
        }
    }

    public void onZwolnij(ActionEvent actionEvent) {
        if (samochody.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Dodaj samochód");
            alert.showAndWait();
        } else {
            samochod.sprzeglo.zwolnij();
            refresh();
        }
    }

    public void onZwiekszObroty(ActionEvent actionEvent) {
        if (samochody.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Dodaj samochód");
            alert.showAndWait();
        } else {
            samochod.silnik.zwiekszObroty();
            refresh();
        }
    }

    public void onZmniejszObroty(ActionEvent actionEvent) {
        if (samochody.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Błąd");
            alert.setHeaderText(null);
            alert.setContentText("Dodaj samochód");
            alert.showAndWait();
        } else {
            samochod.silnik.zmiejszObroty();
            refresh();
        }
    }

    @FXML
    public void refresh() {
        if (samochod != null) {
            nazwasamochoduTextField.setText(String.valueOf(samochod.getModel()));
            nrrejestracyjnyTextField.setText(String.valueOf(samochod.nrRejestracyjny));
            predkoscTextField.setText(String.valueOf(samochod.aktualna_predkosc()));
            aktualnapozycjaxTextField.setText(String.valueOf(samochod.pozycjax()));
            aktualnapozycjayTextField.setText(String.valueOf(samochod.pozycjay()));
            nazwaskrzyniTextField.setText(String.valueOf(samochod.skrzyniaBiegow.getNazwa()));
            cenaskrzyniTextField.setText(String.valueOf(samochod.skrzyniaBiegow.getCena()));
            wagaskrzyniTextField.setText(String.valueOf(samochod.skrzyniaBiegow.getWaga()));
            masasprzeglaTextField.setText(String.valueOf(samochod.sprzeglo.getWaga()));
            nazwasilnikaTextField.setText(String.valueOf(samochod.silnik.getNazwa()));
            maxobTextField.setText(String.valueOf(samochod.silnik.getMaksymalneObroty()));
            minobTextField.setText(String.valueOf(samochod.silnik.getMinObroty()));
            masasilnikaTextField.setText(String.valueOf(samochod.silnik.getWaga()));
            AktualneObrotyTextField.setText(String.valueOf(samochod.silnik.GetObroty()));
            aktualnyBiegTextField.setText(String.valueOf(samochod.skrzyniaBiegow.GetAktualnyBieg()));
            samochodComboBox.setItems(samochody);
            CarImageView.setTranslateX(samochod.pozycjax());
            CarImageView.setTranslateY(samochod.pozycjay());
        }
    }

}
