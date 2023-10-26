package net.autonav.Subsystems;

import net.samuelcampos.usbdrivedetector.USBDeviceDetectorManager;
import net.samuelcampos.usbdrivedetector.USBStorageDevice;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.*;
import java.util.*;


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
        private static final File saves = new File("C:\\Users\\llluy\\OneDrive\\Documents\\GitHub\\AutoNav-Interpreter\\src\\main\\java\\net\\autonav\\Utils\\controller.txt");
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

    // public static class Communication {
    //     /**
    //      * Communication protocol for dispatch and reception of AutoNav communications
    //      * Sending data to the interface is as follows:
    //      * <ul>
    //      * <li>Send the packet to the RoboRIO</li>
    //      * <li>The RoboRIO will then dispatch the packet to the driverstation</li>
    //      * <li>The driverstation will route the packet to the interface for interpretation</li>
    //      * <li>Sends back a 200 code if accepted</li>
    //      * <li>Sends back a 4xx or 5xx code if failed</li>
    //      * </ul>
    //      * Sending data to the server is as follows:
    //      * <ul>
    //      * <li>Send the packet directly to the server via ethernet</li>
    //      * <li>The server will send a 200 code if accepted</li>
    //      * <li>The server will send a 4xx or 5xx code if failed</li>
    //      * </ul>
    //     */

    //     /**
    //      * Send data to the server or the interface
    //      * Interface protocol will route to RoboRIO via ethernet or other means
    //      * Server protocol will route directly to the server via ethernet
    //      * @param message The message object sent to the destination
    //      */
    //     public static void send(Message message) throws IOException {
    //         Targets target = message.target();
    //         DatagramSocket socket = new DatagramSocket();
            
    //         InetAddress address = InetAddress.getByName("192.168.0.0"); //TODO: Change this to the actual IP address
    //         int port = 8080; //TODO: Change this to the actual port
            
    //         byte[] data = convToBytes(message);
    //         DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            
    //         socket.send(packet);
    //         socket.close();
    //     }
        
    //     /**
    //      * Convert a message to a byte array, usable when converting to a Datagram Packet
    //      * @param message Message to serialize
    //      */
    //     private static byte[] convToBytes(Message message) {
    //         ByteArrayOutputStream boas = new ByteArrayOutputStream();
    //         try (ObjectOutputStream oos = new ObjectOutputStream(boas)) {
    //             oos.writeObject(message);
    //             return boas.toByteArray();
    //         } catch (IOException e) {
    //             throw new RuntimeException(e);
    //         }
    //     }
        
    //     /**
    //      * Initalizes the server connection to recieve the incoming messages
    //      * This will be opened on port 8080, no server context
    //      */
    //     public static void initServerConnection() {
    //         try (ServerSocket serverSocket = new ServerSocket(8080)) {
    //             System.out.println("Server started on port 8080!");
    //             while (true) {
    //                 try (Socket client = serverSocket.accept()) {
    //                     handleClient(client);
    //                 }
    //             }
    //         } catch (IOException e) {
    //             throw new RuntimeException(e);
    //         }
    //     }
        
    //     /**
    //      * Handles the incoming requests from server and Interpreter
    //      * Packages into the Request object
    //      * Returns a response to the initial sender
    //      * @param client The socket of the server
    //      */
    //     private static void handleClient(Socket client) {
    //         try {
    //             BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            
    //             StringBuilder requestBuilder = new StringBuilder();
    //             String line;
    //             while ((line = br.readLine()) != null && !line.isBlank()) {
    //                 requestBuilder.append(line + "\r\n");
    //             }
            
    //             String request = requestBuilder.toString();
    //             String[] requestsLines = request.split("\r\n");
    //             String[] requestLine = requestsLines[0].split(" ");
    //             String method = requestLine[0];
    //             String target = requestLine[1];
    //             String httpVersion = requestLine[2];

    //             System.out.println("Received data: " + method + " " + target + " " + httpVersion);

    //             if ("POST".equals(method)) {
    //                 int contentLength = 0;
    //                 for (String header : requestsLines) {
    //                     if (header.startsWith("Content-Length: ")) {
    //                         contentLength = Integer.parseInt(header.substring(16));
    //                         break;
    //                     }
    //                 }
    //                 StringBuilder jsonData = new StringBuilder();
    //                 for (int i = 0; i < contentLength; i++) {
    //                     jsonData.append((char) br.read());
    //                 }
    //                 System.out.println("JSON Data: " + jsonData.toString());
    //             }
                
            
    //             Request requestObject = new Request(method, target, httpVersion);
    //             //parseData(requestObject);
                
    //             /* TODO:
    //             * Ensure the received json is valid
    //             * If not send an error response back to the sender
    //             */
    //             Response response = new Response("RECEIVED", "The request was successfully received.");
    //             String json = "{\"name\":\"" + response.code() + "\",\"body\":" + response.body() + "\"}";
    //             OutputStream clientOutput = client.getOutputStream();
    //             clientOutput.write(("HTTP/1.1 200 OK\r\n").getBytes());
    //             clientOutput.write(("Content-Type: application/json\r\n").getBytes());
    //             clientOutput.write(("\r\n").getBytes());
    //             clientOutput.write(json.getBytes());
    //             clientOutput.write(("\r\n\r\n").getBytes());
    //             clientOutput.flush();
    //             client.close();
    //         } catch (IOException e) {
    //             throw new RuntimeException(e);
    //         }
    //     }
    // }

    /**
     * Request record to hold the requests send from varius sources
     * @param method Method that the request used to be sent
     * @param target Target URL
     * @param httpVersion Version of the HTTP protocol used
     * @param json String of the json object recived
     */
    // static record Request(String method, String target, String httpVersion, String json) implements Serializable {} TODO: Update java for this

    /**
     * Response to serialize and send to initial request sender
     * @param code Corresponding code relative to reception status
     * @param body More info about the reception (usually uniform). Primarily to display on interface, and log
     */
    // static record Response(String code, String body) implements Serializable {}
    
    /**
     * Message (or action / command) to send to the respective target.
     * @param target Targeted subsystem of the action
     * @param level Respective level of the action
     * @param title Title of the action (primarily for interface)
     * @param body Command or action to execute
     */
    // static record Message(Targets target, Levels level, String title, String body) implements Serializable {}

    /**
     * Action / command reception points
     */
    public enum Targets {
        /**
         * Sends a command or action to the interface
         */
        INTERFACE,
        /**
         * Sends a command or action to the AutoNav server
         */
        SERVER
    }
    
    /**
     * Levels of the request, correlating to process priority
     */
    public enum Levels {
        /**
         * Basic level, lowest process priority 
         */
        INFO,
        /**
         * Basic level, second lowest process priority
         */
        SUCCESS,
        /**
         * Intermediate level, second highest process priority
         */
        WARN,
        /**
         * Highest level, highest process priority; could mean AutoNav server or Interpreter shutdown
         */
        FATAL
    }

    /**
     *
     */
    public static class Lidar {

    }
    /**
     * Log handler class
     */
    public static class Logs {
        public static boolean uploadLogsOnExit = false;
        public static boolean deleteAllLogsOnExit = false;
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
         * WARNING: Can only be called once per program run
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
         *
         * @return Current log file id
         */
        public static Integer currentLog() {
            return id;
        }

        /**
         * Logs the specified String and coresponding LogLevel to current log file
         *
         * @param log   String log
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
         *
         * @param log   String log
         * @param level LogLevel enum
         * @param id    Integer id of log file
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
         *
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
                Logs.log(summary, LogLevel.WARN);
                // Interface.sendData(new String[]{"WARN", summary});
            } else {
                // Interface.sendData(new String[]{"INFO", summary});
                Logs.log(summary, LogLevel.INFO);
            }

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
                logBuffer.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return lines;
        }

        /**
         * Uploads the current log file to the current drive connected
         *
         * @throws IOException
         */
        public static void uploadLog() throws IOException {
            uploadFileToDrive(logFile.getAbsolutePath());
        }

        /**
         * Uploads the specified log file to the current drive connected
         *
         * @param id Integer id of log file
         */
        public static void uploadLog(int id) {
            try {
                File logFile = new File(logDir, id + ".txt");
                uploadFileToDrive(logFile.getAbsolutePath());
            } catch (Exception e) {
                log("Cannot find log", LogLevel.ERROR);
            }
        }

        public static void uploadLog(boolean all) throws IOException {
            if (all) {
                for (File file : Objects.requireNonNull(logDir.listFiles())) {
                    if (file.exists()) {
                        uploadFileToDrive(file.getAbsolutePath());
                    }
                }
            }
        }

        public static void uploadFileToDrive(String filePath) throws IOException {
            USBDeviceDetectorManager driveDetector = new USBDeviceDetectorManager();
            List<USBStorageDevice> drives = new ArrayList<>(driveDetector.getRemovableDevices());

            USBStorageDevice drive = drives.get(0);

            if (!drive.canWrite()) {
                RBTSystem.Logs.log("The selected drive is not writable.", LogLevel.ERROR);
                driveDetector.close();
                return;
            }

            Path sourcePath = Paths.get(filePath);

            Path targetPath = Paths.get(drive.getRootDirectory().getPath(), sourcePath.getFileName().toString());

            if (Files.exists(targetPath)) {
                RBTSystem.Logs.log("File already exists on drive. Please clear storage drive " + drive.getSystemDisplayName(), LogLevel.ERROR);
                driveDetector.close();
                return;
            }

            try {
                Files.copy(sourcePath, targetPath);
                Logs.log("Copied file to drive", LogLevel.INFO);
                driveDetector.close();
            } catch (IOException e) {
                e.printStackTrace();
                RBTSystem.Logs.log("Failed to copy file to drive", LogLevel.ERROR);
                driveDetector.close();
            }
        }

        public static void uploadLogsOnExit(boolean value) {
            uploadLogsOnExit = value;
        }

        /**
         * Sets to delete all log files on exit
         * WARNING: Highly suggested to upload all logs first
         * @param value Boolean value
         */
        public static void deleteLogs(boolean value) {
            deleteAllLogsOnExit = value;
        }
        /**
         * Deletes the specified log file
         * System function
         * @param all Integer id of log file
         */
        public static void deleteLogFile(boolean all) throws InterruptedException {
            if (all) {
                for (int i = currentLog(); i >= 0; i--) {
                    File logFile = new File(logDir, i + ".txt");
                    if (logFile.exists()) {
                        Logs.log("Deleting log file " + i, LogLevel.INFO);
                        logFile.delete();
                    }
                }
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
