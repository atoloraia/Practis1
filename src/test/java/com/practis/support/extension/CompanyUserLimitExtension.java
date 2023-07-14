package com.practis.support.extension;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.practis.support.extension.practis.CompanyUserLimitExtensionImpl;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;

@Target({ElementType.METHOD})
@Retention(RUNTIME)
@ExtendWith({CompanyUserLimitExtensionImpl.class})
public @interface CompanyUserLimitExtension {

    /** Limit. */
    int value();
}
