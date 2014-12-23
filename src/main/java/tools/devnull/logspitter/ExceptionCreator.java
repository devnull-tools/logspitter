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

/**
 * Interface that defines a class that can create exceptions
 * based on a given class name.
 * <p/>
 * This is used by LogSpitter to create classes at runtime to
 * pass to the log engine and eliminate the need of the exception
 * classes to be in LogSpitter classpath.
 *
 * @author Marcelo Guimaraes
 */
public interface ExceptionCreator {

  /**
   * Creates an exception of the given class and message.
   *
   * @param exceptionClass the exception class
   * @param message        the message that this exception holds
   * @return the created exception.
   */
  Throwable create(String exceptionClass, String message);

  /**
   * Creates an exception of the given class.
   *
   * @param exceptionClass the exception class
   * @return the created exception.
   */
  Throwable create(String exceptionClass);

}
