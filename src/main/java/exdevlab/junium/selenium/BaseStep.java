package exdevlab.junium.selenium;

import exdevlab.junium.selenium.interfaces.IStepExecutable;
import exdevlab.junium.utils.UrlHelper;
import exdevlab.junium.selenium.enums.Browser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseStep implements IStepExecutable {
    public IBaseStepDto Dto;
    public WebDriver Driver;
    public WebDriverWait SmallWait;
    public WebDriverWait Wait;
    public WebDriverWait LongWait;
    public String Name;
    public UrlHelper SiteUrls;
    public Browser Browser;

    public BaseStep(IBaseStepDto dto) {
        this(dto, "");
    }

    public BaseStep(IBaseStepDto dto, String name) {
        Dto = dto;
        Name = name;
        SiteUrls = new UrlHelper("");
    }
}
