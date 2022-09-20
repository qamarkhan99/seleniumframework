package utilities;


import java.io.IOException;

public class IsTestRunnable {

    public static boolean isTestRunnable (String testName,Excel excel) throws IOException {
    String sheetName = "testSuite";
    int rows = excel.getRowCount(sheetName);
    for (int rNum = 2; rNum < rows; rNum++) {
        String testCase = excel.getCellData(sheetName, 1, rNum);
        System.out.println(testCase);
        if (testCase.equalsIgnoreCase(testName)) {
            String runMode = excel.getCellData(sheetName, 2, rNum);
            System.out.println(runMode);
            if (runMode.equalsIgnoreCase("Y"))
                return true;
            else
                return false;
        }
    }
    return false;
}

    }

