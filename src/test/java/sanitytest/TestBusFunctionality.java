package sanitytest;

import base.TestBase;
import org.testng.annotations.Test;
import pages.objects.HomePage;

public class TestBusFunctionality extends TestBase {
    @Test
    public void testBus() {
        HomePage homePage = new HomePage(driver);
        homePage.clickBus();
    }
}
