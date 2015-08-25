package junium.selenium.interfaces;


import junium.exceptions.PageObjectNotFoundException;

public interface IWebDriverAccessible {
    public void exists() throws PageObjectNotFoundException;
}
