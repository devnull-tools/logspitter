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

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtConstructor;
import tools.devnull.logspitter.ExceptionCreator;

import java.lang.reflect.Constructor;

public class ExceptionCreatorImpl implements ExceptionCreator {

  @Override
  public Throwable create(String exceptionClass) {
    try {
      return create(exceptionClass, null);
    } catch (Throwable throwable) {
      return throwable;
    }
  }

  @Override
  public Throwable create(String exceptionClass, String message) {
    try {
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
      return exception;
    } catch (Throwable throwable) {
      return throwable;
    }
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
