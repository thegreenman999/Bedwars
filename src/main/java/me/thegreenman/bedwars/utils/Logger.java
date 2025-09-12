package me.thegreenman.bedwars.utils;

import java.util.logging.Level;

public class Logger {

    private static String pluinName = "";

    public Logger(String pluginName) {
        pluinName = "[" + pluginName + "] ";
    }

    public static void log(String msg) {
        java.util.logging.Logger.getLogger("Minecraft").log(Level.INFO, pluinName + msg);
    }

    public static void log(String msg, Level level) {
        java.util.logging.Logger.getLogger("Minecraft").log(level, pluinName + msg);
    }

    public static void log(String msg, String name) {
        msg = "[" + name + "] " + msg;
        java.util.logging.Logger.getLogger("Minecraft").log(Level.INFO, msg);
    }
}