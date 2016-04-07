package exdevlab.junium.selenium;

import exdevlab.junium.exceptions.PageObjectNotFoundException;
import exdevlab.junium.selenium.annotations.LazyLoading;
import exdevlab.junium.selenium.interfaces.IWebDriverAccessible;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
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
        catch (Exception e){
            throw new PageObjectNotFoundException(Name);
        }
    }
}