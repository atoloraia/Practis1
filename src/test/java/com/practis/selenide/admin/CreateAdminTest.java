package com.practis.selenide.admin;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.practis.utils.StringUtils.random;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static com.practis.web.selenide.validator.AdminValidator.assertAdminData;
import static com.practis.web.selenide.validator.AdminValidator.assertAdminGridRow;

import com.practis.dto.NewAdminInput;
import com.practis.support.PractisTest;
import com.practis.support.PractisTestClassNew;
import com.practis.web.selenide.component.Snackbar;
import com.practis.web.selenide.service.AdminService;
import com.practis.web.selenide.service.CompanySelectorService;
import com.practis.web.selenide.service.LoginService;
import com.practis.web.selenide.service.NewItemSelectorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@PractisTestClassNew
class CreateAdminTest {

  private final LoginService loginService = new LoginService();
  private final CompanySelectorService companySelectorService = new CompanySelectorService();
  private final NewItemSelectorService newItemSelectorService = new NewItemSelectorService();
  private final AdminService adminService = new AdminService();
  private final Snackbar snackbar = new Snackbar();

  private NewAdminInput inputData;

  @BeforeEach
  void beforeAll() {
    inputData = NewAdminInput.builder()
        .email("automationPractisCompany+" + random() + "@tula.co")
        .firstName("AutoFirstName")
        .lastName("AutoLastName")
        .password("pass1234*")
        .build();

    loginService.login(webCredentialsConfig());
    companySelectorService.initAdmin();
    newItemSelectorService.create("New Admin");
  }

  @Test
  void test() {
    adminService.createAdmin(inputData);

    //assert message
    snackbar.getMessage().shouldBe(exactText("1 Practis admin has been created!"));

    //assert grid row data
    final var adminGridRow = adminService.searchAdmin(inputData.getEmail());
    assertAdminGridRow(inputData, adminGridRow);

    //assert edit page data
    adminGridRow.click();
    assertAdminData(inputData, adminService.getEditPage());
  }
}
