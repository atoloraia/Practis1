package com.practis.support;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.practis.support.extension.ChooseBrowserExtension;
import com.practis.support.extension.CloseBrowserExtension;
import java.lang.annotation.Retention;
import org.junit.jupiter.api.extension.ExtendWith;

@Retention(RUNTIME)
@ExtendWith({
    ChooseBrowserExtension.class,
    CloseBrowserExtension.class,
})
public @interface SelenideTestClass {}
