package junium.exceptions;


public class UnexpectedValueException extends Exception {
    public UnexpectedValueException(String pageObjectName, String stepName, String objectName, String expectedValue, String givenValue) {
        super("WebDriver: " + objectName + " contains '" + givenValue + "' instead of '" + expectedValue
                + "' in " + pageObjectName + ". Step: " + stepName);
    }
}
