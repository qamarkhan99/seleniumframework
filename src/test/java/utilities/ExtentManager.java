package utilities;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;

import java.io.File;

public class ExtentManager {

    private static ExtentReports extentReports;

    public static ExtentReports getInstance(){
        if(extentReports ==null){
            extentReports = new ExtentReports("D:\\Java Master Course\\Selenium Frameworks\\target\\surefire-reports\\html\\extent.html",true, DisplayOrder.OLDEST_FIRST);
            extentReports.loadConfig(new File("D:\\Java Master Course\\Selenium Frameworks\\src\\test\\resources\\extentconfig\\reportconfig.xml"));
        }
        return extentReports;
    }
}
