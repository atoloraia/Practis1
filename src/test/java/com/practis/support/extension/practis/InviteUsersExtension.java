package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInputs;
import static java.lang.String.format;

import com.practis.dto.PractisUser;
import com.practis.rest.dto.admin.RestCompanyResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class InviteUsersExtension implements
    BeforeEachCallback, AfterEachCallback, ParameterResolver {

  private final List<PractisUser> usersToRemove = new ArrayList<>();

  @Override
  public void beforeEach(final ExtensionContext context) throws Exception {
    final var annotation = context.getTestMethod().orElseThrow()
        .getAnnotation(InviteUserExtension.class);
    final var input = getNewUserInputs().stream()
        .limit(annotation.limit())
        .peek(user -> user.setEmail(format(user.getEmail(), timestamp())))
        .peek(user -> user.setFirstName(format(user.getFirstName(), timestamp())))
        .map(user -> PractisUser.builder()
            .email(user.getEmail())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .companyId(practisApi().findCompany(annotation.company())
                .map(RestCompanyResponse::getId)
                .orElseThrow())
            .roleId(annotation.role())
            .build())
        .collect(Collectors.toList());
    usersToRemove.addAll(practisApi().inviteUsers(input));
  }

  @Override
  public void afterEach(final ExtensionContext context) throws Exception {
    //usersToRemove.forEach(label -> practisApi().deleteLabel(label.getName()));
  }

  @Override
  public boolean supportsParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return parameterContext.getParameter().getParameterizedType().getTypeName()
        .equals(format("java.util.List<%s>", PractisUser.class.getName()));
  }

  @Override
  public Object resolveParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return usersToRemove;
  }
}
