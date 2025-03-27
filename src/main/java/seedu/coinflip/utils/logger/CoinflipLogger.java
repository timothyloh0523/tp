package seedu.coinflip.utils.logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Centralizes logging functionality for the Coinflip application.
 * Provides methods for logging at different levels and handles the setup
 * of the logger.
 */
public class CoinflipLogger {
    private static final String LOG_FILE_PATH = "./data/coinflip.log";
    private static Logger logger;
    private static FileHandler fileHandler;
    private static boolean isInitialized = false;

    /**
     * Initializes the logger with appropriate handlers and formatters.
     * Creates the necessary directories and log file if they don't exist.
     *
     * @return true if initialization is successful, false otherwise
     */
    public static boolean init() {
        if (isInitialized) {
            return true;
        }

        try {
            // Create logger
            logger = Logger.getLogger("seedu.coinflip");
            logger.setUseParentHandlers(false);

            // Create directories and log file if they don't exist
            if (!Files.exists(Paths.get(LOG_FILE_PATH))) {
                Files.createDirectories(Paths.get("./data"));
                Files.createFile(Paths.get(LOG_FILE_PATH));
            }

            // Set up file handler and formatter
            fileHandler = new FileHandler(LOG_FILE_PATH, true);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            logger.addHandler(fileHandler);

            isInitialized = true;
            info("Logger initialized successfully");
            return true;
        } catch (IOException | SecurityException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Logs a message at INFO level.
     *
     * @param message the message to log
     */
    public static void info(String message) {
        if (ensureInitialized()) {
            logger.log(Level.INFO, message);
        }
    }

    /**
     * Logs a message at WARNING level.
     *
     * @param message the message to log
     */
    public static void warning(String message) {
        if (ensureInitialized()) {
            logger.log(Level.WARNING, message);
        }
    }

    /**
     * Logs a message at SEVERE level.
     *
     * @param message the message to log
     */
    public static void severe(String message) {
        if (ensureInitialized()) {
            logger.log(Level.SEVERE, message);
        }
    }

    /**
     * Logs a message at FINE level (for detailed information).
     *
     * @param message the message to log
     */
    public static void fine(String message) {
        if (ensureInitialized()) {
            logger.log(Level.FINE, message);
        }
    }

    /**
     * Logs an exception with a custom message at SEVERE level.
     *
     * @param message the custom message
     * @param e       the exception to log
     */
    public static void exception(String message, Exception e) {
        if (ensureInitialized()) {
            logger.log(Level.SEVERE, message, e);
        }
    }

    /**
     * Ensures the logger is initialized before attempting to use it.
     *
     * @return true if the logger is initialized successfully, false otherwise
     */
    private static boolean ensureInitialized() {
        if (!isInitialized) {
            return init();
        }
        return true;
    }

    /**
     * Closes the file handler to ensure all logs are properly written.
     * Should be called when the application is shutting down.
     */
    public static void close() {
        if (fileHandler != null) {
            fileHandler.close();
        }
    }
}
