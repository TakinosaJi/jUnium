package junium.selenium;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import junium.exceptions.PageObjectNotFoundException;
import junium.selenium.interfaces.IWebDriverAccessible;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectBase extends WebElementsContainer implements IWebDriverAccessible {
    public String Name;
    private String MarkerXPath;
    private WebElement Marker;

    public PageObjectBase(WebDriver driver, WebDriverWait wait, String name, String markerXPath) throws PageObjectNotFoundException {
        super(driver, wait);

        Name = name;
        MarkerXPath = markerXPath;

        exists();
    }

    public WebElement getMarker(){
        return Marker;
    }

    @Override
    public void exists() throws PageObjectNotFoundException {
        try {
            Marker = Wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MarkerXPath)));
        }
        catch (ElementNotFoundException e){
            throw new PageObjectNotFoundException(Name);
        }
    }
}