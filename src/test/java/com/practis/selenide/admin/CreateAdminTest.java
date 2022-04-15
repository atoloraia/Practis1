package com.practis.selenide.admin;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.random;
import static com.practis.web.selenide.configuration.data.NewAdminInputData.getNewAdminInput;
import static com.practis.web.selenide.configuration.data.NewAdminInputData.getNewAdminInputs;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static com.practis.web.selenide.validator.AdminValidator.assertAdminData;
import static com.practis.web.selenide.validator.AdminValidator.assertAdminGridRow;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

import com.practis.dto.NewAdminInput;
import com.practis.support.PractisTestClassNew;
import com.practis.web.rest.service.PractisApiService;
import com.practis.web.selenide.component.Snackbar;
import com.practis.web.selenide.service.AdminService;
import com.practis.web.selenide.service.CompanySelectorService;
import com.practis.web.selenide.service.LoginService;
import com.practis.web.selenide.service.NewItemSelectorService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisTestClassNew
class CreateAdminTest {

  private final LoginService loginService = new LoginService();
  private final CompanySelectorService companySelectorService = new CompanySelectorService();
  private final NewItemSelectorService newItemSelectorService = new NewItemSelectorService();
  private final AdminService adminService = new AdminService();
  private final Snackbar snackbar = new Snackbar();
  private final PractisApiService apiService = new PractisApiService();

  private List<String> adminEmailsToRemove;
  private NewAdminInput inputData;

  @BeforeEach
  void beforeEach() {
    adminEmailsToRemove = new ArrayList<>();
    inputData = getNewAdminInput();
    inputData.setEmail(format(inputData.getEmail(), random()));

    adminEmailsToRemove.add(inputData.getEmail());

    loginService.login(webCredentialsConfig());
    companySelectorService.initAdmin();
    newItemSelectorService.create("New Admin");
  }

  @Test
  @DisplayName("Create Admin")
  void createAdmin() {
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

  @Test
  @DisplayName("Create Admin: Validation: Already used email")
  void createAdmin_EmailAlreadyUsed() {
    apiService.createAdmin(inputData);

    adminService.createAdmin(inputData);

    //assert message
    snackbar.getMessage().shouldBe(
        exactText(format("User with email %s already exists!", inputData.getEmail())));

    //todo do we have to check if admin is in grid. It is as we created it through REST endpoint.
  }

  @Test
  @DisplayName("Create Admin: Validation: Short password")
  void createAdmin_ShortPassword() {
    inputData.setPassword("test");

    adminService.createAdmin(inputData);

    //assert message
    adminService.getCreatePage().getPasswordValidationMessage()
        .shouldBe(exactText("Password must be at least 8 characters long."));
  }

  @Test
  @DisplayName("Create Admin: CRUD for multiple adding")
  void createAdmin_Crud_MultipleAdding() {
    //todo should we split it to 3 test?
    final var inputs = getNewAdminInputs().stream().limit(3).collect(toList());

    //==============
    final var firstAdmin = inputs.get(0);
    adminService.getCreatePage().fillCreateAdminForm(inputs.get(0), 0);
    adminService.getCreatePage().deleteRow(0);

    //assert create button disabled
    adminService.getCreatePage().getCreateButtonElement().shouldBe(disabled);

    //==============
    final var secondAdmin = inputs.get(1);
    secondAdmin.setEmail(format(secondAdmin.getEmail(), random()));

    adminService.getCreatePage().addRow();
    adminService.getCreatePage().fillCreateAdminForm(secondAdmin, 0);

    adminService.getCreatePage().showPassword(0);
    adminService.getCreatePage().getPasswordFieldElements().get(0)
        .shouldBe(attribute("type", "text"));

    adminService.getCreatePage().hidePassword(0);
    adminService.getCreatePage().getPasswordFieldElements().get(0)
        .shouldBe(attribute("type", "password"));

    //==============
    final var thirdAdmin = inputs.get(2);
    thirdAdmin.setEmail(format(thirdAdmin.getEmail(), random()));

    adminService.getCreatePage().addRow();
    adminService.getCreatePage().fillCreateAdminForm(thirdAdmin, 1);

    adminEmailsToRemove.add(secondAdmin.getEmail());
    adminEmailsToRemove.add(thirdAdmin.getEmail());
    adminService.createAdmins();

    //assert message
    snackbar.getMessage().shouldBe(exactText("2 Practis admins have been created!"));

    //assert edit page data
    Stream.of(secondAdmin, thirdAdmin).forEach(admin -> {
      final var adminGridRow = adminService.searchAdmin(admin.getEmail());
      adminGridRow.click();
      assertAdminData(admin, adminService.getEditPage());
    });
  }

  @AfterEach
  void cleanup() {
    adminEmailsToRemove.forEach(apiService::deleteAdmin);
  }
}
