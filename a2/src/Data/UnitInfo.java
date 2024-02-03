/* Unit Info class
 * Data.UnitInfo.java
 *
 * Class Description: it is the main info holder of
 * water purification systems
 * Class Invariant: model number no greater than 10 chars
 * serial number should be 15 digits max
 *
 * Author: Mahdi Beigahmadi
 * Student ID: 301570853
 * Last modified: Jan. 2024
 */
package Data;

import Interfaces.UnitInfoHeaderInterface;

import java.lang.String;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class UnitInfo implements UnitInfoHeaderInterface {
    public static final String INFO_FORMAT = "%-15s %-15s %-10d %-15s";
    public static List<UnitInfo> unitInfoStorage
            = new ArrayList<>();
    public List<Test> tests;
    private String serialNumber;
    private String model;
    private String dateShipped = "-";

    protected UnitInfo() {
    }

    public UnitInfo(String modelNumberByUser, String serialNumDouble) {
        this.serialNumber = serialNumDouble;
        this.model = modelNumberByUser;
        this.tests = new ArrayList<>();
        this.dateShipped = "-";
    }

    public static String getCurrentDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return currentDate.format(formatter);
    }

    public String getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(String date) {
        this.dateShipped = date;
    }

    public String getModel() {
        return model;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    protected LocalDate getLatestTestDate(UnitInfo unit) {
        if (unit.tests == null || unit.tests.isEmpty()) return null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return unit.tests.stream()
                .map(test -> LocalDate.parse(test.date, formatter))
                .max(LocalDate::compareTo)
                .orElse(null);
    }

    @Override
    public String toString() {
        return "UnitInfo{" +
                "tests=" + tests +
                ", serialNumber='" + serialNumber + '\'' +
                ", model='" + model + '\'' +
                ", dateShipped='" + dateShipped + '\'' +
                '}';
    }

    public static class Test {
        public String date;
        public boolean isTestPassed;
        public String testResultComment;

        public Test(String date, boolean isTestPassed,
                    String testResultComment) {
            this.testResultComment = testResultComment;
            this.isTestPassed = isTestPassed;
            this.date = date;
        }

        public String turnBoolPassFail() {
            if (isTestPassed) {
                return "Passed";
            } else return "Failed";
        }
    }
}
