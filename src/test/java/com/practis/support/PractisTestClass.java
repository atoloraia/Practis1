package com.practis.support;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.springframework.test.context.TestConstructor.AutowireMode.ALL;

import com.practis.TestApplication;
import java.lang.annotation.Retention;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

@Retention(RUNTIME)
@SpringBootTest(classes = TestApplication.class)
@TestConstructor(autowireMode = ALL)
public @interface PractisTestClass {

}
