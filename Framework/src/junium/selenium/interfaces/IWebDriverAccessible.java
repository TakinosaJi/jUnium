package junium.selenium.interfaces;


import junium.exceptions.PageObjectNotFoundException;
import org.openqa.selenium.WebElement;

public interface IWebDriverAccessible {
    public WebElement getMarker() throws PageObjectNotFoundException;
}
