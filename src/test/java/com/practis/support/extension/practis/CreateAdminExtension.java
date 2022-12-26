package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.NewAdminInputData.getNewAdminInput;
import static java.lang.String.format;

import com.practis.rest.dto.admin.RestAdminResponse;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreateAdminExtension
        implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    private final List<RestAdminResponse> adminToRemove = new ArrayList<>();

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final var input = getNewAdminInput();
        input.setEmail(String.format(input.getEmail(), timestamp()));
        final var admin = practisApi().createAdmin(input);
        adminToRemove.add(admin);
    }

    @Override
    public void afterEach(final ExtensionContext extensionContext) throws Exception {
        adminToRemove.forEach(admin -> practisApi().deleteAdmin(admin.getEmail()));
    }

    @Override
    public boolean supportsParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext
                .getParameter()
                .getParameterizedType()
                .getTypeName()
                .equals(format("java.util.List<%s>", RestAdminResponse.class.getName()));
    }

    @Override
    public Object resolveParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return adminToRemove;
    }
}
