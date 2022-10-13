package com.practis.selenide.company.navigation.library.practisset;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetEditPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.validator.company.PractisSetValidator.assertCreatedPractisSet;
import static com.practis.web.selenide.validator.company.PractisSetValidator.assertElementsEditPractisSet;
import static com.practis.web.selenide.validator.company.PractisSetValidator.assertElementsViewPractisSet;
import static com.practis.web.util.SelenidePageLoadAwait.awaitAjaxComplete;
import static com.practis.web.util.SelenidePageUtil.openPage;

import com.practis.dto.NewPractisSetInput;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.PractisSetExtension;
import org.junit.jupiter.api.DisplayName;


@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class EditPractisSetTest {

  /**
   * Create Practis Set.
   */
  @TestRailTest(caseId = 8789)
  @DisplayName("Check Web Elements on 'View Practis Set' Page")
  @PractisSetExtension(count = 1)
  void checkElementsViewPractisSet(final NewPractisSetInput practisSet) {
    //open Library: Practis Set tab
    openPage(webApplicationConfig().getUrl() + "/library/practis-sets");

    final var practisSetGridRow = practisSetService().searchPS(practisSet.getName());

    awaitAjaxComplete(10);
    practisSetGridRow.click();
    awaitAjaxComplete(10);

    assertCreatedPractisSet(practisSet);
    assertElementsViewPractisSet();
    practisSetEditPage().getScenarioTab().click();
    practisSetEditPage().getEditButton().click();
    areYouSurePopUp().getConfirmButton().click();
    assertElementsEditPractisSet();
  }

}
