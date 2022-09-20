package sanitytest;

import base.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class BankManagerLogin extends TestBase {

    @Test
    public void loginTest (){
        log.debug("Inside login Test");
        //driver.findElement(By.cssSelector(or.getProperty("bmlBtn"))).click();
        click("bmlBtn_CSS");
        Assert.assertTrue(isElementPresent(By.cssSelector(or.getProperty("btnAddCustomer_CSS"))),"Add Customer Button not found");
        log.debug("Login test successfully executed");
        Reporter.log("Login Test successfully executed");
       // Assert.fail("Customer not added successfully");
    }
}
