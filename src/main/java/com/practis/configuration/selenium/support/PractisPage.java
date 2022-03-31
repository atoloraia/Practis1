package com.practis.configuration.selenium.support;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.springframework.stereotype.Component;

@Retention(RUNTIME)
@Component
public @interface PractisPage {

}
