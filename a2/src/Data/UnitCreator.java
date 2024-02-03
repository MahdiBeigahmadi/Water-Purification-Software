/* Data.UnitCreator class
 * Data.UnitCreator.java
 *
 * Class Description: it adds a new unit to the unit info class
 * Class Invariant: must pass the checksums
 *
 * Author: Mahdi Beigahmadi
 * Student ID: 301570853
 * Last modified: Jan. 2024
 */
package Data;

import java.util.Scanner;
import java.util.InputMismatchException;

public class UnitCreator extends UnitInfo {
    public UnitCreator() {
    }

    public void addNewUnit() {
        final Result result = getResult();
        boolean isValidSerialNumber;
        do {
            isValidSerialNumber = isValidSerialNumber(result);
        } while (!isValidSerialNumber);
    }

    private boolean isValidSerialNumber(Result result) {
        double serialNumDouble;
        boolean isValidSerialNumber;
        System.out.print("Serial number: ");
        isValidSerialNumber = true;
        try {
            serialNumDouble = result.scanner.nextDouble();
            if (!checkSum(serialNumDouble)) {
                System.out.println("Unable to add the product.");
                System.out.println("\t'Serial Number Error: Checksum does not match.'\nPlease try again.");
                isValidSerialNumber = false;
            } else {
                unitInfoStorage.add(new UnitInfo(result.modelNumberByUser,
                        String.valueOf((int) serialNumDouble)));
                System.out.println("The unit was added to the inventory successfully.");
                new JSONFiles().sortUnitInfoStorage();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid serial number.");
            isValidSerialNumber = false;
            result.scanner.next();
        }
        return isValidSerialNumber;
    }

    private Result getResult() {
        System.out.print("Enter product info; blank line to quit.\nModel:");
        Scanner scanner = new Scanner(System.in);
        String modelNumberByUser = scanner.nextLine();
        return new Result(scanner, modelNumberByUser);
    }

    private boolean checkSum(double serialNumDouble) {
        int lastTwoDigits = (int) serialNumDouble % 100;
        double digitSums = calculateSum((int) (serialNumDouble / 100));
        return (int) (digitSums % 100) == lastTwoDigits;
    }

    private double calculateSum(int number) {
        double sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    private static class Result {
        public final Scanner scanner;
        public final String modelNumberByUser;

        public Result(Scanner scanner, String modelNumberByUser) {
            this.scanner = scanner;
            this.modelNumberByUser = modelNumberByUser;
        }
    }
}
