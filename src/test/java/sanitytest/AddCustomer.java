package sanitytest;

import base.TestBase;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.DataProviderUtil;

import java.util.Hashtable;



public class AddCustomer extends TestBase {

    @Test(dataProviderClass = DataProviderUtil.class,dataProvider = "dp",enabled = true)
    public void addCustomer(Hashtable<String,String> data)  {
        log.debug("Enter AddCustomer Test");

          //  click("bmlBtn_CSS");
            click("btnAddCustomer_CSS");
            type("firstName_CSS",(data.get("lastName")));
            type("lastName_CSS", (data.get("firstName")));
            type("postCode_CSS", (data.get("postCode")));
            click("btnAddCustomer1_CSS");

            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            Assert.assertTrue(alert.getText().contains(data.get("alertText")));
            alert.accept();
        }

    }


