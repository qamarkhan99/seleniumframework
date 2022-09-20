package pages.locators;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OR_HomePage {

    @FindBy(how = How.CSS,using = ("li.mx-2:nth-child(1) > a:nth-child(1)"))
    public WebElement lnkBus;

}
