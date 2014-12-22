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

package tools.devnull.logspitter;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;

/**
 * Enumeration of the possible log levels.
 *
 * @author Marcelo Guimaraes <ataxexe@gmail.com>
 */
public enum LogLevel {

  TRACE {
    @Override
    public void log(Logger logger, Object message) {
      logger.trace(message);
    }

    @Override
    public void log(Logger logger, Object message, Throwable cause) {
      logger.trace(message, cause);
    }
  },
  DEBUG {
    @Override
    public void log(Logger logger, Object message) {
      logger.debug(message);
    }

    @Override
    public void log(Logger logger, Object message, Throwable cause) {
      logger.debug(message, cause);
    }
  },
  INFO {
    @Override
    public void log(Logger logger, Object message) {
      logger.info(message);
    }

    @Override
    public void log(Logger logger, Object message, Throwable cause) {
      logger.info(message, cause);
    }
  },
  WARN {
    @Override
    public void log(Logger logger, Object message) {
      logger.warn(message);
    }

    @Override
    public void log(Logger logger, Object message, Throwable cause) {
      logger.warn(message, cause);
    }
  },
  ERROR {
    @Override
    public void log(Logger logger, Object message) {
      logger.error(message);
    }

    @Override
    public void log(Logger logger, Object message, Throwable cause) {
      logger.error(message, cause);
    }
  },
  FATAL {
    @Override
    public void log(Logger logger, Object message) {
      logger.fatal(message);
    }

    @Override
    public void log(Logger logger, Object message, Throwable cause) {
      logger.fatal(message, cause);
    }
  };

  /**
   * Logs the given message using the log level.
   *
   * @param logger
   *          logger object to log the message
   * @param message
   *          the message to log
   */
  public abstract void log(Logger logger, Object message);

  /**
   * Logs the given message using the log level.
   *
   * @param logger
   *          logger object to log the message
   * @param message
   *          the message to log
   * @param cause
   *          the exception to log
   */
  public abstract void log(Logger logger, Object message, Throwable cause);

}
