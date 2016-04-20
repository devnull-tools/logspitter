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

import tools.devnull.logspitter.impl.JavassistExceptionCreator;
import tools.devnull.logspitter.impl.Log4JLogForwarder;
import tools.devnull.logspitter.impl.LogSpitterImpl;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * A webservice that exposes an interface for generating log entries.
 *
 * @author Marcelo Guimaraes
 */
@Path("/")
public class LogSpitterService {

  private final LogSpitter spitter = new LogSpitterImpl(
      new Log4JLogForwarder(), new JavassistExceptionCreator()
  );

  private boolean empty(String arg) {
    return arg == null || arg.trim().isEmpty();
  }

  /**
   * Generates a log entry based on the given information.
   *
   * @param level          the level of the message
   * @param message        the message
   * @param category       the category of the message
   * @param exceptionClass the exception to throw (optional)
   */
  @POST
  @Path("/{level}/{category}")
  public Response spit(
      @PathParam("level") String level,
      @PathParam("category") String category,
      @QueryParam("message") String message,
      @QueryParam("exceptionClass") String exceptionClass) {
    if (empty(exceptionClass)) {
      if (empty(category)) {
        spitter.spit(level)
            .message(message)
            .plain();
      } else {
        spitter.spit(level)
            .message(message)
            .ofCategory(category)
            .plain();
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
    return Response.ok().build();
  }

  /**
   * Generates a log entry based on the given information.
   *
   * @param level          the level of the message
   * @param message        the message
   * @param exceptionClass the exception to throw (optional)
   */
  @POST
  @Path("/{level}")
  public Response spit(
      @PathParam("level") String level,
      @QueryParam("message") String message,
      @QueryParam("exceptionClass") String exceptionClass) {
    if (empty(exceptionClass)) {
      spitter.spit(level)
          .message(message)
          .plain();
    } else {
      spitter.spit(level)
          .message(message)
          .thrownBy(exceptionClass);
    }
    return Response.ok().build();
  }

}
