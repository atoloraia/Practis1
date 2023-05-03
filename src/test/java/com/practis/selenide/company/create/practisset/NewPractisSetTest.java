package com.practis.selenide.company.create.practisset;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.newItemSelector;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetCreatePage;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.assignUserModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.createPractisSetService;
import static com.practis.web.selenide.configuration.data.company.NewPractisSetInputData.getNewPractisSetInput;
import static com.practis.web.selenide.validator.company.CreatePractisSetValidator.assertCreatedPractisSet;
import static com.practis.web.selenide.validator.company.CreatePractisSetValidator.assertElementsNewPractisSet;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static com.practis.web.util.AwaitUtils.awaitElementNotExists;
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
import com.practis.support.extension.practis.ScenarioExtension;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class NewPractisSetTest {

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

    /** Practis Set: Check WEB Elements 'Add New Practis Set' page. */
    @TestRailTest(caseId = 5309)
    @DisplayName("Practis Set: Create: Check Elements")
    void checkElementsNewPs() {
        assertElementsNewPractisSet();
    }

    /** Create Practis Set. */
    @TestRailTest(caseId = 59)
    @DisplayName("Practis Set: Create")
    @ScenarioExtension(count = 1)
    @ChallengeExtension(count = 1)
    @LabelExtension(count = 1)
    void publishPractisSet(
            final List<RestCreateLabelResponse> label,
            final List<RestScenarioResponse> scenarios,
            List<RestChallengeResponse> challenge) {

        Selenide.refresh();
        await().pollDelay(FIVE_SECONDS).until(() -> true);

        // Create PS
        createPractisSetService()
                .createPractisSet(
                        inputData,
                        label.get(0).getName(),
                        scenarios.get(0).getTitle(),
                        challenge.get(0).getTitle());

        createPractisSetService().publishPractisSet();
        createPractisSetService().confirmPublish();

        // Check snackbar message "Practis Set Published"
        snackbar().getMessage().shouldBe(exactText("Practis Set Published"));

        // CLick Cancel on "Assign Users and Due Dates" modal
        assignUserModuleService().cancel();

        // assert created PS
        assertCreatedPractisSet(inputData);
    }

    /** Practis Set: Save As Draft. */
    @TestRailTest(caseId = 60)
    @DisplayName("Practis Set: Save As Draft")
    @ScenarioExtension(count = 1)
    @ChallengeExtension(count = 1)
    @LabelExtension(count = 1)
    void saveAsDraftPractisSet(
            final List<RestCreateLabelResponse> label,
            final List<RestScenarioResponse> scenarios,
            List<RestChallengeResponse> challenge) {

        Selenide.refresh();
        await().pollDelay(FIVE_SECONDS).until(() -> true);

        // Save as Draft Practis Set
        createPractisSetService()
                .createPractisSet(
                        inputData,
                        label.get(0).getName(),
                        scenarios.get(0).getTitle(),
                        challenge.get(0).getTitle());
        awaitElementNotExists(10, () -> snackbar().getMessage());
        createPractisSetService().saveAsDraftPractisSet();

        // Check snackbar message "Practis Set Saved as Draft"
        awaitElementExists(10, () -> snackbar().getMessage())
                .shouldBe(exactText("Practis Set Saved as Draft"));

        // assert created PS
        assertCreatedPractisSet(inputData);
    }

    /** Create Practis Set: Discard Changes pop-up. */
    @TestRailTest(caseId = 62)
    @DisplayName("Practis Set: Create : Discard Changes pop-up")
    void discardChangesPractisSet() {
        // discard changes
        createPractisSetService().fillTitle(inputData);
        createPractisSetService().exitPractisSetWithDiscard();

        // save changes
        newItemSelector().create("Practis Set");

        createPractisSetService().fillTitle(inputData);
        awaitElementExists(10, () -> practisSetCreatePage().getPacingDropdown())
                .shouldBe(exactText("Free-Form"));
        createPractisSetService().exitPractisSetWithSave();

        // Check snackbar message "Practis Set Published"
        snackbar().getMessage().shouldBe(exactText("Practis Set Saved as Draft"));

        // assert created PS
        assertCreatedPractisSet(inputData);
    }

    /** Create Practis Set: Discard Changes pop-up. */
    @TestRailTest(caseId = 63)
    @ScenarioExtension(count = 1)
    @DisplayName("Practis set: Create: Validation: Required fields")
    void validationMessagesPractisSet(List<RestScenarioResponse> scenario) {
        // publish without any data
        createPractisSetService().publishPractisSet();
        createPractisSetService().confirmPublish();

        // Check snackbar message "Title required"
        snackbar().getMessage().shouldBe(exactText("Title required"));

        // fill title and publish
        createPractisSetService().fillTitle(inputData);
        createPractisSetService().publishPractisSet();
        createPractisSetService().confirmPublish();

        // Check snackbar message "Practis Set Published"
        snackbar()
                .getMessage()
                .shouldBe(exactText("Active practis set should have at least one " + "scenario"));

        createPractisSetService().addScenario(scenario.get(0).getTitle());

        createPractisSetService().publishPractisSet();
        createPractisSetService().confirmPublish();

        // Check snackbar message "Practis Set Published"
        snackbar().getMessage().shouldBe(exactText("Practis Set Published"));

        // CLick Cancel on "Assign Users and Due Dates" modal
        assignUserModuleService().cancel();

        // assert created PS
        assertCreatedPractisSet(inputData);
    }

    @AfterEach
    void cleanup() {
        practisSetsToRemove.forEach(challenge -> practisApi().deletePractisSet(challenge));
    }
}
