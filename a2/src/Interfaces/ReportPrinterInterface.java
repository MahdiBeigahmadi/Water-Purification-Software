package Interfaces;

public interface ReportPrinterInterface {
    String REPORT_HEADER = "******************\n* Report Options *\n******************\n" +
            "1. ALL:           All products.\n" +
            "2. DEFECTIVE:     Products that failed their last test.\n" +
            "3. READY-TO-SHIP: Products passed tests, not shipped.\n" +
            "4. Cancel report request.\n> ";
}
