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

import org.junit.Before;
import org.junit.Test;
import tools.devnull.logspitter.impl.LogSpitterImpl;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

/**
 * A class for testing the basic functions of logspitter API
 *
 * @author Marcelo Guimaraes
 */
public class LogSpitterTest {

  private ExceptionCreator exceptionCreator;
  private LogForwarder logForwarder;
  private LogSpitter logSpitter;

  private String errorMessage = "Some error message";

  private Exception exceptionWithoutMessage = new Exception();
  private Exception exceptionWithMessage = new Exception(errorMessage);

  private String info = "INFO";
  private String error = "ERROR";

  private String logMessage = "Some message";
  private String defaultCategory = "tools.devnull.logspitter";
  private String someException = "does not matter";

  @Before
  public void initialize() {
    exceptionCreator = mock(ExceptionCreator.class);
    logForwarder = mock(LogForwarder.class);

    when(exceptionCreator.create(anyString())).thenReturn(exceptionWithoutMessage);
    when(exceptionCreator.create(anyString(), anyString())).thenReturn(exceptionWithMessage);

    logSpitter = new LogSpitterImpl(logForwarder, exceptionCreator);
  }

  @Test
  public void testMessageWithoutCategory() {
    logSpitter.spit(info).message(logMessage).plain();

    verify(logForwarder, times(1)).forward(info, defaultCategory, logMessage);
  }

  @Test
  public void testMessageWithCategory() {
    String myCategory = "mycategory";
    logSpitter.spit(info).message(logMessage).ofCategory(myCategory).plain();

    verify(logForwarder, times(1)).forward(info, myCategory, logMessage);
  }

  @Test
  public void testExceptionWithoutCategory() {
    logSpitter.spit(error).message(logMessage).thrownBy(someException);

    verify(logForwarder, times(1)).forward(eq(error), eq(defaultCategory), eq(logMessage), any(Throwable.class));
    verify(exceptionCreator, times(1)).create(someException, logMessage);
  }

  @Test
  public void testExceptionWithCategory() {
    String myCategory = "mycategory";
    logSpitter.spit(error).message(logMessage).ofCategory(myCategory).thrownBy(someException);

    verify(logForwarder, times(1)).forward(eq(error), eq(myCategory), eq(logMessage), any(Throwable.class));
    verify(exceptionCreator, times(1)).create(someException, logMessage);
  }

  @Test
  public void testExceptionWithoutMessage() {
    logSpitter.spit(error).thrownBy(someException);

    verify(logForwarder, times(1)).forward(eq(error), eq(defaultCategory), eq(""), any(Throwable.class));
    verify(exceptionCreator, times(1)).create(someException);
  }

}
