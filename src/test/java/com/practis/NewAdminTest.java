package com.practis;

import static com.practis.utils.StringUtils.random;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.practis.dto.NewAdminInput;
import com.practis.support.PractisTest;
import com.practis.support.PractisTestClass;
import com.practis.web.WebApplication;
import com.practis.web.component.GridSearchComponent;
import com.practis.web.component.NewItemComponent;
import com.practis.web.page.admin.administrators.AdministratorsEditAssertionPage;
import com.practis.web.page.admin.administrators.AdministratorsGridAssertionPage;
import com.practis.web.page.admin.administrators.AdministratorsNewAssertionPage;
import com.practis.web.page.common.component.admin.NavigationComponent;
import com.practis.web.page.common.component.admin.SnackbarComponent;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;

@PractisTestClass
@RequiredArgsConstructor
class NewAdminTest {

  private final WebApplication webApplication;

  private final NewItemComponent newItemComponent;
  private final GridSearchComponent searchComponent;
  private final NavigationComponent navigationComponent;
  private final SnackbarComponent snackbarComponent;

  private final AdministratorsNewAssertionPage newAdminPage;
  private final AdministratorsGridAssertionPage gridAdminPage;
  private final AdministratorsEditAssertionPage editAdminPage;

  @BeforeEach
  void init() {
    webApplication.initAdmin();
  }

  @PractisTest
  void adminCreation() {
    //given
    final var input =
        NewAdminInput.builder()
            .email("automationPractisCompany+" + random() + "@tula.co")
            .firstName("AutoFirstName")
            .lastName("AutoLastName")
            .password("pass1234*")
            .build();

    //when
    newItemComponent.clickNewItem().clickRow("New Admin");

    //Enter email, first name, last name, password.Click “Create” button
    newAdminPage.fillFormAndSubmit(input);

    //assert admin created. Check snackbar message “1 Practis admin has been created!“
    assertEquals("1 Practis admin has been created!", snackbarComponent.getSuccessNotification());

    //Check new Admin with appropriate data is shown in Administrators list
    navigationComponent.goTo("Admin");
    searchComponent.search(input.getEmail());

    //Open Admin and check the data.
    final var createdAdministrator = gridAdminPage.getFirstAdministratorInGrid();
    gridAdminPage.assertEqual(input, createdAdministrator);

    gridAdminPage.goTo(createdAdministrator);
    editAdminPage.assertPageData(input);
  }

  @PractisTest
  void validation_UserExists() {
    //given
    navigationComponent.goTo("Admin");
    final var existingAdministrator = gridAdminPage.getFirstAdministratorInGrid();

    final var input =
        NewAdminInput.builder()
            .email(existingAdministrator.getEmail())
            .firstName("AutoFirstName")
            .lastName("AutoLastName")
            .password("pass1234*").build();

    //Enter email, first name, last name, password.Click “Create” button
    newItemComponent.clickNewItem().clickRow("New Admin");

    //assert Create button disabled
    newAdminPage.assertCreateButtonDisabled();

    //Enter already used email , first name, last name, password.
    newAdminPage.fillFormAndSubmit(input);

    //assert snackbar message appears
    snackbarComponent.assertSuccessNotification(
        "User with email " + input.getEmail().toLowerCase() + " already exists!");

    //assert the Admin is NOT shown in Administrators list.
    navigationComponent.makeActive().goTo("Admin");
    searchComponent.search(input.getEmail());

    final var administrator = gridAdminPage.getFirstAdministratorInGrid();
    gridAdminPage.assertNotEqual(input, administrator);
  }

  @PractisTest
  void validation_Password() {
    //given
    final var input =
        NewAdminInput.builder()
            .email("automationPractisCompany+" + random() + "@tula.co")
            .firstName("AutoFirstName")
            .lastName("AutoLastName")
            .password("1234567").build();

    //Go to /companies page. Click “+” → New Admin.
    newItemComponent.clickNewItem().clickRow("New Admin");

    //Enter email, first name, last name and password (less than 8 characters)
    newAdminPage.fillFormAndSubmit(input);

    //assert snackbar message “Password must be at least 8 characters long.“
    newAdminPage.assertPasswordValidationMessage();
  }

  @PractisTest
  void crudNewAdmin() {
    //given
    final var input = List.of(
        NewAdminInput.builder()
            .email("automationPractisCompany+" + random() + "@tula.co")
            .firstName("AutoFirstName1").lastName("AutoLastName1")
            .password("pass1234*").build(),
        NewAdminInput.builder()
            .email("automationPractisCompany+" + random() + "@tula.co")
            .firstName("AutoFirstName2")
            .lastName("AutoLastName2")
            .password("pass1234*").build());

    //Go to /companies page. Click “+” → New Admin.
    newItemComponent.clickNewItem().clickRow("New Admin");

    //TODO Update according to the test
    newAdminPage.setRowsNumber(input.size());
    IntStream.of(0, input.size() - 1).forEach(idx -> newAdminPage.fillRow(idx, input.get(idx)));

    //assert password visibility
    newAdminPage.showPassword(0);
    newAdminPage.assertPasswordVisible(0);
    newAdminPage.hidePassword(0);
    newAdminPage.assertPasswordNotVisible(0);

    newAdminPage.submit();

    ////Enter email, first name, last name, password.Click “Create” button
    snackbarComponent.assertSuccessNotification("2 Practis admins have been created!");

    ////TODO Check the Companies with appropriate data are shown in Company Accounts
  }
}
