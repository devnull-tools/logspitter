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

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class LogSpitterService {

  private final LogSpitter spitter = new LogSpitterImpl();

  private final boolean empty(String arg) {
    return arg == null || arg.trim().isEmpty();
  }

  public void fatal(@WebParam(name = "category") String category, @WebParam(name = "message") String message,
                    @WebParam(name = "exceptionClass") String exceptionClass) throws Exception {
    if (empty(exceptionClass)) {
      if (empty(category)) {
        spitter.fatal(message);
      } else {
        spitter.fatal(category, message);
      }
    } else {
      spitter.fatal(message, category, exceptionClass);
    }
  }

  public void error(@WebParam(name = "category") String category, @WebParam(name = "message") String message,
                    @WebParam(name = "exceptionClass") String exceptionClass) throws Exception {
    if (empty(exceptionClass)) {
      if (empty(category)) {
        spitter.error(message);
      } else {
        spitter.error(category, message);
      }
    } else {
      spitter.error(message, category, exceptionClass);
    }
  }

  public void warn(@WebParam(name = "category") String category, @WebParam(name = "message") String message,
                   @WebParam(name = "exceptionClass") String exceptionClass) throws Exception {
    if (empty(exceptionClass)) {
      if (empty(category)) {
        spitter.warn(message);
      } else {
        spitter.warn(category, message);
      }
    } else {
      spitter.warn(message, category, exceptionClass);
    }
  }

  public void info(@WebParam(name = "category") String category, @WebParam(name = "message") String message,
                   @WebParam(name = "exceptionClass") String exceptionClass) throws Exception {
    if (empty(exceptionClass)) {
      if (empty(category)) {
        spitter.info(message);
      } else {
        spitter.info(category, message);
      }
    } else {
      spitter.info(message, category, exceptionClass);
    }
  }

  public void debug(@WebParam(name = "category") String category, @WebParam(name = "message") String message,
                    @WebParam(name = "exceptionClass") String exceptionClass) throws Exception {
    if (empty(exceptionClass)) {
      if (empty(category)) {
        spitter.debug(message);
      } else {
        spitter.debug(category, message);
      }
    } else {
      spitter.debug(message, category, exceptionClass);
    }
  }

  public void trace(@WebParam(name = "category") String category, @WebParam(name = "message") String message,
                    @WebParam(name = "exceptionClass") String exceptionClass) throws Exception {
    if (empty(exceptionClass)) {
      if (empty(category)) {
        spitter.trace(message);
      } else {
        spitter.trace(category, message);
      }
    } else {
      spitter.trace(message, category, exceptionClass);
    }
  }

}
