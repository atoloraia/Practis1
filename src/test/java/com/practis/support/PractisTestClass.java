package com.practis.support;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.test.context.TestConstructor.AutowireMode.ALL;

import com.practis.TestApplication;
import com.practis.support.extension.TestRailStartReportExtension;
import com.practis.support.extension.TestRailWatcherExtension;
import java.lang.annotation.Retention;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

@Retention(RUNTIME)
@SpringBootTest(classes = TestApplication.class)
@ExtendWith({
    TestRailStartReportExtension.class,
    TestRailWatcherExtension.class
})
@TestConstructor(autowireMode = ALL)
public @interface PractisTestClass {

}
