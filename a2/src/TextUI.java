/* UI.TextUI class
 * UI.TextUI.java
 *
 * Class Description: it asks user to give the command to the code
 * also, this class runs the app
 * Class Invariant:
 *
 * Author: Mahdi Beigahmadi
 * Student ID: 301570853
 * Last modified: Jan. 2024
 */

//ATTENTION: !!! I USED MY PREVIOUS ASSIGNMENT'S CODE HERE TO SHOW THE MENU !!! //

import Data.JSONFiles;
import Data.UnitCreator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {
    public final Scanner
            scanner = new Scanner(System.in);

    public void greetTheUser() {
        System.out.println("**********************************\n" +
                "Water Purification Inventory Management\n" +
                "by Mahdi Beigahmadi.\n" +
                "**********************************\n");
        showMenuItems();
    }

    public void showMenuItems() {
        String[] menuOptions = {
                "Read JSON input file.",
                "Display info on a unit.",
                "Create new unit.",
                "Test a unit.",
                "Ship a unit.",
                "Print report.",
                "Set report sort order.",
                "Exit"
        };

        while (true) {
            try {
                System.out.println("*************\n* Main Menu *\n*************");
                for (int i = 0; i < menuOptions.length; i++) {
                    System.out.printf("%d. %s\n", i + 1, menuOptions[i]);
                }
                System.out.print("> ");

                int menuOption = scanner.nextInt();
                boolean condition = menuOption < 1 || menuOption > menuOptions.length;
                if (condition) {
                    System.out.println("Error: Please enter a selection between 1 and " + menuOptions.length);
                    continue;
                }
                if (switchHolder(menuOption)) return;
            } catch (InputMismatchException ex) {
                System.out.println("Error: Please enter a numeric value.");
                scanner.next();
            }
        }
    }
    private boolean switchHolder(int menuOption) {
        switch (menuOption) {
            case 1:
                new JSONFiles().readFromJsonFile();
                break;
            case 2:
                new UnitInfoDisplayed().showSummary();
                break;
            case 3:
                new UnitCreator().addNewUnit();
                break;
            case 4:
                new UnitInfoDisplayed().getTestInfoFromUser();
                break;
            case 5:
                new UnitShipper().shipUnit();
                break;
            case 6:
                new ReportPrinter().printReport();
                break;
            case 7:
                new ReportPrinter().setReportSorter();
                break;
            case 8:
                System.exit(0);
            default:
                break;
        }
        return false;
    }
}
