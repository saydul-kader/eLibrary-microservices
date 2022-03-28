package com.eLibrary.order.configuration;

import com.google.common.flogger.FluentLogger;

import java.util.logging.Level;

public class LoggerConfig {
    private static FluentLogger logger;

    private LoggerConfig(){
        logger = FluentLogger.forEnclosingClass();
    }

    public static void log(final Level level, final String message) {
        logger.at(level).log(message);
    }

    public static void log(final Exception e) {
        logger.atSevere().withCause(e.getCause()).log(e.getMessage());
    }

    public static void error(String message, Exception e) {
        logger.atSevere().withCause(e.getCause()).log(message);
    }
}
