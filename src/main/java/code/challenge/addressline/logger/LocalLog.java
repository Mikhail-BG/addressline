package code.challenge.addressline.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Application logger.
 */
public final class LocalLog
{
    private static final Logger LOG = LogManager.getLogger();

    private LocalLog()
    {
    }

    /**
     * Log INFO message.
     */
    public static void info(String message)
    {
        LOG.info(message);
    }

    /**
     * Log WARN message.
     */
    public static void warn(String message)
    {
        LOG.warn(message);
    }

    /**
     * Log ERROR message.
     */
    public static void error(String message)
    {
        LOG.error(message);
    }

    /**
     * Log FATAL message.
     */
    public static void fatal(String message)
    {
        LOG.fatal(message);
    }

    /**
     * Log DEBUG message.
     */
    public static void debug(String message)
    {
        LOG.debug(message);
    }
}
