/*
 * Copyright (c) 2018-2020 Karlatemp. All rights reserved.
 * @author Karlatemp <karlatemp@vip.qq.com> <https://github.com/Karlatemp>
 * @create 2020/03/27 23:07:23
 *
 * CommandInvokeHidden/CommandInvokeHidden/Log4JMessageFilter.java
 */

package cn.mcres.karlatemp.cih;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.message.Message;

public class Log4JMessageFilter extends AbstractFilter {
    @Override
    public Result filter(LogEvent logEvent) {
        return filter(logEvent.getMessage().getFormattedMessage());
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, String s, Object... objects) {
        return filter(s);
    }

    public Result filter(String s) {
        return CommandInvokeHidden.check(s) ? Result.DENY : Result.NEUTRAL;
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Object o, Throwable throwable) {
        return filter(String.valueOf(o));
    }

    @Override
    public Result filter(Logger logger, Level level, Marker marker, Message message, Throwable throwable) {
        return filter(message.getFormattedMessage());
    }
}
