package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

public class Controller {
    //Variables
    public ListView lista;
    public ImageView imagen;
    public Text texto;
    public ObservableList row = FXCollections.observableArrayList();
    public Button boton;

    //setGraphic per al bot� d'actualitza
    public void initialize() {
        boton.setGraphic(new ImageView("refresh.png"));
    }

    //m�tode per actualitzar el listview amb les dades
    public void actualiza(ActionEvent actionEvent) {
        row.add("Leo");
        lista.setItems(row);
        texto.setText(row.toString());
    }

    //treball amb el forecast. hem triat JAXB per a l'extracci� de dades

}


