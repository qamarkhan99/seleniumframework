package base;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.Excel;
import utilities.Excel_Updated;
import utilities.ExtentManager;
import utilities.ScreenshotUtil;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class TestBase {

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties or = new Properties();
    public static FileInputStream fis;
    public static Logger log = Logger.getLogger("devpinoyLogger");
    //public static Excel excel = new Excel(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
    public static Excel_Updated excel = new Excel_Updated(System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
    public static WebDriverWait wait;
    public ExtentReports reports = ExtentManager.getInstance();
    public static ExtentTest extentTest;
    static WebElement dropdown;

    @BeforeSuite
    public void setUp(){
        if(driver==null){
            try {
                fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
                try {
                    config.load(fis);
                    log.debug("Config file Loaded");

                } catch (IOException e) {
                    e.printStackTrace();
                }

            try {
                fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                or.load(fis);
                log.debug("OR file Loaded");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(config.getProperty("browser").equals("chrome")){
               // System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\test\\resources\\executeables\\chromedriver.exe");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                log.debug("Chrome Launched !!!");
            }
            else if (config.getProperty("browser").equals("firefox")){
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            driver.get(config.getProperty("url"));
            log.debug("navigated to  |"+config.getProperty("url") +" |successfully ");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
           // driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")), TimeUnit.SECONDS);
           // wait = new WebDriverWait(driver,5);
            wait = new WebDriverWait(driver,Duration.ofSeconds(5));

        }
    }
    public void click(String locator){
        if(locator.endsWith("_CSS")){
            driver.findElement(By.cssSelector(or.getProperty(locator))).click();
        }
        else if(locator.endsWith("_XPATH")) {
            driver.findElement(By.xpath(or.getProperty(locator))).click();
        }
        else if(locator.endsWith("_ID")) {
            driver.findElement(By.id(or.getProperty(locator))).click();
        }
        else if(locator.endsWith("_NAME")){
            driver.findElement(By.name(or.getProperty(locator))).click();
        }

         //   extentTest.log(LogStatus.INFO,"Clicking on "+locator);
    }

    public void type(String locator,String value){
        if(locator.endsWith("_CSS")) {
            driver.findElement(By.cssSelector(or.getProperty(locator))).sendKeys(value);
        }
        else if(locator.endsWith("_XPATH")){
            driver.findElement(By.xpath(or.getProperty(locator))).sendKeys(value);
        }
       // extentTest.log(LogStatus.INFO,"Typing in  "+locator+ " value as "+value);
    }


    public boolean isElementPresent(By by){
        try {
            driver.findElement(by);
            return true;
        }
        catch (NoSuchElementException e){
            return false;
        }
    }

    public static void verifyEquals(String expected, String actual) throws IOException {
        try{
            Assert.assertEquals(actual,expected);
        }
        catch (Throwable t){
            ScreenshotUtil.captureScreenshot();
            Reporter.log("Click to see Screenshot");
            Reporter.log("<br>" + "Verification Failure : "+ t.getMessage()+"<br>");
            Reporter.log("<br>");
            Reporter.log("<a target=\"_blank\" href=" + ScreenshotUtil.screenShotName + ">Screenshot</a>");

            //Extent Report
            extentTest.log(LogStatus.FAIL,"Verification Failure"+ t.getMessage());
            extentTest.log(LogStatus.FAIL,extentTest.addScreencast(ScreenshotUtil.screenShotName));

        }
    }

    public void select(String locator, String value){
        if(locator.endsWith("_CSS")) {
           dropdown = driver.findElement(By.cssSelector(or.getProperty(locator)));
        }
        else if(locator.endsWith("_XPATH")){
          dropdown =  driver.findElement(By.xpath(or.getProperty(locator)));
        }

        Select select = new Select(dropdown);
        select.selectByVisibleText(value);
       // extentTest.log(LogStatus.INFO,"Selecting from dropdown  "+locator+ " value as "+value);
    }

    @AfterSuite
    public void tearDown(){
        if(driver !=null){
            driver.quit();
        }
        log.debug("teardown executed successfully");

    }
}
