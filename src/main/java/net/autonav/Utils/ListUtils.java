package net.autonav.Utils;

import net.autonav.Subsystems.RBTSystem;

import java.io.*;
import java.util.List;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ListUtils {
    public static File saves = new File("C:\\Users\\llluy\\OneDrive\\Documents\\GitHub\\AutoNav-Interpreter\\src\\main\\java\\net\\autonav\\Utils\\lists.txt");
    static {
        if (!saves.exists()) {
            try {
                saves.createNewFile();
            } catch (Exception e) {
                RBTSystem.Logs.log("Failed to create saves file", RBTSystem.LogLevel.ERROR);
            }
        }
    }

    /**
     * Saves the list to a file
     * @param list List to save
     */
    public static void save(List list) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saves));
            oos.writeObject(list);
            oos.close();
        } catch (Exception e) {
            RBTSystem.Logs.log("Failed to save list", RBTSystem.LogLevel.ERROR);
        }
    }

    /**
     * Loads the list from a file
     * @param list List to load
     */
    public static void load(List list) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saves));
            Object readList = ois.readObject();
            if (readList instanceof List) {
                list.addAll((List) readList);
            }
            ois.close();
        } catch (Exception e) {
            RBTSystem.Logs.log("Failed to load list", RBTSystem.LogLevel.ERROR);
        }
    }
}
