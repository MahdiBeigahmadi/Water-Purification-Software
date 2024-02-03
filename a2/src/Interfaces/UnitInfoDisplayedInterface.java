package Interfaces;

import Data.UnitInfo;

import java.util.Objects;

public interface UnitInfoDisplayedInterface {
    default void printUnitDetails(String option) {
        System.out.println("Unit details:");
        for (UnitInfo unit : UnitInfo.unitInfoStorage) {
            if (Objects.equals(unit.getSerialNumber(), option)) {
                showUnitInformation(unit);
                printUnitDetails(unit);
                break;
            }
        }
    }

    default void showUnitInformation(UnitInfo unit) {
        System.out.println("Serial: " + unit.getSerialNumber());
        System.out.println("Model: " + unit.getModel());
        System.out.println("Ship Date: " + unit.getDateShipped() + "\n\n");
        System.out.println("Tests\n*********\n        Date   Passed? " +
                " Test Comments\n------------  --------  -------------");
    }

    default void printUnitDetails(UnitInfo unit) {
        if (unit.tests != null) {
            for (UnitInfo.Test test : unit.tests) {
                System.out.printf(" " + test.date + "     "
                        + test.turnBoolPassFail()
                        + "     " +
                        test.testResultComment + "\n");
            }
        }
    }

    // ChatGPT helped me to make the code cleaner and simpler.
    void displayInformation();
}
