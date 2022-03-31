package com.practis;

import static com.practis.utils.AwaitUtils.awaitSeconds;
import static com.practis.utils.StringUtils.currentDate;
import static com.practis.utils.StringUtils.random;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.practis.dto.NewCompanyInput;
import com.practis.support.PractisTest;
import com.practis.support.PractisTestClass;
import com.practis.web.WebApplication;
import com.practis.web.page.admin.CompanyAccountsPage;
import com.practis.web.page.admin.CompanyEditPage;
import com.practis.web.page.admin.CompanyNewPage;
import java.util.List;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;

@PractisTestClass
@RequiredArgsConstructor
class NewCompanyTest {

  private final WebApplication webApplication;

  private final CompanyAccountsPage companyAccountsPage;
  private final CompanyNewPage newCompanyPage;
  private final CompanyEditPage companyEditPage;

  @BeforeEach
  void init() {
    webApplication.initAdmin();
  }

  @PractisTest
  void createCompany() {
    //given
    final var input = NewCompanyInput.builder()
        .name("CompanyAuto -" + currentDate())
        .email("automationPractisCompany+" + random() + "@tula.co")
        .firstName("AutoFirstName")
        .lastName("AutoLastName")
        .build();

    //Go to /companies page. Click “+” → New Company.
    companyAccountsPage.newEntity().selectEntity("New Company").click();

    //Enter Company Name, Email, First Name and Last Name.
    newCompanyPage.fillForm(0, input).invite();

    //assert snackbar message "1 Company has been created".
    assertEquals(
        "1 Company has been created", companyAccountsPage.getSnackbar().getSuccessNotification());

    //assert company in company selector list
    companyAccountsPage.clickCompanySelector();
    awaitSeconds(10,
        () -> companyAccountsPage.findCompanyUnderCompanySelector(input.getName()).isPresent());
    companyAccountsPage.clickCompanySelector();

    //assert company is in grid
    companyAccountsPage.search(input.getName());
    final var companies = companyAccountsPage.getCompanies();

    assertEquals(1, companies.size());
    final var createdCompany = companies.get(0);
    assertEquals(input.getName(), createdCompany.getName());

    //assert company data
    companyAccountsPage.goToCompany(input.getName());

    assertEquals(input.getName(), companyEditPage.getCompanyName());
  }

  @PractisTest
  void validation_UserExists() {
    //get email for the first company in the grid
    final var firstCompany = companyAccountsPage.getCompanies().stream().findFirst().orElseThrow();
    companyAccountsPage.goToCompany(firstCompany.getName());
    final var firstCompanyEmail = companyEditPage.getEmail();

    final var input = NewCompanyInput.builder()
        .name("CompanyAuto -" + currentDate())
        .email(firstCompanyEmail)
        .firstName("AutoFirstName")
        .lastName("AutoLastName")
        .build();

    //Go to /companies page. Click “+” → New Company.
    companyAccountsPage.newEntity().selectEntity("New Company").click();

    //assert create button disabled
    final var createButton = newCompanyPage.getInviteButton();
    assertNotNull(createButton.getAttribute("disabled"));

    //Enter already used Email, Company Name, First Name and Last Name
    newCompanyPage.fillForm(0, input).invite();

    //Check snackbar message “User with this email [email] already exists!“
    final var errorNotification = newCompanyPage.getSnackbar().getErrorNotification();
    assertEquals(
        "User with this email " + firstCompanyEmail + " already exists!", errorNotification);
    // TODO Check the Company is NOT shown in Company Accounts
  }

  @PractisTest
  void crudNewCompany() {
    //get email for the first company in the grid
    final var inputs = List.of(
        NewCompanyInput.builder()
            .name("CompanyAuto1 -" + currentDate())
            .email("automationPractisCompany+" + random() + "@tula.co")
            .firstName("AutoFirstName1")
            .lastName("AutoLastName1")
            .build(),
        NewCompanyInput.builder()
            .name("CompanyAuto2 -" + currentDate())
            .email("automationPractisCompany+" + random() + "@tula.co")
            .firstName("AutoFirstName2")
            .lastName("AutoLastName2")
            .build());

    //Go to /companies page. Click “+” → New Company.
    companyAccountsPage.newEntity().selectEntity("New Company").click();

    //Enter email, Company Name, First Name and Last Name.
    newCompanyPage.setRowNum(inputs.size());

    //TODO Click “Add another”.

    IntStream.of(0, inputs.size() - 1)
        .forEach(idx -> newCompanyPage.fillForm(idx, inputs.get(idx)));
    newCompanyPage.invite();

    //then
    final var notification = companyAccountsPage.getSnackbar().getSuccessNotification();
    assertEquals("2 Companies have been created", notification);

    //TODO Check the Companies with appropriate data are shown in Company Accounts
  }
}
