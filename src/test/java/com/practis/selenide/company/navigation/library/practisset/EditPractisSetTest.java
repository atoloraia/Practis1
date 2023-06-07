package com.practis.selenide.company.navigation.library.practisset;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.areYouSurePopUp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.assignUsersAndDueDatesModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetEditPage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.createPractisSetService;
import static com.practis.web.selenide.configuration.data.company.NewPractisSetInputData.getNewPractisSetInput;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.validator.company.CreatePractisSetValidator.assertCreatedPractisSet;
import static com.practis.web.selenide.validator.company.CreatePractisSetValidator.assertEditedPractisSetData;
import static com.practis.web.selenide.validator.company.CreatePractisSetValidator.assertElementsEditPractisSet;
import static com.practis.web.selenide.validator.company.CreatePractisSetValidator.assertElementsViewPractisSet;
import static com.practis.web.util.AwaitUtils.awaitSoft;
import static com.practis.web.util.SelenidePageUtil.openPage;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;

import com.codeborne.selenide.Selenide;
import com.practis.dto.NewPractisSetInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import com.practis.rest.dto.company.library.RestChallengeResponse;
import com.practis.rest.dto.company.library.RestScenarioResponse;
import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import com.practis.support.extension.practis.ChallengeExtension;
import com.practis.support.extension.practis.LabelExtension;
import com.practis.support.extension.practis.PractisSetExtension;
import com.practis.support.extension.practis.ScenarioExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class EditPractisSetTest {

    private NewPractisSetInput inputData;
    private List<String> practisSetsToRemove;

    @BeforeEach
    void init() {
        newItemSelector().create("Practis Set");

        inputData = getNewPractisSetInput();
        inputData.setName(String.format(inputData.getName(), timestamp()));

        practisSetsToRemove = new ArrayList<>();
        practisSetsToRemove.add(inputData.getName());
    }

    /** Create Practis Set. */
    @TestRailTest(caseId = 8789)
    @DisplayName("Practis Set: View: Check Elements")
    @PractisSetExtension(count = 1)
    void checkElementsViewPractisSet(final List<NewPractisSetInput> practisSets) {
        // open Library: Practis Set tab
        openPage(webApplicationConfig().getUrl() + "/library/practis-sets");

        assertCreatedPractisSet(practisSets.get(0));
        assertElementsViewPractisSet();
        practisSetEditPage().getScenarioTab().click();
        practisSetEditPage().getEditButton().click();
        areYouSurePopUp().getConfirmButton().click();
        assertElementsEditPractisSet();
    }

    /** Practis Set: Edit Practis Set. */
    @TestRailTest(caseId = 31734)
    @DisplayName("Library: Practis Set: Edit Practis Set")
    @ScenarioExtension(count = 2)
    @ChallengeExtension(count = 2)
    @LabelExtension(count = 1)
    void editPractisSet(
            final List<RestCreateLabelResponse> label,
            final List<RestScenarioResponse> scenarios,
            List<RestChallengeResponse> challenge) {
        Selenide.refresh();
        await().pollDelay(FIVE_SECONDS).until(() -> true);

        // Create PS
        createPractisSetService()
                .createPractisSet(
                        inputData, scenarios.get(0).getTitle(), challenge.get(0).getTitle());
        createPractisSetService().publishPractisSet();
        createPractisSetService().confirmPublish();
        assignUsersAndDueDatesModule().getCancelButton().click();

        // Open Practis Set page
        final var practisSetGridRow = createPractisSetService().searchPS(inputData.getName());
        practisSetGridRow.click();
        practisSetEditPage().getEditButton().click();
        areYouSurePopUp().getConfirmButton().click();

        // Edit Practis Set
        createPractisSetService()
                .editPractisSet(
                        label.get(0).getName(),
                        scenarios.get(1).getTitle(),
                        challenge.get(1).getTitle());
        practisSetEditPage().getSaveButton().click();
        awaitSoft(10, () -> !practisSetEditPage().getTitleField().isDisplayed());

        // Assert that changes have been applied
        final var editedPractisSetGridRow = createPractisSetService().searchPS("_edit");
        editedPractisSetGridRow.click();
        practisSetEditPage().getEditButton().click();
        areYouSurePopUp().getConfirmButton().click();

        assertEditedPractisSetData(label, scenarios, challenge);
    }

    @AfterEach
    void cleanup() {
        practisSetsToRemove.forEach(challenge -> practisApi().deletePractisSet(challenge));
    }
}
