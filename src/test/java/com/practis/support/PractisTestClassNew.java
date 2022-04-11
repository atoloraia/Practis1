package com.practis.support;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.practis.configuration.junit.extension.ChooseBrowserExtension;
import com.practis.configuration.junit.extension.CloseBrowserExtension;
import com.practis.configuration.junit.extension.GoToWebApplicationExtension;
import java.lang.annotation.Retention;
import org.junit.jupiter.api.extension.ExtendWith;

@Retention(RUNTIME)
@ExtendWith({
    //TestRailTestWatcher.class
    ChooseBrowserExtension.class,
    GoToWebApplicationExtension.class,
    CloseBrowserExtension.class
})
public @interface PractisTestClassNew {

}
