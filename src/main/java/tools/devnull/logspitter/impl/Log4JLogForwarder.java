/*
 * The MIT License
 *
 * Copyright (c) 2014 Marcelo Guimaraes <ataxexe@gmail.com>
 *
 * Permission  is hereby granted, free of charge, to any person obtaining
 * a  copy  of  this  software  and  associated  documentation files (the
 * "Software"),  to  deal  in the Software without restriction, including
 * without  limitation  the  rights to use, copy, modify, merge, publish,
 * distribute,  sublicense,  and/or  sell  copies of the Software, and to
 * permit  persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The  above  copyright  notice  and  this  permission  notice  shall be
 * included  in  all  copies  or  substantial  portions  of the Software.
 *
 * THE  SOFTWARE  IS  PROVIDED  "AS  IS",  WITHOUT  WARRANTY OF ANY KIND,
 * EXPRESS  OR  IMPLIED,  INCLUDING  BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN  NO  EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM,  DAMAGES  OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT  OR  OTHERWISE,  ARISING  FROM,  OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE   OR   THE   USE   OR   OTHER   DEALINGS  IN  THE  SOFTWARE.
 */

package tools.devnull.logspitter.impl;

import org.apache.log4j.Appender;
import org.apache.log4j.Layout;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.Filter;
import org.apache.log4j.spi.LoggingEvent;
import tools.devnull.logspitter.LogForwarder;

/**
 * A class that forwards log entries using Apache Log4J.
 *
 * @author Marcelo Guimaraes
 */
public class Log4JLogForwarder implements LogForwarder {

  {
    Logger.getRootLogger().addAppender(new Appender() {
      @Override
      public void addFilter(Filter newFilter) {

      }

      @Override
      public Filter getFilter() {
        return null;
      }

      @Override
      public void clearFilters() {

      }

      @Override
      public void close() {

      }

      @Override
      public void doAppend(LoggingEvent event) {

      }

      @Override
      public String getName() {
        return null;
      }

      @Override
      public void setErrorHandler(ErrorHandler errorHandler) {

      }

      @Override
      public ErrorHandler getErrorHandler() {
        return null;
      }

      @Override
      public void setLayout(Layout layout) {

      }

      @Override
      public Layout getLayout() {
        return null;
      }

      @Override
      public void setName(String name) {

      }

      @Override
      public boolean requiresLayout() {
        return false;
      }
    });
  }

  @Override
  public void forward(String level, String category, String message, Throwable exception) {
    Logger.getLogger(category).log(Level.toLevel(level, Level.INFO), message, exception);
  }

  @Override
  public void forward(String level, String category, String message) {
    Logger.getLogger(category).log(Level.toLevel(level, Level.INFO), message);
  }

}
