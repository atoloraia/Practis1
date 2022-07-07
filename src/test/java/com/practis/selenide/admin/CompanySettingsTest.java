package com.practis.selenide.admin;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.company;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInput;
import static com.practis.web.selenide.validator.CompanyValidator.assertCompanyGridRow;
import static com.practis.web.selenide.validator.CompanyValidator.assertElementsOnCompanySettingsPage;
import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.practis.dto.NewCompanyInput;
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
public class CompanySettingsTest {

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
  @TestRailTest(caseId = 666)
  @DisplayName("Check WEB Elements 'Company Settings' page")
  void createCompany() {
    company().createCompany(inputData);

    //assert company in company selector list
    companySelector().open();
    final var companyInSelector = companySelector().findCompany(inputData.getName());
    assertTrue(companyInSelector.exists());

    //assert grid row data
    final var companyGridRow = company().searchCompany(inputData.getName());
    assertCompanyGridRow(inputData, companyGridRow);

    //assert edit page data
    companyGridRow.click();
    assertElementsOnCompanySettingsPage();

  }

  @AfterEach
  void cleanup() {
    companiesToRemove.forEach(name -> practisApi().deleteCompany(name));
  }

}
