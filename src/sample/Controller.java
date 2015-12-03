package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;
import jdk.internal.org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {
    //Variables
    public ListView lista;
    public ImageView imagen;
    public Text texto;
    public ObservableList row = FXCollections.observableArrayList();
    public Text tempMaxRes;
    public Text tempMinRes;


    //Variables de les imatges
    public Image clear = new Image ("http://openweathermap.org/img/w/01d.png");
    public Image few = new Image ("http://openweathermap.org/img/w/02d.png");
    public Image scattered = new Image ("http://openweathermap.org/img/w/03d.png");

    //setGraphic per al bot� d'actualitza
    public void initialize() throws ParserConfigurationException, IOException, org.xml.sax.SAXException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("El Temps - Leonardo Martinez");
        alert.setHeaderText(null);
        alert.setContentText("Benvingut a l'aplicacio El Temps\nCopyright 2015 - Leonardo Martinez");
        alert.showAndWait();

        dom();
    }

    /* m�tode per actualitzar el listview amb les dades de l'xml. configurat per actualitzar-se amb json o per a que canviin els
       valor en el moment d'utilitzar dades que canvi�n constantment
    */
    public void actualiza(ActionEvent actionEvent) throws ParserConfigurationException, IOException, org.xml.sax.SAXException {
        row.remove(0,16); //numero de filas
        dom();
    }

    public void dom() throws ParserConfigurationException, IOException, org.xml.sax.SAXException {
        //DOM per a afegir les dades al llen�ar el programa
        File xmlFile = new File("src/sample/forecast/forecast.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlFile);

        NodeList nList = doc.getElementsByTagName("time");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                row.add(eElement.getAttribute("day")+" || "+
                        eElement.getElementsByTagName("symbol").item(0).getAttributes().item(0).getTextContent());
                lista.setItems(row);
            }
        }
        //canvia el camp 'texto' segons la filera que seleccionem
        lista.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener() {
                    @Override
                    public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                        Node nNode = nList.item(lista.getSelectionModel().getSelectedIndex());
                        Element eElement = (Element) nNode;
                        texto.setText(eElement.getAttribute("day"));
                        tempMaxRes.setText(eElement.getElementsByTagName("temperature").item(0).getAttributes().item(2).getTextContent());
                        tempMinRes.setText(eElement.getElementsByTagName("temperature").item(0).getAttributes().item(3).getTextContent());


                        //aprofitem i canviem tamb� la imatge.
                        if(eElement.getElementsByTagName("symbol").item(0).getAttributes().item(1).getTextContent().equals("800")){
                            imagen.setImage(clear);
                        }else if(eElement.getElementsByTagName("symbol").item(0).getAttributes().item(1).getTextContent().equals("802")){
                            imagen.setImage(scattered);
                        }else if(eElement.getElementsByTagName("symbol").item(0).getAttributes().item(1).getTextContent().equals("801")){
                            imagen.setImage(few);
                        }

                    }
                }
        );
    }
}
