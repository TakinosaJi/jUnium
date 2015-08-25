package junium.selenium;

import junium.selenium.enums.Browser;
import junium.selenium.interfaces.IStepExecutable;
import junium.utils.UrlHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseStep implements IStepExecutable {
    public BaseStepDto Dto;
    public WebDriver Driver;
    public WebDriverWait SmallWait;
    public WebDriverWait Wait;
    public WebDriverWait LongWait;
    public String Name;
    public UrlHelper SiteUrl;
    public Browser Browser;

    public BaseStep(BaseStepDto dto) {
        this(dto, "");
    }

    public BaseStep(BaseStepDto dto, String name) {
        Dto = dto;
        Name = name;
        SiteUrl = new UrlHelper("");
    }
}
