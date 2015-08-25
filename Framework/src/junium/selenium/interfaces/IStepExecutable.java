package junium.selenium.interfaces;

import junium.exceptions.PageObjectNotFoundException;
import junium.exceptions.UnexpectedValueException;

import java.io.FileNotFoundException;
import java.util.HashMap;


public interface IStepExecutable {
    public void Execute(HashMap<String, Object> returns) throws PageObjectNotFoundException, UnexpectedValueException, InterruptedException, FileNotFoundException;
}
