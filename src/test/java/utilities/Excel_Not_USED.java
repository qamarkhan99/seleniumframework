package utilities;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.DataProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Excel_Not_USED {

    private static final DataFormatter dataFormatter = new DataFormatter();
    private static final String FILE = "\\src\\test\\resources\\excel\\testdata.xlsx";


    @DataProvider(name="dp")
    public Object [][] getData(Method m) throws IOException {
        String sheetName = m.getName();
        Workbook workbook = WorkbookFactory.create(new File(FILE));
        Sheet sheet = workbook.getSheet(sheetName);
        Iterable<Row> rows = sheet::rowIterator;

        List<Map<String, String>> results = new ArrayList<>();
        boolean header = true;
        List<String> keys = null;
        for (Row row : rows) {
            List<String> values = getValuesInEachRow(row);
            if (header) {
                header = false;
                keys = values;
                continue;
            }
            results.add(transform(keys, values));
        }
        return asTwoDimensionalArray(results);
    }

    private static Object[][] asTwoDimensionalArray(List<Map<String, String>> interimResults) {
        Object[][] results = new Object[interimResults.size()][1];
        int index = 0;
        for (Map<String, String> interimResult : interimResults) {
            results[index++] = new Object[] {interimResult};
        }
        return results;
    }

    private static Map<String, String> transform(List<String> names, List<String> values) {
        Map<String, String> results = new HashMap<>();
        for (int i = 0; i < names.size(); i++) {
            String key = names.get(i);
            String value = values.get(i);
            results.put(key, value);
        }
        return results;
    }

    private static List<String> getValuesInEachRow(Row row) {
        List<String> data = new ArrayList<>();
        Iterable<Cell> columns = row::cellIterator;
        for (Cell column : columns) {
            data.add(dataFormatter.formatCellValue(column));
        }
        return data;
    }
}
