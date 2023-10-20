package net.autonav.Subsystems;

import jakarta.persistence.Id;
import net.samuelcampos.usbdrivedetector.USBDeviceDetectorManager;
import net.samuelcampos.usbdrivedetector.USBStorageDevice;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RBTSystem {
    public static Controllers controller; 
    /*
     * TODO: Set on startup
     * TODO: Make variable utils to store this on restart
     */
    
    /**
     * Controller handler class
     */
    public static class Controller {
        private static File saves = new File("C:\\Users\\llluy\\OneDrive\\Documents\\GitHub\\AutoNav-Interpreter\\src\\main\\java\\net\\autonav\\Utils\\controller.txt");
        /**
         * Toggles the controller between human and autonomous
         */
        public static void toggleController() {
            if (controller == Controllers.HUMAN) {
                controller = Controllers.AUTONOMOUS;
                Logs.log("Controller set to autonomous", LogLevel.INFO);
            } else {
                controller = Controllers.HUMAN;
                Logs.log("Controller set to human", LogLevel.INFO);
            }
        }

        /**
         * Sets the controller to the specified controller
         * @param controller Controllers enum
         */
        public static void set(Controllers controller) {
            RBTSystem.controller = controller;
            try {
                FileWriter savesWriter = new FileWriter(saves);
                savesWriter.write(controller.toString());
                savesWriter.close();
                Logs.log("Controller set to " + controller, LogLevel.INFO);
            } catch (IOException e) {
                Logs.log("Failed to set controller to " + controller, LogLevel.ERROR);
            }
        }

        /**
         * Gets the current controller
         * @return Current controller of type Controllers
         */
        public static Controllers getController() {
            Logs.log("Controller requested", LogLevel.INFO);
            return controller;
        }

        /**
         * Loads the controller from the saves file
         */
        public static void load() {
            try {
                FileReader savesReader = new FileReader(saves);
                BufferedReader savesBuffer = new BufferedReader(savesReader);
                String line;
                while ((line = savesBuffer.readLine()) != null) {
                    if (line.equals("HUMAN")) {
                        controller = Controllers.HUMAN;
                    } else if (line.equals("AUTONOMOUS")) {
                        controller = Controllers.AUTONOMOUS;
                    }
                }
                savesReader.close();
                savesBuffer.close();
            } catch (IOException e) {
                Logs.log("Failed to load controller", LogLevel.ERROR);
            }
        }
    }

    public static class Movement {
        
    }

    /**
     * Log handler class
     */
    public static class Logs {
        @Id
        private static int id;
        private static final File logDir = new File("C:\\Users\\llluy\\OneDrive\\Documents\\GitHub\\AutoNav-Interpreter\\src\\main\\java\\net\\autonav\\Logs");
        static {
            for (File file : Objects.requireNonNull(logDir.listFiles())) {
                if (file.exists()) {
                    id++;
                }
            }
        }
        private static final File logFile = new File(logDir, id + ".txt");

        /**
         * Creates a new log file
         */
        public static void newLog() {
            if (!logFile.exists()) {
                try {
                    if (logFile.createNewFile()) {
                        System.out.println("Log file created");
                        System.out.println("Log file path: " + logFile.getAbsolutePath());
                    } else {
                        System.out.println("Log file already exists");
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Log file already exists at " + logFile.getAbsolutePath());
                newLog();
            }
        }

        /**
         * Gets the current log file
         * @return Current log file id
         */
        public static Integer currentLog() {
            return id;
        }

        /**
         * Logs the specified String and coresponding LogLevel to current log file
         * @param log String log
         * @param level LogLevel enum
         */
        public static void log(String log, LogLevel level) {
            try {
                FileWriter logWriter = new FileWriter(logFile, true);
                logWriter.write("[" + level + "] " + log + "\n");
                logWriter.close();
                System.out.println("[" + level + "] " + log);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Logs the specified String and coresponding LogLevel to specified log file
         * @param log String log
         * @param level LogLevel enum
         * @param id Integer id of log file
         */
        public static void log(String log, LogLevel level, Integer id) {
            try {
                File logFile = new File(logDir, id + ".txt");
                FileWriter logWriter = new FileWriter(logFile, true);
                logWriter.write("[" + level + "] " + log + "\n");
                logWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         * Analyzes the current log file
         * TODO: Log this to the interface
         * @param id Integer id of log file
         */
        public static void analyzeLog(Integer id) {
            List<String> lines = getStrings(id);
            int info = 0;
            int warn = 0;
            int error = 0;
            int fatal = 0;
            for (String line : lines) {
                if (line.contains("[INFO]")) {
                    info++;
                } else if (line.contains("[WARN]")) {
                    warn++;
                } else if (line.contains("[ERROR]")) {
                    error++;
                } else if (line.contains("[FATAL]")) {
                    fatal++;
                }
            }
            String summary = "Log summary for " + id + ".txt:\n" +
                    "INFO: " + info + "\n" +
                    "WARN: " + warn + "\n" +
                    "ERROR: " + error + "\n" +
                    "FATAL: " + fatal;
            if (error > 0 || fatal > 0) {
                System.out.println("Log contains errors or fatal errors. Please make sure to check the log file for more information");
            }
            System.out.println(summary);
        }

        @NotNull
        private static List<String> getStrings(Integer id) {
            File logFile = new File(logDir, id + ".txt");
            if (!logFile.exists()) throw new RuntimeException("Log file does not exist");
            List<String> lines = new ArrayList<>();
            FileReader logReader;
            try {
                logReader = new FileReader(logFile);
                BufferedReader logBuffer = new BufferedReader(logReader);
                String line;
                while ((line = logBuffer.readLine()) != null) {
                    lines.add(line);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return lines;
        }

        /**
         * Uploads the current log file to the current drive connected
         * @throws IOException
         */
        public static void uploadLog() throws IOException {
            uploadFileToDrive(logFile.getAbsolutePath());
        }

        /**
         * Uploads the specified log file to the current drive connected
         * @param id Integer id of log file
         * @throws IOException
         */
        public static void uploadLog(int id) throws IOException {
            try {
                File logFile = new File(logDir, id + ".txt");
                uploadFileToDrive(logFile.getAbsolutePath());
            } catch (Exception e) {
                log("Cannot find log", LogLevel.ERROR);
            }
        }

        public static void uploadFileToDrive(String filePath) throws IOException {
            USBDeviceDetectorManager driveDetector = new USBDeviceDetectorManager();
            List<USBStorageDevice> drives = new ArrayList<>();
            for (Object drive : driveDetector.getRemovableDevices()) {
                drives.add((USBStorageDevice) drive);
            }
            driveDetector.addDriveListener(System.out::println);

            USBStorageDevice drive = drives.get(0);

            if (!drive.canWrite()) {
                RBTSystem.Logs.log("The selected drive is not writable.", LogLevel.ERROR);
                driveDetector.close();
                return;
            }

            Path sourcePath = Paths.get(filePath);

            Path targetPath = Paths.get(drive.getRootDirectory().getPath(), sourcePath.getFileName().toString());

            try {
                Files.copy(sourcePath, targetPath);
                driveDetector.close();
            } catch (IOException e) {
                e.printStackTrace();
                RBTSystem.Logs.log("Failed to copy file to drive", LogLevel.ERROR);
                driveDetector.close();
            }
        }
    }

    public static class CommandHandler {
        public static void execute(String[] command) {
            
        }
    }

    public enum LogLevel {
        INFO,
        WARN,
        ERROR,
        FATAL
    }

    public enum Controllers {
        HUMAN, 
        AUTONOMOUS
    }
}
