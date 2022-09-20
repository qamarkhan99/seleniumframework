package base;

import com.relevantcodes.extentreports.LogStatus;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import utilities.ScreenshotUtil;

import java.io.IOException;


public class Listeners  extends TestBase implements ITestListener {

    public void onTestFailure(ITestResult arg0){

        System.setProperty("org.uncommons.reportng.escape-output","false");
        try {
            ScreenshotUtil.captureScreenshot();
        } catch (IOException e) {
            e.printStackTrace();
            //Reporter.log(“<a target=\"_blank\" href=\""+ ScreenshotUtil.screenShotName+"" >Screenshot</a>”);
        }
        extentTest.log(LogStatus.FAIL,arg0.getMethod().getMethodName()+"Failed with Exception"+ arg0.getThrowable());
        extentTest.log(LogStatus.FAIL,extentTest.addScreencast(ScreenshotUtil.screenShotName));

        reports.endTest(extentTest);
        reports.flush();

        Reporter.log("Capturing Screenshot");
        Reporter.log("<a target=\"_blank\" href=" + ScreenshotUtil.screenShotName + ">Screenshot</a>");
    }


    public void onTestSuccess(ITestResult arg0){
        extentTest.log(LogStatus.PASS,arg0.getMethod().getMethodName().toUpperCase()+"PASS");
        reports.endTest(extentTest);
        reports.flush();
    }

    public void onTestStart(ITestResult result){
        String methodName = result.getMethod().getMethodName();
        String logText = "<b>"+"Test Case :"+methodName+" Passed"+"<b>";
      //  extentTest.log(logText);
       // extentTest = reports.startTest(arg0.getMethod().getMethodName().toUpperCase());
       /* try {
            if(IsTestRunnable.isTestRunnable(arg0.getTestName(),excel)){
                throw new SkipException("Skipping the test "+ arg0.getTestName()+ "as the run mode is false");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
   */

    }

    public void onTestSkipped(ITestResult arg0){
        extentTest.log(LogStatus.SKIP,arg0.getMethod().getMethodName()+" Skipped the Test");
        reports.endTest(extentTest);
        reports.flush();
    }

}
