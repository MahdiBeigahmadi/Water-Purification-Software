/* Data.JSONFiles class
 * Data.JSONFiles.java
 *
 * Class Description: it asks user to give a json file path
 * it loads the data from json file
 * Class Invariant:
 *
 * Author: Mahdi Beigahmadi
 * Student ID: 301570853
 * Last modified: Jan. 2024
 */

package Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class JSONFiles extends UnitInfo {
    Scanner aPath = new Scanner(System.in);
    static public String filePath = null;

    public void readFromJsonFile() {
        System.out.print("Enter the path to the input JSON file; blank to cancel.\n" +
                "WARNING: This will replace all current data with data from the file.\n> ");

        filePath = aPath.nextLine();

        if (filePath.trim().isEmpty()) {
            System.out.println("Operation cancelled.");

        }
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new GsonBuilder()
                    .setPrettyPrinting()
                    .setDateFormat("yyyy-MM-dd").create();

            final List<UnitInfo> unitInfos = getInfoList(gson, reader);

            unitInfoStorage.clear();
            unitInfoStorage.addAll(unitInfos);
            sortUnitInfoStorage();

            System.out.println("Read " + unitInfoStorage.size()
                    + " products from JSON file '"
                    + filePath + "'.");
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        } catch (JsonSyntaxException e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
        }
    }

    private List<UnitInfo> getInfoList(Gson gson, FileReader reader) {
        Type listType = new TypeToken<List<UnitInfo>>() {
        }.getType();
        return gson.fromJson(reader, listType);
    }

    public void sortUnitInfoStorage() {
        unitInfoStorage.sort((firstElement, secondElement) -> {
            Double firstSerialNum = Double.parseDouble(firstElement.getSerialNumber());
            Double secondSerialNum = Double.parseDouble(secondElement.getSerialNumber());
            return firstSerialNum.compareTo(secondSerialNum);
        });
    }
}

//ChatGPT helped me find and fix the errors with this class.
//Also assisted me to figure out reading properly from json file
