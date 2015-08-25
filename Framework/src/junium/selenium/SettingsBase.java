package junium.selenium;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SettingsBase {
    public String MainUrl;
    public String ScreenshotFolderPath;
    public String LogFolderPath;
    public int ScreenshotFolderCapacity;
    public int LogFolderCapacity;
    public String WebAppUrl;
    public String ChromeDriverPath;
    public String IEDriverPath;

    public SettingsBase() {
        MainUrl = "http://";
        ScreenshotFolderPath = "screenshots";
        LogFolderPath = "logs";
        ScreenshotFolderCapacity = 50;
        LogFolderCapacity = 50;
        WebAppUrl = "localhost";
        ChromeDriverPath = "drivers\\chromedriver.exe";
        IEDriverPath = "drivers\\IEDriverServer.exe";
    }
}
