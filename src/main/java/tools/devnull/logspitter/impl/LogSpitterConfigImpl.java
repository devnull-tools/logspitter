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

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import tools.devnull.logspitter.ExceptionCreator;
import tools.devnull.logspitter.LogSpitter;
import tools.devnull.logspitter.LogSpitterConfig;
import tools.devnull.logspitter.SpitterMessageConfig;

public class LogSpitterConfigImpl implements LogSpitterConfig {

  private final Level level;
  private final ExceptionCreator exceptionCreator;

  public LogSpitterConfigImpl(ExceptionCreator exceptionCreator, Level level) {
    this.level = level;
    this.exceptionCreator = exceptionCreator;
  }

  @Override
  public SpitterMessageConfig message(String message) {
    return new SpitterMessageConfigImpl(exceptionCreator, level, message);
  }

  @Override
  public void exception(String exceptionClass) {
    Logger.getLogger(LogSpitter.class.getPackage().getName()).log(level, exceptionCreator.create(exceptionClass));
  }

}