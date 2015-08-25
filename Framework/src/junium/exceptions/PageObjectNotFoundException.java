package junium.exceptions;


public class PageObjectNotFoundException extends Exception {
    public PageObjectNotFoundException(String pageObjectName) {
        super(pageObjectName + " not found.");
    }
}
