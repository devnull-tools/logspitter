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

import org.junit.Test;
import tools.devnull.kodo.TestScenario;
import tools.devnull.kodo.function.BaseFunction;
import tools.devnull.kodo.function.BasePredicate;
import tools.devnull.kodo.function.Function;
import tools.devnull.kodo.function.Predicate;
import tools.devnull.logspitter.impl.JavassistExceptionCreator;

import static tools.devnull.kodo.Spec.*;

/**
 * A class that tests the basic behaviour of the {@link JavassistExceptionCreator}.
 *
 * @author Marcelo Guimaraes
 */
public class JavassistExceptionCreatorTest {

  private String classNotInClasspath = "tools.devnull.logspitter.exception.MockException";

  @Test
  public void shouldInstantiateClasspathClass() {
    TestScenario.given(new JavassistExceptionCreator())
        .the(newExceptionOf(NullPointerException.class.getName()),
            should(be(NOT_NULL).and(aClassOf(NullPointerException.class))),
            because("If a class is on classpath, it should be instantiated"));
  }

  @Test
  public void shouldCreateClass() {
    TestScenario.given(new JavassistExceptionCreator())
        .the(newExceptionOf(classNotInClasspath),
            should(be(NOT_NULL).and(aClassOf(classNotInClasspath))),
            because("If a class is not on classpath, it should be created"));
  }

  @Test
  public void shouldCreateExceptionWithoutMessage() {
    ExceptionCreator exceptionCreator = new JavassistExceptionCreator();
    Throwable exception = exceptionCreator.create(NullPointerException.class.getName());
    TestScenario.given(exception)
        .the(message(), should(be(NULL)),
            because("Exceptions created without a message should not have a message returned by #getMessage()"));
  }

  @Test
  public void shouldCreateExceptionWithGivenMessage() {
    ExceptionCreator exceptionCreator = new JavassistExceptionCreator();
    String errorMessage = "Some error message";
    Throwable exception = exceptionCreator.create(NullPointerException.class.getName(), errorMessage);
    TestScenario.given(exception)
        .the(message(), should(equal(errorMessage)),
            because("Exceptions created with a message should have this message as #getMessage() result"));
  }

  private Function<Throwable, String> message() {
    return new BaseFunction<Throwable, String>() {
      @Override
      public String apply(Throwable throwable) {
        return throwable.getMessage();
      }
    };
  }

  private Predicate<Object> aClassOf(final Class type) {
    return new BasePredicate<Object>() {
      @Override
      public boolean test(Object object) {
        return object.getClass().equals(type);
      }
    };
  }

  private Predicate<Object> aClassOf(final String type) {
    return new BasePredicate<Object>() {
      @Override
      public boolean test(Object object) {
        return object.getClass().getName().equals(type);
      }
    };
  }

  private Function<? super JavassistExceptionCreator, ?> newExceptionOf(final String type) {
    return new BaseFunction<JavassistExceptionCreator, Object>() {
      @Override
      public Object apply(JavassistExceptionCreator javassistExceptionCreator) {
        return javassistExceptionCreator.create(type);
      }
    };
  }

}
