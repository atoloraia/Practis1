package com.practis.support;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.practis.support.extension.TestRailInvocationInterceptor;
import com.practis.support.extension.TestRailStartReportExtension;
import com.practis.support.extension.TestRailWatcherExtension;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;

@Target({ElementType.TYPE})
@Retention(RUNTIME)
@ExtendWith({
    TestRailInvocationInterceptor.class,
    TestRailStartReportExtension.class,
    TestRailWatcherExtension.class
})
public @interface TestRailTestClass {

}
