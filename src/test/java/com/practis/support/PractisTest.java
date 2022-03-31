package com.practis.support;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.AFTER_METHOD;

import java.lang.annotation.Retention;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

@Retention(RUNTIME)
@Test
@DirtiesContext(methodMode = AFTER_METHOD)
public @interface PractisTest {

}
