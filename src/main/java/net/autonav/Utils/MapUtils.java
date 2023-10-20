package net.autonav.Utils;

import net.autonav.Subsystems.RBTSystem;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MapUtils {
    public static File saves = new File("C:\\Users\\llluy\\OneDrive\\Documents\\GitHub\\AutoNav-Interpreter\\src\\main\\java\\net\\autonav\\Utils\\saves.txt");
    static {
        if (!saves.exists()) {
            try {
                saves.createNewFile();
            } catch (IOException e) {
                RBTSystem.Logs.log("Failed to create saves file", RBTSystem.LogLevel.ERROR);
            }
        }
    }
    public static Map<String, Map> maps = new HashMap<>();

    /**
     * Saves the map to a file
     * @param map Map to save
     */
    public static void save(Map map) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saves));
            oos.writeObject(map);
            oos.close();
            RBTSystem.Logs.log("Saved map " + map.get("name"), RBTSystem.LogLevel.INFO);
        } catch (Exception e) {
            RBTSystem.Logs.log("Failed to save map " + map.get("name"), RBTSystem.LogLevel.ERROR);
        }
    }

    /**
     * Loads the map from a file
     * @param map Map to load
     */
    public static void load(Map map) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saves));
            Object readMap = ois.readObject();
            if (readMap instanceof Map) {
                map.putAll((HashMap) readMap);
            }
            ois.close();
        } catch (Exception e) {
            RBTSystem.Logs.log("Failed to load map " + map.get("name"), RBTSystem.LogLevel.ERROR);
        }
    }
}
