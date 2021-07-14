package lv.cebbys.mcmods.celib.loggers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CelibLogger {
    private static final Logger LOGGER = LogManager.getLogger();

    public static void log( String name, String message ) {
        LOGGER.info( "({}): {}", name, message );
    }

    public static void warn( String name, String message ) {
        LOGGER.warn("({}): {}", name, message );
    }

    public static void error( String name, String message ) {
        LOGGER.error("({}): {}", name, message );
    }
}
