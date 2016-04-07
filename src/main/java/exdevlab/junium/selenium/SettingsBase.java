package exdevlab.junium.selenium;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SettingsBase {
    public String MainUrl;
    public String ScreenshotSystemPath;
    public String ScreenshotUrlPath;
    public String LogSystemPath;
    public String LogUrlPath;
    public int ScreenshotFolderCapacity;
    public int LogFolderCapacity;
    public String WebAppUrl;
    public String ChromeDriverPath;
    public String IEDriverPath;

    public SettingsBase() {
        MainUrl = "http://";
        ScreenshotSystemPath = "screenshots";
        ScreenshotUrlPath = "";
        LogSystemPath = "logs";
        LogUrlPath = "";
        ScreenshotFolderCapacity = 50;
        LogFolderCapacity = 50;
        WebAppUrl = "localhost";
        ChromeDriverPath = "drivers\\chromedriver.exe";
        IEDriverPath = "drivers\\IEDriverServer.exe";
    }
}
