package com.practis.configuration.testrail;

import com.practis.configuration.junit.extension.TestRailInvocationInterceptor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith({
    TestRailInvocationInterceptor.class
})
@Test
public @interface TestRailTest {

  /**
   * TestRail test case id.
   */
  int caseId();
}
