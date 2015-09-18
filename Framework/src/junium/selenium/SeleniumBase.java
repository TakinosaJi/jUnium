package junium.selenium;

import junium.exceptions.PageObjectNotFoundException;
import junium.exceptions.UnexpectedValueException;
import junium.selenium.enums.Browser;
import junium.utils.FileByCreationTimeComparator;
import junium.utils.PathsHelper;
import junium.utils.SystemHelper;
import junium.utils.UrlHelper;

import org.apache.commons.io.FileUtils;

import org.junit.After;
import org.junit.Before;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

public class SeleniumBase {
    protected WebDriver Driver;
    protected WebDriverWait SmallWait;
    protected WebDriverWait Wait;
    protected WebDriverWait LongWait;

    protected SettingsBase Settings;
    protected UrlHelper SiteUrl;
    protected Boolean IsFailed;
    protected Browser Browser;

    protected DesiredCapabilities FirefoxCapabilities;
    protected FirefoxProfile FirefoxDriverProfile;
    protected FirefoxBinary FirefoxDriverBinary;

    protected DesiredCapabilities ChromeCapabilities;
    protected ChromeDriverService ChromeService;

    protected DesiredCapabilities IECapabilities;
    protected InternetExplorerDriverService IEService;
    public SeleniumBase() {
        Settings = new SettingsBase();
        SiteUrl = new UrlHelper(Settings.MainUrl);
        IsFailed = true;

        try {
            Browser = Browser.valueOf(System.getProperty("browser"));
        } catch(NullPointerException excp) {
            Browser = Browser.Firefox;
        } catch (IllegalArgumentException excp){
            Browser = Browser.Firefox;
        }
    }

//    @BeforeClass
//    public static void TestFixtureSetUp(){
//        Class<? extends SeleniumBase> thisClass = this.getClass();
//        System.out.print(thisClass.getAnnotation(TestCaseInfo.class).Description());
//    }

    @Before
    public void SetUp() {
        System.out.print("Using " + Browser + SystemHelper.LineSeparator);

        switch(Browser) {
            case Chrome: {
                System.setProperty("webdriver.chrome.driver", Settings.ChromeDriverPath);
                ChromeCapabilities = ChromeCapabilities == null ? DesiredCapabilities.chrome() : ChromeCapabilities;
                ChromeService = ChromeService == null ? ChromeDriverService.createDefaultService() : ChromeService;
                Driver = new ChromeDriver(ChromeService, ChromeCapabilities);
            }
            break;
            case Firefox: {
                InitializeFirefoxDriver();
            }
            break;
            case IE: {
                System.setProperty("webdriver.ie.driver", Settings.IEDriverPath);
                IECapabilities = IECapabilities == null ? DesiredCapabilities.internetExplorer() : IECapabilities;
                IEService = IEService == null ? InternetExplorerDriverService.createDefaultService() : IEService;
                Driver = new InternetExplorerDriver(IEService, IECapabilities);
            }
            break;
            default: {
                InitializeFirefoxDriver();
            }
            break;
        }

        Driver.manage().window().maximize();
        SmallWait = new WebDriverWait(Driver, 3);
        Wait = new WebDriverWait(Driver, 10);
        LongWait = new WebDriverWait(Driver, 30);
    }

    private void InitializeFirefoxDriver(){
        FirefoxCapabilities = FirefoxCapabilities == null ? DesiredCapabilities.firefox() : FirefoxCapabilities;
        FirefoxDriverProfile = FirefoxDriverProfile == null ? new FirefoxProfile() : FirefoxDriverProfile;
        FirefoxDriverBinary = FirefoxDriverBinary == null ? new FirefoxBinary() : FirefoxDriverBinary;
        Driver = new FirefoxDriver(FirefoxDriverBinary, FirefoxDriverProfile, FirefoxCapabilities);
    }

    @After
    public void BaseTearDown() throws IOException {
        if (IsFailed == true){
            File scrFile = ((TakesScreenshot)Driver).getScreenshotAs(OutputType.FILE);
            Date now = new Date();
            String screenshotName = "Screenshot_" + now.toString().replace(' ', '_').replace(':', '_') + ".png";
            String className = this.getClass().getName();
            String folderPath = Paths.get(Settings.ScreenshotFolderPath).isAbsolute() ? Settings.ScreenshotFolderPath :
                    PathsHelper.GetAbsoluteWorkingDirPath() + File.separator + Settings.ScreenshotFolderPath;
            Path completePath = Paths.get(folderPath, className);
            File screenshotsDir = new File(completePath.toString());
            if(!screenshotsDir.exists() || !screenshotsDir.isDirectory()){
                screenshotsDir.mkdirs();
            }
            String filePath = completePath + File.separator + screenshotName;
            FileUtils.copyFile(scrFile, new File(filePath));
            File[] files = screenshotsDir.listFiles(pathname -> {
                return pathname.getName().endsWith(".png");
            });
            int maxFilesCount = Settings.ScreenshotFolderCapacity;
            if(files.length > maxFilesCount) {
                Arrays.sort(files, new FileByCreationTimeComparator());
                int extraCount = files.length - maxFilesCount;
                for (int i = 0; i < extraCount; i++) {
                    File file = files[i];
                    file.delete();
                }
            }
            System.out.print("View screenshot: " + Settings.WebAppUrl + "/" +
                    new File(PathsHelper.GetAbsoluteWorkingDirPath() + '/' + Settings.ScreenshotFolderPath).getName() + "/" + className + "/" + screenshotName);
        }
        Driver.quit();
    }

    public HashMap<String, Object> Do(BaseStep step) throws PageObjectNotFoundException, UnexpectedValueException, InterruptedException, FileNotFoundException {
        step.Driver = Driver;
        step.SmallWait = new WebDriverWait(Driver, 3);
        step.Wait = new WebDriverWait(Driver, 10);
        step.LongWait = new WebDriverWait(Driver, 30);
        step.SiteUrls = SiteUrl;
        step.Browser = Browser;

        IsFailed = true;

        HashMap<String, Object> stepReturns = new HashMap<String, Object>();
        step.Execute(stepReturns);

        IsFailed = false;

        return stepReturns;
    }
/*    public <TStep extends BaseStep, TDto extends IBaseStepDto> HashMap<String, Object> Do(TDto dto) {

    }*/
}


