package com.practis.support;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.practis.configuration.testrail.TestRailTestWatcher;
import java.lang.annotation.Retention;
import org.junit.jupiter.api.extension.ExtendWith;

@Retention(RUNTIME)
@ExtendWith(TestRailTestWatcher.class)
public @interface PractisTestClassNew {

}
