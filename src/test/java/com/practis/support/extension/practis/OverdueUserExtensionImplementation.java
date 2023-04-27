package com.practis.support.extension.practis;

import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static java.lang.String.format;

import com.practis.rest.dto.company.RestEnrollUnEnrollRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class OverdueUserExtensionImplementation
        implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    private final SignUpUserExtension signUpUserExtension = new SignUpUserExtension();
    private final CreatePractisExtension createPractisExtension = new CreatePractisExtension();

    private List<RestEnrollUnEnrollRequest> enrollments = new ArrayList<>();

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final var annotation =
                context.getTestMethod().orElseThrow().getAnnotation(OverdueUserExtension.class);
        final var companyId =
                practisApi()
                        .findCompany(webApplicationConfig().getAutomationCompanyName())
                        .orElseThrow()
                        .getId();

        final var invite =
                signUpUserExtension.inviteUsers(annotation.limit(), companyId, annotation.role());
        signUpUserExtension.signUpUsers(invite);
        final var userId = invite.get(0).getId();

        createPractisExtension.createPractisSets(annotation.limit());
        final var practisSetId = createPractisExtension.getPractisSetToRemove().get(0).getId();

        final var enrollment =
                RestEnrollUnEnrollRequest.builder()
                        .practisSetId(practisSetId)
                        .userId(userId)
                        .build();
        enrollments.add(enrollment);

        practisApi()
                .assignPractisSetWithDueDate(
                        enrollment.getPractisSetId(),
                        enrollment.getUserId(),
                        ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public boolean supportsParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext
                .getParameter()
                .getParameterizedType()
                .getTypeName()
                .equals(format("java.util.List<%s>", RestEnrollUnEnrollRequest.class.getName()));
    }

    @Override
    public Object resolveParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return enrollments;
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        signUpUserExtension.afterEach(context);
        createPractisExtension.afterEach(context);
    }
}
