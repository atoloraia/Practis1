package com.practis.support;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.practis.support.extension.AdminLoginExtension;
import com.practis.support.extension.CompanyLoginExtension;
import java.lang.annotation.Retention;
import org.junit.jupiter.api.extension.ExtendWith;

@Retention(RUNTIME)
@ExtendWith({
    CompanyLoginExtension.class
})
public @interface PractisCompanyTestClass {

}
