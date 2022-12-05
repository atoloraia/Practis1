package com.practis.support;

import com.practis.support.extension.TestRailInvocationInterceptor;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith({TestRailInvocationInterceptor.class})
@Test
public @interface TestRailTest {

    /** TestRail test case id. */
    int caseId();
}
