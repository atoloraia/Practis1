package com.practis.selenide.admin;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.companyEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.company;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInput;
import static com.practis.web.selenide.validator.CompanyValidator.assertCompanyData;
import static com.practis.web.selenide.validator.CompanyValidator.assertCompanyGridRow;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.practis.dto.NewCompanyInput;
import com.practis.support.PractisAdminTestClass;
import com.practis.support.SelenideTestClass;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@PractisAdminTestClass
@SelenideTestClass
class UpNewCompanyTest {

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

  /**
   * Create Company.
   */
  @Test
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

  @AfterEach
  void cleanup() {
    companiesToRemove.forEach(name -> practisApi().deleteCompany(name));
  }
}
