package com.practis.selenide.admin;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.adminService;
import static com.practis.web.selenide.configuration.data.NewAdminInputData.getNewAdminInput;
import static com.practis.web.selenide.validator.AdminValidator.assertElementsOnUserSettingsPage;
import static java.lang.String.format;

import com.practis.dto.NewAdminInput;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
class UserSettingsTest {

  private List<String> adminEmailsToRemove;
  private NewAdminInput inputData;

  @BeforeEach
  void beforeEach() {
    newItemSelector().create("New Admin");

    inputData = getNewAdminInput();
    inputData.setEmail(format(inputData.getEmail(), timestamp()));

    adminEmailsToRemove = new ArrayList<>();
    adminEmailsToRemove.add(inputData.getEmail());
  }


  @TestRailTest(caseId = 8788)
  @DisplayName("Check Web Elements on User Settings Page")
  void assertElementsOnAdminUserProfilePage() {
    adminService().createAdmin(inputData);

    //assert grid row data
    final var adminGridRow =
        adminService().searchAdmin(inputData.getEmail().toLowerCase(Locale.ROOT));

    //assert edit page data
    adminGridRow.click();
    assertElementsOnUserSettingsPage();
  }


  @AfterEach
  void cleanup() {
    adminEmailsToRemove.forEach(email -> practisApi().deleteAdmin(email));
  }
}
