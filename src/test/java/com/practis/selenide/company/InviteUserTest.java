package com.practis.selenide.company;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.data.company.NewUserInputData.getNewUserInput;
import static com.practis.web.selenide.validator.InviteUsersToTheAppValidator.assertElementsOnInviteUsersPage;

import com.practis.dto.NewLabelInput;
import com.practis.dto.NewUserInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteUserTest {

  private List<String> labelsToRemove;
  private List<String> usersToRemove;
  private NewUserInput inputData;

  @BeforeEach
  void init() {
    newItemSelector().create("User");

    inputData = getNewUserInput();
    inputData.setEmail(String.format(inputData.getEmail(), timestamp()));

    labelsToRemove = new ArrayList<>();
    usersToRemove = new ArrayList<>();
    usersToRemove.add(inputData.getEmail());
  }

  @Test
  @TestRailTest(caseId = 8687)
  @DisplayName("Check WEB Elements on 'Invite Users to the App page")
  void checkElementsInviteUser() {
    assertElementsOnInviteUsersPage();
  }

  @Test
  @TestRailTest(caseId = 1073)
  @DisplayName("Invite User")
  void inviteUser() {
    final var labelInput =
        NewLabelInput.builder().name(String.format("test-%s", timestamp())).build();
    final var label = practisApi().createLabel(labelInput).getName();
    labelsToRemove.add(labelInput.getName());
  }
}
