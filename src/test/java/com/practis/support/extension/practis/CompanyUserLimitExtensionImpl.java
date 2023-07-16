package com.practis.support.extension.practis;

import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;

import com.practis.support.extension.CompanyUserLimitExtension;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class CompanyUserLimitExtensionImpl implements BeforeEachCallback, AfterEachCallback {

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final var annotation =
                context.getTestMethod()
                        .orElseThrow()
                        .getAnnotation(CompanyUserLimitExtension.class);

        practisApi().increaseCompanyUsersLimit(annotation.value());
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        practisApi().setUnlimitedCompanyUsers();
    }
}
