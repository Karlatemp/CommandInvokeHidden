/*
 * Copyright (c) 2018-2020 Karlatemp. All rights reserved.
 * @author Karlatemp <karlatemp@vip.qq.com> <https://github.com/Karlatemp>
 * @create 2020/03/27 23:07:23
 *
 * CommandInvokeHidden/CommandInvokeHidden/PlayerInvokeCommandLoggingEvent.java
 */

package cn.mcres.karlatemp.cih;

import org.bukkit.Bukkit;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerInvokeCommandLoggingEvent extends Event implements Cancellable {
    public static final HandlerList handlers = new HandlerList();
    private final String command;
    private boolean c = false;

    public PlayerInvokeCommandLoggingEvent(String command) {
        super(!Bukkit.isPrimaryThread());
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return c;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.c = cancel;
    }
}
