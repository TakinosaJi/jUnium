package junium.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebElementsContainer {
    protected WebDriver Driver;
    protected WebDriverWait Wait;

    public WebElementsContainer(WebDriver driver,WebDriverWait wait){
        Driver = driver;
        Wait = wait;
    }
}
