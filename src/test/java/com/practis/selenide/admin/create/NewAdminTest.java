package com.practis.selenide.admin.create;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.navigationAdminSideBar;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.adminEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.adminService;
import static com.practis.web.selenide.configuration.data.NewAdminInputData.getNewAdminInput;
import static com.practis.web.selenide.configuration.data.NewAdminInputData.getNewAdminInputs;
import static com.practis.web.selenide.validator.admin.AdminValidator.assertAdminData;
import static com.practis.web.selenide.validator.admin.AdminValidator.assertAdminGridRow;
import static com.practis.web.selenide.validator.admin.AdminValidator.assertElementsOnCreateCompanyPage;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

import com.practis.dto.NewAdminInput;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
class NewAdminTest {

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

  @TestRailTest(caseId = 5242)
  @DisplayName("Check WEB Elements on 'New Practis Admin' page")
  void checkElementsNewAdmin() {
    assertElementsOnCreateCompanyPage();
  }

  @TestRailTest(caseId = 41)
  @DisplayName("Create Admin")
  void adminCreation() {
    adminService().createAdmin(inputData);

    //assert message
    snackbar().getMessage().shouldBe(exactText("1 Practis admin has been created!"));

    //assert grid row data
    final var adminGridRow = adminService()
        .searchAdmin(inputData.getEmail().toLowerCase(Locale.ROOT));
    assertAdminGridRow(inputData, adminGridRow);

    //assert edit page data
    adminGridRow.click();
    assertAdminData(inputData, adminEditPage());
  }

  @TestRailTest(caseId = 42)
  @DisplayName("Create Admin: Validation: Already used email")
  void createAdmin_EmailAlreadyUsed() {
    practisApi().createAdmin(inputData);

    adminService().createAdmin(inputData);

    //assert message
    snackbar().getMessage()
        .shouldBe(exactText(format("User with email %s already exists!", inputData.getEmail())));
  }

  @TestRailTest(caseId = 43)
  @DisplayName("Create Admin: Validation: Short password")
  void createAdmin_ShortPassword() {
    inputData.setPassword("test");

    adminService().createAdmin(inputData);

    //assert message
    adminService().getPasswordValidationMessage()
        .shouldBe(exactText("Password must be 8 characters long."));
  }

  @TestRailTest(caseId = 44)
  @DisplayName("Create Admin: CRUD for multiple adding")
  void createAdmin_Crud_MultipleAdding() {
    final var inputs = getNewAdminInputs().stream().limit(3).collect(toList());

    //Add Admin Row
    final var firstAdmin = inputs.get(0);
    adminService().fillCreateAdminForm(firstAdmin, 0);

    //Delete Admin Row and check that 'Create' button is disabled
    adminService().deleteRow(0);
    adminCreatePage().getCreateButton().shouldBe(disabled);

    //Add Admin Row
    final var secondAdmin = inputs.get(1);
    secondAdmin.setEmail(format(secondAdmin.getEmail(), timestamp()));
    adminService().addRow();
    adminService().fillCreateAdminForm(secondAdmin, 0);

    //Check Show/Hide password
    adminService().showPassword(0);
    adminCreatePage().getPasswordField().get(0).shouldBe(attribute("type", "text"));
    adminService().hidePassword(0);
    adminCreatePage().getPasswordField().get(0)
        .shouldBe(attribute("type", "password"));

    //Add Admin Row
    final var thirdAdmin = inputs.get(2);
    thirdAdmin.setEmail(format(thirdAdmin.getEmail(), timestamp()));
    adminService().addRow();
    adminService().fillCreateAdminForm(thirdAdmin, 1);
    adminEmailsToRemove.add(secondAdmin.getEmail());
    adminEmailsToRemove.add(thirdAdmin.getEmail());

    //Select 2 Admins row and click 'Create'
    adminService().clickCreate();

    //assert message
    snackbar().getMessage().shouldBe(exactText("2 Practis admins have been created!"));

    navigationAdminSideBar().adminNavigationItem.click();

    //assert edit page data
    Stream.of(secondAdmin, thirdAdmin).forEach(admin -> {
      final var adminGridRow =
          adminService().searchAdmin(admin.getEmail().toLowerCase(Locale.ROOT));
      adminGridRow.click();
      assertAdminData(admin, adminEditPage());
    });
  }

  @AfterEach
  void cleanup() {
    adminEmailsToRemove.forEach(email -> practisApi().deleteAdmin(email));
  }
}