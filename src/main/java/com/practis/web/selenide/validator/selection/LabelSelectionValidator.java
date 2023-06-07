package com.practis.web.selenide.validator.selection;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.checked;
import static com.codeborne.selenide.Condition.cssClass;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.util.AwaitUtils.awaitElementExists;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;

public class LabelSelectionValidator {

    /** Assert search on Label model. */
    public static void assertSearchElementsOnLabelsModal() {
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        labelModule().getSearchField().shouldBe(visible);
        labelModule().getSearchField().shouldBe(attribute("font-size", "13px"));
        labelModule().getSearchField().shouldBe(enabled);
        labelModule().getSearchField().shouldBe(attribute("type", "text"));
        labelModule().getSearchFieldIcon().shouldBe(visible);
        labelModule().getCleanSearchIcon().shouldNotBe(visible);
    }

    /** Assert no search results. */
    public static void assertNoLabelSearchResult() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        labelModule().getNoSearchResultText().shouldBe(visible);
        labelModule().getNoSearchResultImage().shouldBe(visible);
        labelModule().getSelectedText().shouldBe(visible);
        // TODO clarify Selected/Unselected state
        // labelModule().getSelectedAllButton().shouldBe(visible);
        labelModule().getLabelRows().shouldBe(CollectionCondition.size(0));
    }

    /** Assert search results. */
    public static void assertLabelSearchResult(final String label) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        labelModuleService().findLabelCheckbox(label).shouldBe(visible);
        labelModule().getLabelRows().shouldBe(CollectionCondition.size(1));
    }

    /** Assert Search should be performed after entering 1 characters. */
    public static void assertLabelSearchAfter1Char(final String searchString) {
        final var input = searchString.charAt(searchString.length() - 1);
        labelModule().getSearchField().append(String.valueOf(input));
        labelModule().getCleanSearchIcon().shouldBe(Condition.visible);
        labelModule().getLabelRows().get(0).shouldBe(visible);
    }

    /** Assert clean search on Label model. */
    public static void assertCleanLabelSearch(int row) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        labelModule().getCleanSearchIcon().shouldNotBe(visible);
        labelModule().getLabelRows().shouldHave(CollectionCondition.size(row));
        labelModule().getSearchField().append("check clean icon");
        labelModule().getCleanSearchIcon().shouldBe(visible);
        labelModule().getLabelRows().shouldHave(CollectionCondition.size(0));
        labelModule().getCleanSearchIcon().click();
        labelModule().getCleanSearchIcon().shouldNotBe(visible);
        labelModule().getLabelRows().shouldHave(CollectionCondition.size(row));
    }

    /** Assert empty Label model. */
    public static void assertEmptyLabelModel() {
        labelModule().getLabelsTitle().shouldBe(exactText("Labels"));
        labelModule().getLabelRows().shouldBe(CollectionCondition.size(0));
        labelModule().getSearchField().shouldBe(visible);
        labelModule().getSearchField().shouldBe(attribute("font-size", "13px"));
        labelModule().getSearchField().shouldBe(attribute("disabled", "true"));
        labelModule().getSearchField().shouldBe(attribute("type", "text"));
        labelModule().getSearchFieldIcon().shouldBe(visible);
        labelModule().getNoLabelsAddedIcon().shouldBe(visible);
        labelModule().getNoLabelsAddedText().shouldBe(exactText("No Labels yet"));
    }

    /** Assert Select All button. */
    public static void assertSelectAllLabelButton() {
        labelModule().getSelectedAllButton().shouldBe(exactText("Select All"));
        labelModule().getSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));
    }

    /** Assert Unselect All button. */
    public static void assertUnSelectAllLabelButton() {
        labelModule().getUnSelectedAllButton().shouldBe(exactText("Unselect All"));
        labelModule().getUnSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));
    }

    /** Assert Select All. */
    public static void assertSelectedAllStateLabels() {
        labelModule()
                .getLabelCheckbox()
                .shouldBe(
                        CollectionCondition.allMatch(
                                "checked", element -> Selenide.$(element).isSelected()));
        labelModule().getSelectedText().shouldBe(matchText(" selected"));
        assertUnSelectAllLabelButton();
    }

    /** Assert Unselect All. */
    public static void assertUnSelectAllStateLabels() {
        labelModule()
                .getLabelCheckbox()
                .should(
                        CollectionCondition.allMatch(
                                "checked",
                                element ->
                                        !Selenide.$(element).has(Condition.attribute("checked"))));
        labelModule().getSelectedText().shouldBe(exactText("No Labels selected"));
        assertSelectAllLabelButton();
    }

    /** Assert the Label is selected. */
    public static void assertSelectedLabel(final String label) {
        awaitElementExists(10, () -> labelModule().getLabelNameRows().get(0));
        labelModuleService().findLabelCheckbox(label).shouldBe(visible);
        labelModuleService().findSelectedLabelCheckboxView(label).shouldBe(enabled);
    }
    /** Assert the Label is selected on Scenario page. */
    public static void assertSelectedLabelOnScenario(final String label) {
        awaitElementExists(10, () -> labelModule().getLabelNameRows().get(0));
        labelModuleService().findLabelCheckboxOnScenario(label).shouldBe(visible);
    }

    /** Assert the Label is selected on Challenge page. */
    public static void assertSelectedLabelOnChallenge(final String label) {
        awaitElementExists(10, () -> labelModule().getLabelItemPractisSet().get(0));
        labelModuleService().findLabelCheckboxOnChallenge(label).shouldBe(checked);
    }

    /** Assert the Label is selected on Practis Set page. */
    public static void assertSelectedLabelOnPractisSet(final String label) {
        awaitElementExists(10, () -> labelModule().getLabelItemChallenge().get(0));
        labelModuleService().findLabelCheckboxOnPractisSet(label).shouldBe(enabled);
    }

    /** Assert the Label is selected. */
    public static void assertPartiallySelectedLabel(final String label) {
        labelModuleService().findLabelCheckbox(label).shouldBe(visible);
        final var checkbox = labelModuleService().findSelectedLabelCheckboxView(label);
        checkbox.shouldHave(cssClass("gEhwjD"));
    }

    /** Assert the Label is unselected. */
    public static void assertUnselectedLabel(final String label) {
        labelModuleService().findLabelCheckbox(label).shouldBe(visible);
        labelModuleService()
                .findLabelCheckbox(label)
                .shouldNotHave(Condition.attribute(".sc-hJhJlY.gyEmir"));
    }

    /** Assert created label. */
    public static void assertOneLabel(final String label) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        labelModule().getLabelNameRows().get(0).shouldBe(visible);
        labelModuleService().findLabelCheckbox(label).shouldBe(visible);
        labelModule().getLabelRows().shouldBe(CollectionCondition.size(1));
    }

    /** Assert the Label is selected. */
    public static void assertLabelCounter(String counter) {
        labelModule().getSelectedText().shouldBe(visible);
        labelModule().getSelectedText().shouldBe(matchText(counter));
    }

    /** Assert Apply button. */
    public static void assertDisabledApplyLabelButton() {
        labelModule().getApplyButton().shouldBe(disabled);
        labelModule().getApplyButton().shouldBe(visible);
        labelModule().getApplyButton().shouldBe(exactText("Apply"));
    }

    /** Assert Cancel button. */
    public static void assertCancelLabelButton() {
        labelModule().getCancelButton().shouldBe(visible);
        labelModule().getCancelButton().shouldBe(exactText("Cancel"));
    }

    /** Assert No Labels Yet. */
    public static void assertNoLabelsYet() {
        labelModule().getNoLabelsYetTooltip().shouldBe(visible);
        labelModule().getNoLabelsYetTooltip().shouldBe(exactText("No labels added yet"));
    }

    /** Assert WEB elements on Label dropdown. */
    public static void assertElementsOnLabelSection() {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        assertSearchElementsOnLabelsModal();
        labelModule().getSelectedText().shouldBe(visible);
        labelModule().getSelectedText().shouldBe(exactText("No Labels selected"));
        assertSelectAllLabelButton();
        labelModule().getLabelNameRows().shouldBe(CollectionCondition.size(1));
        labelModule().getLabelCheckbox().shouldBe(CollectionCondition.size(1));
        labelModule().getLabelNameRows().shouldBe(CollectionCondition.size(1));
    }

    /** Assert WEB elements on Label dropdown. */
    public static void assertCancelApplyButtonsBulkAction() {
        labelModule().getApplyButton().shouldBe(visible);
        labelModule().getApplyButton().shouldBe(disabled);
        labelModule().getCancelButton().shouldBe(visible);
        labelModule().getCancelButton().shouldBe(enabled);
    }

    /** Assert WEB elements on Label dropdown. */
    public static void assertCancelApplyButtonsSingleAction() {
        labelModule().getApplyButton().shouldBe(visible);
        labelModule().getApplyButton().shouldBe(disabled);
        labelModule().getCancelButton().shouldBe(visible);
        labelModule().getCancelButton().shouldBe(enabled);
    }

    /** Assert Labels modal */
    public static void assertLabelsModal() {
        labelModule().getSearchField().shouldBe(visible);
        labelModule().getSearchFieldIcon().shouldBe(visible);
        labelModule().getCleanSearchIcon().shouldBe(hidden);
        labelModule().getNoLabelsSelectedText().shouldBe(visible);
        labelModule().getLabelRows().shouldBe(CollectionCondition.size(1));
        labelModule().getSelectedAllButton().shouldBe(exactText("Select All"));
        labelModule().getSelectedAllButton().shouldBe(attribute("color", "#4aa9e2"));
    }
}
