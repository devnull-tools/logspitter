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

public interface LogSpitter {

  /**
   * Logs the given message using the FATAL level and the default category.
   *
   * @param message
   *          the message to log
   */
  void fatal(String message);

  /**
   * Logs the given message using the FATAL level and the given category.
   *
   * @param category
   *          the category to use
   * @param message
   *          the message to log
   */
  void fatal(String category, String message);

  /**
   * Logs the given message using the FATAL level, the given category and the
   * given exception. If the exception does not exist in
   * classpath, it will be created at runtime (if possible).
   *
   * @param category
   *          the category to use
   * @param message
   *          the message to log
   * @param exceptionClass
   *          the exception to log
   */
  void fatal(String category, String message, String exceptionClass)
      throws Exception;

  /**
   * Logs the given message using the ERROR level and the default category.
   *
   * @param message
   *          the message to log
   */
  void error(String message);

  /**
   * Logs the given message using the ERROR level and the given category.
   *
   * @param category
   *          the category to use
   * @param message
   *          the message to log
   */
  void error(String category, String message);

  /**
   * Logs the given message using the ERROR level, the given category and the
   * given exception. If the exception does not exist in
   * classpath, it will be created at runtime (if possible).
   *
   * @param category
   *          the category to use
   * @param message
   *          the message to log
   * @param exceptionClass
   *          the exception to log
   */
  void error(String category, String message, String exceptionClass)
      throws Exception;

  /**
   * Logs the given message using the WARN level and the default category.
   *
   * @param message
   *          the message to log
   */
  void warn(String message);

  /**
   * Logs the given message using the WARN level and the given category.
   *
   * @param category
   *          the category to use
   * @param message
   *          the message to log
   */
  void warn(String category, String message);

  /**
   * Logs the given message using the WARN level, the given category and the
   * given exception. If the exception does not exist in
   * classpath, it will be created at runtime (if possible).
   *
   * @param category
   *          the category to use
   * @param message
   *          the message to log
   * @param exceptionClass
   *          the exception to log
   */
  void warn(String category, String message, String exceptionClass)
      throws Exception;

  /**
   * Logs the given message using the INFO level and the default category.
   *
   * @param message
   *          the message to log
   */
  void info(String message);

  /**
   * Logs the given message using the INFO level and the given category.
   *
   * @param category
   *          the category to use
   * @param message
   *          the message to log
   */
  void info(String category, String message);

  /**
   * Logs the given message using the INFO level, the given category and the
   * given exception. If the exception does not exist in
   * classpath, it will be created at runtime (if possible).
   *
   * @param category
   *          the category to use
   * @param message
   *          the message to log
   * @param exceptionClass
   *          the exception to log
   */
  void info(String category, String message, String exceptionClass)
      throws Exception;

  /**
   * Logs the given message using the DEBUG level and the default category.
   *
   * @param message
   *          the message to log
   */
  void debug(String message);

  /**
   * Logs the given message using the DEBUG level and the given category.
   *
   * @param category
   *          the category to use
   * @param message
   *          the message to log
   */
  void debug(String category, String message);

  /**
   * Logs the given message using the DEBUG level, the given category and the
   * given exception. If the exception does not exist in
   * classpath, it will be created at runtime (if possible).
   *
   * @param category
   *          the category to use
   * @param message
   *          the message to log
   * @param exceptionClass
   *          the exception to log
   */
  void debug(String category, String message, String exceptionClass)
      throws Exception;

  /**
   * Logs the given message using the TRACE level and the default category.
   *
   * @param message
   *          the message to log
   */
  void trace(String message);

  /**
   * Logs the given message using the TRACE level and the given category.
   *
   * @param category
   *          the category to use
   * @param message
   *          the message to log
   */
  void trace(String category, String message);

  /**
   * Logs the given message using the TRACE level, the given category and the
   * given exception. If the exception does not exist in
   * classpath, it will be created at runtime (if possible).
   *
   * @param category
   *          the category to use
   * @param message
   *          the message to log
   * @param exceptionClass
   *          the exception to log
   */
  void trace(String category, String message, String exceptionClass)
      throws Exception;

}
