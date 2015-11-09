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
    public ListView lista;
    public ImageView imagen;
    public Text texto;
    public ObservableList row = FXCollections.observableArrayList();
    public Button boton;

    public void initialize() {
        boton.setGraphic(new ImageView("refresh.png"));
    }

    public void actualiza(ActionEvent actionEvent) {
        row.add("Leo");
        lista.setItems(row);
        texto.setText(row.toString());
    }
}


