package com.practis.selenide.admin;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.admin;
import static com.practis.web.selenide.configuration.data.NewAdminInputData.getNewAdminInput;
import static com.practis.web.selenide.validator.AdminValidator.assertAdminData;
import static com.practis.web.selenide.validator.AdminValidator.assertAdminGridRow;
import static com.practis.web.selenide.validator.CompanyValidator.assertElementsOnCompanySettingsPage;
import static java.lang.String.format;

import com.practis.dto.NewAdminInput;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
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


  @Test
  @TestRailTest(caseId = 5555)
  @DisplayName("Check Web Elements on User Settings Page")
  void adminCreation() {
    admin().createAdmin(inputData);

    //assert message
    snackbar().getMessage().shouldBe(exactText("1 Practis admin has been created!"));

    //assert grid row data
    final var adminGridRow = admin().searchAdmin(inputData.getEmail());
    assertAdminGridRow(inputData, adminGridRow);

    //assert edit page data
    adminGridRow.click();
    assertElementsOnCompanySettingsPage();
  }


  @AfterEach
  void cleanup() {
    adminEmailsToRemove.forEach(email -> practisApi().deleteAdmin(email));
  }
}
