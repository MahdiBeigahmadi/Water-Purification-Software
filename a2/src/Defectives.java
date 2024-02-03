/* Defectives class
 * Defectives.java
 *
 * Class Description: It generates data about defective units
 * Class Invariant:
 *
 * Author: Mahdi Beigahmadi
 * Student ID: 301570853
 * Last modified: Feb. 2024
 */

import Data.UnitInfo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class Defectives extends SortController {
    public Defectives() {
        super();
    }

    protected String formatDefectiveUnits(UnitInfo unit) {
        StringBuilder defectiveUnitsInfo = new StringBuilder();
        if (unit.tests != null && !unit.tests.isEmpty()) {
            Test latestTest = unit.tests.stream()
                    .max(Comparator.comparing(test ->
                            LocalDate.parse(test.date,
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd"))))
                    .orElse(null);
            if (!latestTest.isTestPassed) {
                printDefectives(unit, latestTest, defectiveUnitsInfo);
            }
        }
        return defectiveUnitsInfo.toString();
    }

    private void printDefectives(UnitInfo unit, Test test,
                                 StringBuilder defectiveUnitsInfo) {
        int countTests = (unit.tests != null) ? (unit.tests.size()) : 0;
        String testResult = test.testResultComment != null ? test.testResultComment : "-";
        if (!test.isTestPassed) {
            defectiveUnitsInfo.append(String.format(ReportPrinter.DEFECTIVE_FORMAT, unit.getModel(),
                    unit.getSerialNumber(), countTests, getLatestTestDate(unit), testResult)).append("\n");
        }
    }
}
