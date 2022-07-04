package com.practis.selenide.company;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;
import static com.practis.web.selenide.validator.ChallengeValidator.assertElementsOnNewChallengePage;
import static com.practis.web.selenide.validator.InviteUsersToTheAppValidator.assertElementsOnInviteUsersPage;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class InviteUserTest {

  @BeforeEach
  void init() {
    newItemSelector().create("User");
  }

  @Test
  @TestRailTest(caseId = 8687)
  @DisplayName("Check WEB Elements on 'Invite Users to the App page")
  void checkElementsInvite() {
    assertElementsOnInviteUsersPage();
  }
}
