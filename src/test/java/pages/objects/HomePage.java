package pages.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import pages.locators.OR_HomePage;


public class HomePage {

    WebDriver driver;
    OR_HomePage or_homePage = new OR_HomePage();

    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,or_homePage);
    }

    public void click(WebElement webElement) {
        webElement.click();
    }

    public void clickBus(){
        click(or_homePage.lnkBus);
    }
}
