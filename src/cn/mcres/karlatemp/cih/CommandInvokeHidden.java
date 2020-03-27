/*
 * Copyright (c) 2018-2020 Karlatemp. All rights reserved.
 * @author Karlatemp <karlatemp@vip.qq.com> <https://github.com/Karlatemp>
 * @create 2020/03/27 23:07:23
 *
 * CommandInvokeHidden/CommandInvokeHidden/CommandInvokeHidden.java
 */

package cn.mcres.karlatemp.cih;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Logger;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.logging.Level;

public class CommandInvokeHidden extends JavaPlugin {
    public static final Log4JMessageFilter filter = new Log4JMessageFilter();
    public static final String splitter = " issued server command: ";

    public static boolean check(String s) {
        int split = s.indexOf(splitter);
        if (split == -1)
            return false;
        String command = s.substring(split + splitter.length());
        PlayerInvokeCommandLoggingEvent invoke = new PlayerInvokeCommandLoggingEvent(command);
        Bukkit.getPluginManager().callEvent(invoke);
        return invoke.isCancelled();
    }

    @Override
    public void onDisable() {
        final Iterator<Filter> filters = getPCLogger().getFilters();
        while (filters.hasNext()) {
            try {
                if (filters.next() == filter) filters.remove();
            } catch (Throwable exce) {
                getLogger().log(Level.SEVERE, "Failed to remove filter!", exce);
            }
        }
    }

    @Override
    public void onEnable() {
        getPCLogger().addFilter(filter);
    }

    public static Logger getPCLogger() {
        try {
            // org.bukkit.craftbukkit.VER.CraftServer
            final String name = Bukkit.getServer().getClass().getName();
            final String[] split = name.split("\\.");
            String ver = split[3];
            final Class<?> connection = Class.forName("net.minecraft.server." + ver + ".PlayerConnection");
            Field LOGGER = null;
            for (Field f : connection.getDeclaredFields()) {
                if (f.getType() == org.apache.logging.log4j.Logger.class) {
                    LOGGER = f;
                    break;
                }
            }
            assert LOGGER != null : "Failed to get logger field from player connection.";
            LOGGER.setAccessible(true);
            return (Logger) LOGGER.get(null);
        } catch (Throwable ignore) {
        }
        return null;
    }
}
