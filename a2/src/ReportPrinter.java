/* PrintReporter class
 * PrintReporter.java
 *
 * Class Description: It generates reports based on various requests
 * Class Invariant:
 *
 * Author: Mahdi Beigahmadi
 * Student ID: 301570853
 * Last modified: Jan. 2024
 */

import Data.UnitInfo;
import Interfaces.*;

import java.util.Scanner;

public class ReportPrinter extends Defectives implements
        ReportPrinterInterface,
        ReportPrinterSort, ReportPrinterDefectiveInterface {

    public static final String DEFECTIVE_FORMAT = "%-15s %-15s %-10d %-15s %-15s";
    public final Scanner scanner = new Scanner(System.in);

    public ReportPrinter() {
        super();
    }

    public void printReport() {
        do {
            System.out.print(REPORT_HEADER);
            String input = scanner.nextLine();
            int userResponse;
            try {
                userResponse = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number between 1 and 4");
                continue;
            }
            boolean acceptanceBound = userResponse < 1 || userResponse > 4;
            if (acceptanceBound) {
                System.out.println("Error: Please enter a selection between 1 and 4");
                continue;
            }

            processUserResponse(userResponse);
        } while (true);
    }

    void processUserResponse(int userResponse) {
        switch (userResponse) {
            case 1:
                displayAllProducts();
                break;
            case 2:
                displayDefectiveUnits();
                break;
            case 3:
                showReadyToShipUnits();
                break;
            default:
                displayMenuAgain();
                break;
        }
    }

    void displayAllProducts() {
        UnitInfoDisplayedInterface allProducts = new UnitInfoDisplayed();
        allProducts.displayInformation();
        System.out.println();
        new TextUI().showMenuItems();
    }

    void displayDefectiveUnits() {
        System.out.println(ReportPrinterDefectiveInterface.DEFECTIVE_HEADER);
        for (UnitInfo unit : UnitInfo.unitInfoStorage) {
            System.out.print(formatDefectiveUnits(unit));
        }
    }

    void showReadyToShipUnits() {
        new UnitShipper().showReadyToShipUnits();
    }

    void displayMenuAgain() {
        System.out.println();
        new TextUI().showMenuItems();
    }
}
