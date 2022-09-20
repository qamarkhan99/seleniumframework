package utilities;

import base.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class ScreenshotUtil extends TestBase {
    public static String screenShotName;

    public static void captureScreenshot() throws IOException {
        //type cast with driver reference
        File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        Date date = new Date();
        screenShotName =  date.toString().replace(":","_").replace(" ","_")+".jpg";
        FileUtils.copyFile(srcFile,new File("D:\\Java Master Course\\Selenium Frameworks\\target\\surefire-reports\\html\\"+screenShotName));
    }
}
