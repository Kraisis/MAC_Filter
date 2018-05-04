package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


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


    }
















}
