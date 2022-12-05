package com.practis.support;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.practis.support.extension.CompanyLoginExtension;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;

@Target({ElementType.TYPE})
@Retention(RUNTIME)
@ExtendWith({CompanyLoginExtension.class})
public @interface PractisCompanyTestClass {}
