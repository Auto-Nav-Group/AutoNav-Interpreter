package net.autonav.Utils;

import net.autonav.Subsystems.RBTSystem;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings({"rawtypes", "unchecked"})
public class MapUtil {
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

    public static void load(Map map) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saves));
            Object readMap = ois.readObject();
            if (readMap instanceof Map) {
                map.putAll((HashMap) readMap);
            }
        } catch (Exception e) {
            RBTSystem.Logs.log("Failed to load map " + map.get("name"), RBTSystem.LogLevel.ERROR);
        }
    }
}
