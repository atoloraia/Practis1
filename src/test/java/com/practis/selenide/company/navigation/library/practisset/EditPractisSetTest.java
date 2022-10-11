package com.practis.selenide.company.navigation.library.practisset;

import static com.codeborne.selenide.Selenide.open;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.practisSetService;
import static com.practis.web.selenide.configuration.data.company.NewChallengeInputData.getNewChallengeInput;
import static com.practis.web.selenide.configuration.data.company.NewPractisSetInputData.getNewPractisSetInput;
import static com.practis.web.selenide.configuration.data.company.NewScenarioInputData.getNewScenarioInput;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.validator.company.PractisSetValidator.assertCreatedPractisSet;
import static com.practis.web.selenide.validator.company.PractisSetValidator.assertElementsEditPractisSet;
import static com.practis.web.selenide.validator.company.PractisSetValidator.assertElementsViewPractisSet;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;

import com.practis.dto.NewChallengeInput;
import com.practis.dto.NewPractisSetInput;
import com.practis.dto.NewScenarioInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.library.RestPractisSetResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
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
  @PractisSetExtension
  void checkElementsViewPractisSet(final NewPractisSetInput practisSet) {
    //open Library: Pracis Set tab
    open(webApplicationConfig().getUrl() + "/library/practis-sets");

    assertCreatedPractisSet(practisSet);
    //final var practisSetGridRow = practisSetService().searchPS(practisSet.getTitle());

    //awaitFullPageLoad(10);
    //practisSetGridRow.click();

    //assertElementsViewPractisSet();
    //practisSetEditPage().getScenarioTab().click();
    //practisSetEditPage().getEditButton().click();
    //areYouSurePopUp().getConfirmButton().click();
    //assertElementsEditPractisSet();
  }

}
