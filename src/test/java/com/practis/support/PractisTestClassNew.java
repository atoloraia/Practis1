package com.practis.support;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.practis.configuration.extension.ChooseBrowserExtension;
import com.practis.configuration.extension.CloseBrowserExtension;
import com.practis.configuration.extension.GoToWebApplicationExtension;
import java.lang.annotation.Retention;
import org.junit.jupiter.api.extension.ExtendWith;

@Retention(RUNTIME)
@ExtendWith({
    ChooseBrowserExtension.class,
    GoToWebApplicationExtension.class,
    CloseBrowserExtension.class,
    //TestRailTestWatcher.class
})
public @interface PractisTestClassNew {

}
