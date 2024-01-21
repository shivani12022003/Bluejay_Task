import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmployeeAnalyzer {

    public static void main(String[] args) {
        try {
            // Load the Excel file
            FileInputStream file = new FileInputStream(new File("Assignment_Timecard.xlsx"));
            Workbook workbook = new XSSFWorkbook(file);

            // Assuming the data is in the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            // Iterate through rows
            for (Row row : sheet) {
                // Skip the header row
                if (row.getRowNum() == 0) {
                    continue;
                }

                // Extract relevant columns
                String employeeName = row.getCell(7).getStringCellValue();
                String position = row.getCell(1).getStringCellValue();

                // Print employees meeting conditions
                if (workedFor7ConsecutiveDays(row) || hasShortBreak(row) || hasLongSingleShift(row)) {
                    System.out.println("Employee Name: " + employeeName + ", Position: " + position);
                }
            }

            // Close the workbook
            workbook.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean workedFor7ConsecutiveDays(Row row) {
        // Add logic to check if an employee has worked for 7 consecutive days
        // You may need to modify this based on the actual structure of your Excel file
        return false;
    }

    private static boolean hasShortBreak(Row row) {
        // Check if the time between shifts is less than 10 hours but greater than 1 hour
        double timeBetweenShifts = row.getCell(4).getNumericCellValue();
        return timeBetweenShifts > 1 && timeBetweenShifts < 10;
    }

    private static boolean hasLongSingleShift(Row row) {
        // Check if an employee has worked for more than 14 hours in a single shift
        double singleShiftHours = row.getCell(5).getNumericCellValue();
        return singleShiftHours > 14;
    }
}
