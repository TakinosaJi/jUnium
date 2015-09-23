package junium.selenium;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import junium.exceptions.PageObjectNotFoundException;
import junium.selenium.interfaces.IWebDriverAccessible;
import junium.selenium.annotations.LazyLoading;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectBase extends WebElementsContainer implements IWebDriverAccessible {
    public String Name;
    private String MarkerXPath;
    private WebElement Marker;
    private Boolean _lazyLoading;

    public PageObjectBase(WebDriver driver, WebDriverWait wait, String name, String markerXPath, Boolean lazyLoading) throws PageObjectNotFoundException {
        super(driver, wait);
        Init(name, markerXPath, lazyLoading);
    }

    public PageObjectBase(WebDriver driver, WebDriverWait wait, String name, String markerXPath) throws PageObjectNotFoundException {
        super(driver, wait);
        Init(name, markerXPath, this.getClass().isAnnotationPresent(LazyLoading.class));
    }

    private void Init(String name, String markerXPath, Boolean lazyLoading) throws PageObjectNotFoundException {
        Name = name;
        MarkerXPath = markerXPath;
        _lazyLoading = lazyLoading;
        if(!_lazyLoading) {
            getMarker();
        }
    }

    @Override
    public WebElement getMarker() throws PageObjectNotFoundException{
        try {
           return Marker = Wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(MarkerXPath)));
        }
        catch (ElementNotFoundException e){
            throw new PageObjectNotFoundException(Name);
        }
    }
}