package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;


public class Controller {

    @FXML
    Button confirm = new Button("Confirm");
    @FXML
    TextField mac_address = new TextField();
    @FXML
    Label label = new Label();

    //Comments added for dramatic effect


    public void addMac(){
        System.out.println("Added MAC???");

        String mac = "";

        mac = mac_address.getText();

        System.out.println("The MAC Address entered was: " + mac);

    }
















}
