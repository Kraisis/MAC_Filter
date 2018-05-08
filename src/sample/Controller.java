package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Controller {

    String username = "admin";
    String password = "TSAATC2018";

    ObservableList<String> deviceList = FXCollections.observableArrayList("Phone", "Computer", "Tablet");



    @FXML
    Button confirm = new Button();
    @FXML
    Button nothing = new Button();
    @FXML
    TextField mac_address = new TextField();
    @FXML
    TextField name_tf = new TextField();
    @FXML
    Label label = new Label();
    @FXML
    Button ok = new Button();
    @FXML
    ChoiceBox device;

    //Comments added for dramatic effect
    public void addMac() throws InterruptedException {

        String mac = mac_address.getText();
        String name = name_tf.getText();
        String dev = (String)device.getValue();



        if(mac.equalsIgnoreCase("") && name.equalsIgnoreCase("")){
            //Alert Box when nothing is entered
            nothingEntered();
            System.out.println("Nothing entered");

        }else{
            if(macValidate(mac)){
                //if you didn't update the Path system variable to add the full directory path to the executable as above mentioned then doing this directly through code
                System.setProperty("webdriver.gecko.driver", "geckodriver");
                WebDriver driver = new FirefoxDriver();

                if(login(driver)){
                    System.out.println("Login Went Well");

                }else{
                    System.out.println("Error, could not login");
                }

                Thread.sleep(5000);
                System.out.println("Successfully Terminated");
                driver.quit();
            }else{
                System.out.println("Incorrect MAC");
                display();
            }
        }


    }

    public boolean macValidate(String mac) {
        Pattern p = Pattern.compile("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");
        Matcher m = p.matcher(mac);
        return m.find();
    }

    public void display(){
        Stage stage = new Stage();

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("alert.fxml"));
        } catch (IOException e) {
            System.out.println("Couldn't Load FXML Document");
        }
        stage.setTitle("Incorrect MAC Address");
        stage.setScene(new Scene(root, 400, 200));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(confirm.getScene().getWindow());
        stage.show();

    }

    @FXML
    private void closeButtonAction(){
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void closeButton(){
        Stage stage = (Stage) nothing.getScene().getWindow();
        stage.close();
    }

    public boolean login(WebDriver driver) throws InterruptedException {

        try{
//            driver.get("192.168.0.1/?wifi_mac");
//            driver.findElement(By.id("UserName")).sendKeys(username);
//            driver.findElement(By.id("Password")).sendKeys(password);
//            driver.findElement(By.className("submitBtn")).click();
            System.out.println("Login Successful");
            Thread.sleep(1000);
        }catch(Exception e){
            return false;
        }
        return true;

    }

    public void nothingEntered(){
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("nothing.fxml"));
        } catch (IOException e) {
            System.out.println("Couldn't Load FXML Document");
        }
        stage.setTitle("Required Fields");
        stage.setScene(new Scene(root, 270, 85));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(confirm.getScene().getWindow());
        stage.showAndWait();
    }



    //This function crashes almost everything
//    @FXML
//    private void initialize(){
//        device.setValue("Phone");
//        device.setItems(deviceList);
//    }



}
