package com.practis.selenide.admin;

import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyCreatePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.company;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInput;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInputs;
import static com.practis.web.selenide.validator.CompanyValidator.assertCompanyData;
import static com.practis.web.selenide.validator.CompanyValidator.assertCompanyGridRow;
import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.practis.dto.NewCompanyInput;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisAdminTestClass
@SelenideTestClass
@TestRailTestClass
class NewCompanyTest {

  private List<String> companiesToRemove;
  private NewCompanyInput inputData;

  @BeforeEach
  void beforeEach() {
    newItemSelector().create("New Company");

    inputData = getNewCompanyInput();
    inputData.setName(format(inputData.getName(), timestamp()));
    inputData.setEmail(format(inputData.getEmail(), timestamp()));

    companiesToRemove = new ArrayList<>();
    companiesToRemove.add(inputData.getName());
  }

  @Test
  @TestRailTest(caseId = 45)
  @DisplayName("Create Company")
  void createCompany() {
    company().createCompany(inputData);

    //assert message
    snackbar().getMessage().shouldBe(exactText("1 Company has been created"));

    //assert company in company selector list
    companySelector().open();
    final var companyInSelector = companySelector().findCompany(inputData.getName());
    assertTrue(companyInSelector.exists());

    //assert grid row data
    final var companyGridRow = company().searchCompany(inputData.getName());
    assertCompanyGridRow(inputData, companyGridRow);

    //assert edit page data
    companyGridRow.click();
    assertCompanyData(inputData, companyEditPage());
  }

  @Test
  @TestRailTest(caseId = 42)
  @DisplayName("Create Company: Validation: Already used email")
  void validation_UserExists() {
    practisApi().createCompany(inputData);
    company().createCompany(inputData);

    //Check snackbar message “User with this email [email] already exists!“
    snackbar().getMessage()
        .shouldBe(
            exactText(format("User with email %s already exists!", inputData.getEmail())));

    //assert grid row data
    final var companyGridRow = company().searchCompany(inputData.getName()).getRowElement();
    assertTrue(companyGridRow.exists());
  }

  @Test
  @TestRailTest(caseId = 47)
  @DisplayName("Create Admin: CRUD for multiple adding.")
  void crudNewCompany() {
    //fill data for Company_1
    final var inputs = getNewCompanyInputs().stream().limit(3)
        .peek(inputData -> inputData.setName(format(inputData.getName(), timestamp())))
        .peek(inputData -> inputData.setEmail(format(inputData.getEmail(), timestamp())))
        .collect(toList());

    //==============
    final var firstCompany = inputs.get(0);
    companyCreatePage().fillCreateCompanyForm(firstCompany, 0);
    companyCreatePage().deleteRow(0);

    //assert invite button disabled
    companyCreatePage().getInviteButtonElement().shouldBe(disabled);

    //==============
    final var secondCompany = inputs.get(1);

    companyCreatePage().addRow();
    companyCreatePage().fillCreateCompanyForm(secondCompany, 0);

    //==============
    final var thirdCompany = inputs.get(2);

    companyCreatePage().addRow();
    companyCreatePage().fillCreateCompanyForm(thirdCompany, 1);

    companiesToRemove.add(secondCompany.getName());
    companiesToRemove.add(thirdCompany.getName());
    companyCreatePage().clickInvite();

    //assert message
    snackbar().getMessage().shouldBe(exactText("2 Companies have been created"));

    //assert edit page data
    Stream.of(secondCompany, thirdCompany).forEach(company -> {
      final var companyGridRow = company().searchCompany(company.getName()).getRowElement();
      companyGridRow.click();
      assertCompanyData(company, companyEditPage());
    });
  }

  @AfterEach
  void cleanup() {
    companiesToRemove.forEach(name -> practisApi().deleteCompany(name));
  }
}
