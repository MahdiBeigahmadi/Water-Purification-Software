/* UnitInfoDisplayed class
 * UnitInfoDisplayed.java
 *
 * Class Description: prints the information of the units that
 * were entered by the user
 * Class Invariant:
 *
 * Author: Mahdi Beigahmadi
 * Student ID: 301570853
 * Last modified: Jan. 2024
 */

import Data.JSONFiles;
import Data.UnitInfo;
import Interfaces.RecordInterface;
import Interfaces.UnitInfoDisplayedInterface;

import java.util.Scanner;

public class UnitInfoDisplayed extends JSONFiles
        implements UnitInfoDisplayedInterface, RecordInterface {
    // ChatGPT helped me to make the code cleaner.
    private final Scanner scanner = new Scanner(System.in);

    public void showSummary() {
        if (JSONFiles.filePath == null) {
            System.out.println("No units defined.\n" +
                    "Please create a unit and then re-try this option.\n");
            new TextUI().showMenuItems();
        }
        System.out.print("Enter the serial number (0 for list, -1 for cancel): \n> ");

        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        if (option.equals("0")) {
            displayInformation();
            showSummary();
        } else if (option.length() > 1) {
            printUnitDetails(option);
        } else {
            System.out.print("\n");
            new TextUI().showMenuItems();
        }
    }

    // ChatGPT helped me to make the code cleaner and simpler.
    @Override
    public void displayInformation() {
        System.out.println(HEADER);
        if (JSONFiles.filePath == null) {
            System.out.println("No units found.");
            new TextUI().showMenuItems();
        } else {
            for (UnitInfo unit : unitInfoStorage) {
                System.out.println(formatUnitInfo(unit));
            }
        }
    }

    @Override
    public String formatUnitInfo(UnitInfo unit) {
        String serial = unit.getSerialNumber();
        int numberOfTests = (unit.tests != null) ? unit.tests.size() : 0;
        String shipDate = (unit.getDateShipped() != null) ? unit.getDateShipped() : "-";
        return String.format(INFO_FORMAT, unit.getModel(), serial, numberOfTests, shipDate);
    }

    @Override
    public void getTestInfoFromUser() {
        System.out.print("Enter the serial number (0 for list, -1 for cancel): \n> ");
        String inputFromUser = scanner.nextLine();
        if (inputFromUser.equals("0")) {
            displayInformation();
            getTestInfoFromUser();
        } else if (inputFromUser.equals("-1")) {
            new TextUI().showMenuItems();
        } else {
            processOption(inputFromUser);
        }
    }
}
