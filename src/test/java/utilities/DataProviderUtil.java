package utilities;

import base.TestBase;
import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;
import java.util.Hashtable;

public class DataProviderUtil extends TestBase {



/*
    @DataProvider(name="dp")
    public Object [][] getData(Method m) throws IOException {
        String sheetName = m.getName();
        int rows = excel.getRowCount(sheetName);
        int cells = excel.getCellCount(sheetName,1);
        Object[][] data = new Object[rows][cells];
     //   HashMap<String,String> map ;
        for(int i=1;i<rows;i++){
     //       map = new HashMap<>();
            for(int j=0;j<cells;j++){
        //        map.put(excel.getCellData(sheetName,0,j),excel.getCellData(sheetName,i,j));
              data[i][j]=excel.getCellData(sheetName,i,j);
                System.out.println(data[i][j]);
             //     data[i][0]=map;

            }
        }
        return data;
    }
*/
@DataProvider(name="dp")
public Object[][] getData(Method m) {

    String sheetName = m.getName();
    int rows = excel.getRowCount(sheetName);
    int cols = excel.getColumnCount(sheetName);

    Object[][] data = new Object[rows - 1][1];

    Hashtable<String,String> table = null;

    for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

        table = new Hashtable<String,String>();

        for (int colNum = 0; colNum < cols; colNum++) {

            // data[0][0]
            table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
            data[rowNum - 2][0] = table;
        }

    }

    return data;

}



}
