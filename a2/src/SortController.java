/* SortController class
 * SortController.java
 *
 * Class Description: a class assists us to better control the sort operations
 * Class Invariant:
 *
 * Author: Mahdi Beigahmadi
 * Student ID: 301570853
 * Last modified: Feb. 2024
 */
import Data.JSONFiles;
import Data.UnitInfo;

import java.time.LocalDate;
import java.util.Comparator;

import static Interfaces.RecordInterface.scanner;

public class SortController extends UnitInfo {
    public SortController() {
        super();
    }

    public void setReportSorter() {
        System.out.print(ReportPrinter.SORT_HEADER);
        int sortType = scanner.nextInt();
        switch (sortType) {
            case 1:
                new JSONFiles().sortUnitInfoStorage();
                break;
            case 2:
                sortByModel();
                new JSONFiles().sortUnitInfoStorage();
                break;
            case 3:
                sortByTestDate();
                break;
            default:
                System.out.print("\n");
                new TextUI().showMenuItems();
                break;
        }
    }

    public void sortByTestDate() {
        SortController.unitInfoStorage.sort((unit1, unit2) -> {
            LocalDate latestDate1 = getLatestTestDate(unit1);
            LocalDate latestDate2 = getLatestTestDate(unit2);
            return Comparator.nullsLast(LocalDate::compareTo).compare(latestDate2, latestDate1);
        });
    }

    public void sortByModel() {
        SortController.unitInfoStorage.sort(Comparator.comparing(UnitInfo::getModel,
                Comparator.nullsFirst(String::compareTo)));
    }
}
