
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

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import org.apache.log4j.Logger;

import java.lang.reflect.Constructor;

public class LogSpitterImpl implements LogSpitter {

  /**
   * The default category, used if no one is specified.
   */
  private static final String DEFAULT_CATEGORY = LogSpitter.class.getPackage().getName();

  public void fatal(String message) {
    fatal(DEFAULT_CATEGORY, message);
  }

  public void fatal(String category, String message) {
    log(category, LogLevel.FATAL, message);
  }

  public void fatal(String category, String message, String exceptionClass)
      throws Exception {
    log(category, LogLevel.FATAL, message, exceptionClass);
  }

  public void error(String message) {
    error(DEFAULT_CATEGORY, message);
  }

  public void error(String category, String message) {
    log(category, LogLevel.ERROR, message);
  }

  public void error(String category, String message, String exceptionClass)
      throws Exception {
    log(category, LogLevel.ERROR, message, exceptionClass);
  }

  public void warn(String message) {
    warn(DEFAULT_CATEGORY, message);
  }

  public void warn(String category, String message) {
    log(category, LogLevel.WARN, message);
  }

  public void warn(String category, String message, String exceptionClass)
      throws Exception {
    log(category, LogLevel.WARN, message, exceptionClass);
  }

  public void info(String message) {
    info(DEFAULT_CATEGORY, message);
  }

  public void info(String category, String message) {
    log(category, LogLevel.INFO, message);
  }

  public void info(String category, String message, String exceptionClass)
      throws Exception {
    log(category, LogLevel.INFO, message, exceptionClass);
  }

  public void debug(String message) {
    debug(DEFAULT_CATEGORY, message);
  }

  public void debug(String category, String message) {
    log(category, LogLevel.DEBUG, message);
  }

  public void debug(String category, String message, String exceptionClass)
      throws Exception {
    log(category, LogLevel.DEBUG, message, exceptionClass);
  }

  public void trace(String message) {
    trace(DEFAULT_CATEGORY, message);
  }

  public void trace(String category, String message) {
    log(category, LogLevel.TRACE, message);
  }

  public void trace(String category, String message, String exceptionClass)
      throws Exception {
    log(category, LogLevel.TRACE, message, exceptionClass);
  }

  /**
   * Logs the given message using the category and level.
   *
   * @param category
   *          the category to use
   * @param level
   *          the log level
   * @param message
   *          the message to log
   */
  private void log(String category, LogLevel level, String message) {
    Logger logger = Logger.getLogger(category);
    level.log(logger, message);
  }

  /**
   * Logs the given message using the category, level and an exception of the
   * given class name. If the class does not exist in classpath, it will be
   * created (if possible) at runtime.
   *
   * @param category
   *          the category to use
   * @param level
   *          the log level
   * @param message
   *          the message to log
   * @param exceptionClass
   *          the exception to log
   */
  private void log(String category, LogLevel level, String message,
                   String exceptionClass) throws Exception {
    Logger logger = Logger.getLogger(category);
    Throwable exception;
    try {
      Class<?> c = Class.forName(exceptionClass);
      // tries to create the exception using the message in constructor
      exception = createException(message, c);
    } catch (ClassNotFoundException e) {
      // tries to create the exception at runtime
      ClassPool classPool = ClassPool.getDefault();
      CtClass superClass = classPool.getCtClass(Exception.class
          .getCanonicalName());
      CtClass dynaClass = classPool.makeClass(exceptionClass, superClass);
      for (CtConstructor c : superClass.getConstructors()) {
        dynaClass.addConstructor(new CtConstructor(c, dynaClass, null));
      }
      exception = createException(message, dynaClass.toClass());
      dynaClass.detach();
    }
    level.log(logger, message, exception);
  }

  private Throwable createException(String message, Class<?> c)
      throws InstantiationException, IllegalAccessException {
    Throwable exception;
    try {
      Constructor<?> constructor = c.getDeclaredConstructor(String.class);
      exception = (Throwable) constructor.newInstance(message);
    } catch (Exception e) {
      // use the default constructor
      exception = (Throwable) c.newInstance();
    }
    return exception;
  }

}
