package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInputs;
import static java.lang.String.format;

import com.practis.dto.NewUserInput;
import com.practis.rest.dto.admin.RestCompanyResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.AccessLevel;
import lombok.Getter;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class SignUpUserExtension
        implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    @Getter(AccessLevel.PACKAGE)
    private final List<NewUserInput> usersToRemove = new ArrayList<>();

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final var annotation =
                context.getTestMethod().orElseThrow().getAnnotation(RegisteredUserExtension.class);
        final var companyId =
                practisApi()
                        .findCompany(annotation.company())
                        .map(RestCompanyResponse::getId)
                        .orElseThrow();

        final List<NewUserInput> invite =
                inviteUsers(annotation.limit(), companyId, annotation.role());
        signUpUsers(invite);
    }

    void signUpUsers(List<NewUserInput> invite) {
        final var signedUp = practisApi().signupUsers(invite);
        usersToRemove.addAll(signedUp);
    }

    public List<NewUserInput> inviteUsers(final int limit, final int companyId, final int roleId) {
        final var input =
                getNewUserInputs().stream()
                        .limit(limit)
                        .peek(user -> user.setEmail(format(user.getEmail(), timestamp())))
                        .peek(user -> user.setFirstName(format(user.getFirstName(), timestamp())))
                        .map(
                                user ->
                                        NewUserInput.builder()
                                                .email(user.getEmail())
                                                .firstName(user.getFirstName())
                                                .lastName(user.getLastName())
                                                .companyId(companyId)
                                                .roleId(roleId)
                                                .password(user.getPassword())
                                                .build())
                        .collect(Collectors.toList());
        return practisApi().inviteUsers(input);
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        usersToRemove.forEach(user -> practisApi().deleteUser(user.getEmail()));
    }

    @Override
    public boolean supportsParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return isTypeMatches(parameterContext) && isQualifierMatches(parameterContext);
    }

    @Override
    public Object resolveParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return usersToRemove;
    }

    private boolean isQualifierMatches(final ParameterContext parameterContext) {
        final var qualifier = parameterContext.getParameter().getAnnotation(Qualifier.class);
        if (Objects.isNull(qualifier)) {
            return true;
        }
        return qualifier.value().equals("registered");
    }

    private boolean isTypeMatches(final ParameterContext parameterContext) {
        return parameterContext
                .getParameter()
                .getParameterizedType()
                .getTypeName()
                .equals(format("java.util.List<%s>", NewUserInput.class.getName()));
    }
}
