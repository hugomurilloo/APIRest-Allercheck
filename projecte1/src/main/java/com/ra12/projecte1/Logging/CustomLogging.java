package com.ra12.projecte1.Logging;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Component
public class CustomLogging {
    
    // Ruta del archivo de log
    private static final String LOG_FILE = "Logging/application.log";
    
    // Formato de fecha para los logs
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void logError(String className, String methodName, String errorMsg, Exception exception) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = String.format("[ERROR] %s - Class: %s - Method: %s - Message: %s", 
                                       timestamp, className, methodName, errorMsg);
        
        if (exception != null) {
            logEntry += " - Exception: " + exception.getMessage();
        }
        
        writeToFile(logEntry);
        System.out.println(logEntry);
    }
    

    public void logInfo(String className, String methodName, String infoMsg) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = String.format("[INFO] %s - Class: %s - Method: %s - Message: %s", 
                                       timestamp, className, methodName, infoMsg);
        
        writeToFile(logEntry);
        System.out.println(logEntry);
    }

    private void writeToFile(String message) {
        Path logPath = Paths.get(LOG_FILE);
        
        try {
            Files.createDirectories(logPath.getParent());
            
            try (BufferedWriter bw = Files.newBufferedWriter(logPath, 
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
                bw.write(message);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("ERROR escribiendo al archivo de log: " + e.getMessage());
        }
    }
}