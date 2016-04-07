package exdevlab.junium.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.Set;

public class LastWindowCondition implements ExpectedCondition<String> {
    private Set<String> _oldWindowSet;

    public LastWindowCondition(Set<String> oldWindowSet){
        _oldWindowSet = oldWindowSet;
    }
    public String apply(WebDriver Driver) {
        Set<String> newWindowsSet = Driver.getWindowHandles();
        newWindowsSet.removeAll(_oldWindowSet);
        return newWindowsSet.size() > 0 ?
        newWindowsSet.iterator().next() : null;
    }
}