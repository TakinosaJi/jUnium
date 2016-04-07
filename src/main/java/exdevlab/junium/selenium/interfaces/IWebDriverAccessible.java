package exdevlab.junium.selenium.interfaces;


import exdevlab.junium.exceptions.PageObjectNotFoundException;
import org.openqa.selenium.WebElement;

public interface IWebDriverAccessible {
    public WebElement getMarker() throws PageObjectNotFoundException;
}
