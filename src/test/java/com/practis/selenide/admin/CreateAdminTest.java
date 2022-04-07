package com.practis.selenide.admin;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.random;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;

import com.practis.dto.NewAdminInput;
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

    snackbar.getMessage().shouldBe(exactText("1 Practis admin has been created!"));
  }
}
