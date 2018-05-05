package sample;

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

    @FXML
    Button confirm = new Button("Confirm");
    @FXML
    TextField mac_address = new TextField();
    @FXML
    Label label = new Label();
    @FXML
    Button ok = new Button();

    //Comments added for dramatic effect


    public void addMac(){
        System.out.println("Added MAC???");
        String mac = "";
        mac = mac_address.getText();

        if(macValidate(mac)){

            //if you didn't update the Path system variable to add the full directory path to the executable as above mentioned then doing this directly through code
            System.setProperty("webdriver.gecko.driver", "geckodriver");
            WebDriver driver = new FirefoxDriver();
            driver.get("https://www.google.com");
            System.out.println("Went to Google.com");
            driver.manage().window().maximize();
            driver.findElement(By.id("lst-ib")).sendKeys("Facebook");
            driver.findElement(By.name("btnK")).click();

            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            driver.quit();

        }else{
            System.out.println("Incorrect MAC");

            display();
        }





    }

    public boolean validate(String mac) {
        System.out.println("The MAC Address entered was: " + mac);
        Pattern p = Pattern.compile("^([a-fA-F0-9][:-]){5}[a-fA-F0-9][:-]$");
        Matcher m = p.matcher(mac);
        return m.find();
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










}
