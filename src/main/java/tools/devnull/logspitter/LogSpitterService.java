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

import tools.devnull.logspitter.impl.LogSpitterImpl;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * A webservice that exposes an interface for generating log entries.
 *
 * @author Marcelo Guimaraes
 */
@WebService
public class LogSpitterService {

  private final LogSpitter spitter = new LogSpitterImpl();

  private final boolean empty(String arg) {
    return arg == null || arg.trim().isEmpty();
  }

  /**
   * Generates a log entry based on the given informations.
   *
   * @param level          the level of the message
   * @param message        the message
   * @param category       the category of the message (optional)
   * @param exceptionClass the exception to throw (optional)
   */
  @Oneway
  @WebMethod
  public void spit(
      @WebParam(name = "level") String level,
      @WebParam(name = "message") String message,
      @WebParam(name = "category") String category,
      @WebParam(name = "exceptionClass") String exceptionClass) throws Exception {
    if (empty(exceptionClass)) {
      if (empty(category)) {
        spitter.spit(level)
            .message(message)
            .raw();
      } else {
        spitter.spit(level)
            .message(message)
            .ofCategory(category)
            .raw();
      }
    } else {
      if (empty(category)) {
        spitter.spit(level)
            .message(message)
            .thrownBy(exceptionClass);
      } else {
        spitter.spit(level)
            .message(message)
            .ofCategory(category)
            .thrownBy(exceptionClass);
      }
    }
  }

}
