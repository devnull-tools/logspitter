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

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * A webservice that exposes an interface for generating log entries.
 *
 * @author Marcelo Guimaraes
 */
@Path("/")
public class LogSpitterService {

  private final LogSpitter spitter = new LogSpitterImpl(new Log4JLogForwarder(), new JavassistExceptionCreator());

  /**
   * Generates a log entry based on the given information.
   *
   * @param entry the entry to log
   */
  @POST
  @Path("/log")
  @Consumes("application/json")
  public Response spit(LogEntry entry) {
    if (empty(entry.getLevel())) {
      return Response.status(Response.Status.BAD_REQUEST).entity("Level not supplied").build();
    }
    if (empty(entry.getMessage())) {
      return Response.status(Response.Status.BAD_REQUEST).entity("Message not supplied").build();
    }
    if (empty(entry.getCategory())) {
      entry.setCategory(this.getClass().getPackage().getName());
    }
    entry.log(spitter);
    return Response.ok().build();
  }

  private boolean empty(String arg) {
    return arg == null || arg.trim().isEmpty();
  }

}
