package com.practis.selenide.admin.navigation.companies;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companyService;
import static com.practis.web.selenide.configuration.data.NewCompanyInputData.getNewCompanyInput;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertCompanyGridRow;
import static com.practis.web.selenide.validator.admin.CompanyValidator.assertElementsOnCompanySettingsPage;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
import static java.lang.String.format;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

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

  @TestRailTest(caseId = 8734)
  @DisplayName("Check WEB Elements 'Company Settings' page")
  void checkElementsOnCompanyProfilePage() {
    companyService().createCompany(inputData);
    await().pollDelay(TWO_SECONDS).until(() -> true);

    //assert grid row data
    final var companyGridRow = companyService().searchCompany(inputData.getName());
    assertCompanyGridRow(inputData, companyGridRow);

    //assert edit page data
    companyGridRow.click();
    await().pollDelay(TWO_SECONDS).until(() -> true);
    assertElementsOnCompanySettingsPage();
  }

  @AfterEach
  void cleanup() {
    companiesToRemove.forEach(name -> practisApi().deleteCompany(name));
  }

}
