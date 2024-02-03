/* UnitShipper class
 * UnitShipper.java
 *
 * Class Description: it asks user to ship a unit. also
 * it prints the products that they have passed the test but
 * been shipped yet
 * Class Invariant:
 *
 * Author: Mahdi Beigahmadi
 * Student ID: 301570853
 * Last modified: Jan. 2024
 */
import Data.UnitInfo;
import Interfaces.UnitShipperInterface;

import java.util.Comparator;
import java.util.Scanner;

public class UnitShipper extends UnitInfo
        implements UnitShipperInterface {
    public static final String SHIPMENT_FORMAT = "%-15s %-15s %-10s";
    private final Scanner scanner = new Scanner(System.in);

    public UnitShipper() {
    }

    public void shipUnit() {
        while (true) {
            System.out.print("Enter serial number to ship: ");
            String serialNumForShip = scanner.nextLine();
            if (validateShipment(serialNumForShip)) {
                break;
            }
        }
    }

    private boolean validateShipment(String serialNumForShip) {
        if ("0".equals(serialNumForShip)) {
            new UnitInfoDisplayed().showSummary();
            return true;
        } else {
            for (UnitInfo unitInfo : unitInfoStorage) {
                if (serialNumForShip.equals(unitInfo.getSerialNumber())) {
                    unitInfo.setDateShipped(getCurrentDate());
                    System.out.println("Unit successfully shipped.");
                    return true;
                }
            }
            System.out.println("No unit found matching serial '" + serialNumForShip + "'");
            return false;
        }
    }

    public void showReadyToShipUnits() {
        System.out.println(READY_TO_SHIP_HEADER);
        for (UnitInfo unit : unitInfoStorage) {
            if (unit.tests != null && !unit.tests.isEmpty()) {
                String readyOrder = readyToShipItems(unit);
                if (!readyOrder.isEmpty()) {
                    System.out.print(readyOrder);
                }
            }
        }
    }


    private String readyToShipItems(UnitInfo unit) {
        boolean isGoodToShip = unit.tests != null && unit.getDateShipped()
                .equals("-");

        if (isGoodToShip) {
            boolean allTestsPassed =
                    unit.tests.stream().allMatch(test -> test.isTestPassed);
            if (allTestsPassed) {
                String latestTestDate = unit.tests.stream()
                        .map(test -> test.date)
                        .max(Comparator.naturalOrder())
                        .orElse("");
                return showReadyOrders(unit.getModel(), unit.getSerialNumber(), latestTestDate);
            }
        }
        return "";
    }

    private String showReadyOrders(String unit, String serialNumber, String testDate) {
        String truncatedSerialNumber = serialNumber.length() > 15
                ? serialNumber.substring(0, 15) : serialNumber;
        return String.format(SHIPMENT_FORMAT, unit, truncatedSerialNumber, testDate) + "\n";

    }
}

