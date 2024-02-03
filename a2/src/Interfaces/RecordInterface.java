package Interfaces;

import Data.JSONFiles;
import Data.UnitInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public interface RecordInterface {
    Scanner scanner = new Scanner(System.in);

    String formatUnitInfo(UnitInfo unit);

    void getTestInfoFromUser();

    default void processOption(String option) {
        while (true) {
            boolean found = false;
            for (UnitInfo unitInfo : UnitInfo.unitInfoStorage) {
                if (Objects.equals(unitInfo.getSerialNumber(), option)) {
                    recordTestResult(unitInfo, option);
                    found = true;
                    break;
                }
            }
            if (found) {
                break;
            }
            if(JSONFiles.filePath == null) {
                System.out.println("Please add the data json files before trying to modifying the data.");
                return;
            } else {
                System.out.println("No unit found matching serial '" + option + "'");
                System.out.println("Please enter the serial number again: ");
                option = scanner.nextLine();
            }
        }
    }

    default void recordTestResult(UnitInfo unit, String option) {
        if (JSONFiles.filePath == null) return;

        String yesNo = promptUserForInput("y", "n");
        String comment = promptForComment();

        boolean testPassed = "y".equalsIgnoreCase(yesNo);
        String testDate = UnitInfo.getCurrentDate();

        UnitInfo.Test newTest = new UnitInfo.Test(testDate, testPassed, comment);
        if (unit.tests == null) {
            unit.tests = new ArrayList<>();
        }
        unit.tests.add(newTest);

        System.out.println("Test recorded.");
    }

    private String promptUserForInput(String... acceptableInputs) {
        System.out.print("Pass? (y/n): ");
        String input = scanner.nextLine();
        boolean acceptableInput = Arrays.stream(acceptableInputs)
                .anyMatch(input::equalsIgnoreCase);

        while (!acceptableInput) {
            System.out.print("Error: Please enter [Y]es or [N]o:");
            input = scanner.nextLine();
            acceptableInput = Arrays.stream(acceptableInputs)
                    .anyMatch(input::equalsIgnoreCase);
        }

        return input;
    }

    private String promptForComment() {
        System.out.print("Comment: ");
        return scanner.nextLine();
    }
}
