package utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public Object[][] getData() throws IOException {

        // Excel file path
        //String path = System.getProperty("user.dir") + "/testData/Opencart_LoginData.xlsx";
    	String path = "./testData/nopCommerce_testData.xlsx";

        ExcelUtility xlutil = new ExcelUtility(path);

        int totalRows = xlutil.getRowCount("Sheet1");

        // Dynamically detect the number of non-empty columns in the header row (row 0)
        int totalCols = 0;
        int headerCols = xlutil.getCellCount("Sheet1", 0);
        for (int j = 0; j < headerCols; j++) {
            String headerCell = xlutil.getCellData("Sheet1", 0, j).trim();
            if (!headerCell.isEmpty()) {
                totalCols++;
            }
        }

        // Collect non-empty rows
        List<String[]> dataList = new ArrayList<>();

        for (int i = 1; i <= totalRows; i++) { // Start from row 1 (row 0 is header)
            String[] rowData = new String[totalCols];
            boolean rowHasData = false;

            for (int j = 0; j < totalCols; j++) {
                String cellData = xlutil.getCellData("Sheet1", i, j).trim();
                rowData[j] = cellData;

                if (!cellData.isEmpty()) {
                    rowHasData = true;
                }
            }

            // Add row only if it has at least one non-empty cell
            if (rowHasData) {
                dataList.add(rowData);
            }
        }

        // Convert List<String[]> to Object[][]
        Object[][] logindata = new Object[dataList.size()][totalCols];
        for (int i = 0; i < dataList.size(); i++) {
            logindata[i] = dataList.get(i);
        }

        return logindata;
    }
}
