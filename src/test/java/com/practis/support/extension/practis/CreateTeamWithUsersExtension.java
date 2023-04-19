package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static java.lang.String.format;

import com.practis.dto.NewTeamInput;
import com.practis.support.extension.dto.TeamWithChildren;
import java.util.List;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreateTeamWithUsersExtension
        implements BeforeEachCallback, AfterEachCallback, ParameterResolver {

    private final SignUpUserExtension signUpUserExtension = new SignUpUserExtension();

    private NewTeamInput teamToRemove;

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final var annotation =
                context.getTestMethod().orElseThrow().getAnnotation(TeamExtensionWithUsers.class);
        teamToRemove = practisApi().createTeam(format("test-%s", timestamp()));
        final var companyId =
                practisApi()
                        .findCompany(webApplicationConfig().getAutomationCompanyName())
                        .orElseThrow()
                        .getId();
        final var invite = signUpUserExtension.inviteUsers(annotation.users(), companyId, 7);
        signUpUserExtension.signUpUsers(invite);

        practisApi()
                .addMembersToTeam(
                        teamToRemove.getId(),
                        List.of(signUpUserExtension.getUsersToRemove().get(0).getId()));
    }

    @Override
    public void afterEach(final ExtensionContext context) throws Exception {
        practisApi().deleteTeam(teamToRemove.getName());
        signUpUserExtension.afterEach(context);
    }

    @Override
    public boolean supportsParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return TeamWithChildren.class.isAssignableFrom(parameterContext.getParameter().getType());
    }

    @Override
    public Object resolveParameter(
            final ParameterContext parameterContext, final ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return TeamWithChildren.builder()
                .team(teamToRemove)
                .users(signUpUserExtension.getUsersToRemove())
                .build();
    }
}
