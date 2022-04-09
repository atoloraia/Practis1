package com.practis.configuration.testrail;

import com.practis.configuration.junit.JsonArgumentProvider;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ParameterizedTest
@ArgumentsSource(JsonArgumentProvider.class)
public @interface TestRailTest {

  /**
   * TestRail test case id.
   */
  int caseId();

  /**
   * Input object class to map input data.
   */
  Class<?> inputDataClass();
}
