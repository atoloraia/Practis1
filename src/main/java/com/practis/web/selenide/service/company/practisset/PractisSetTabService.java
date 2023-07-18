package com.practis.web.selenide.service.company.practisset;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.filter;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.grid;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.labelModule;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.search;
import static com.practis.web.selenide.configuration.PageObjectFactory.libraryPage;
import static com.practis.web.selenide.configuration.PageObjectFactory.practisSetTab;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.labelModuleService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.statusModuleService;
import static com.practis.web.util.AwaitUtils.awaitGridRowExists;
import static com.practis.web.util.SelenideJsUtils.jsClick;
import static java.lang.String.format;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.FIVE_SECONDS;
import static org.awaitility.Duration.TWO_SECONDS;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.practis.web.selenide.component.GridRow;

public class PractisSetTabService {

    private static final String DUPLICATED_TEMPLATE =
            "\\[[0-9]{2}\\/[0-9]{2}\\/[0-9]{4}\\s[0-9]{1,2}:[0-9]{2}:[0-9]{2}\\s(AM|PM)\\]-%s";
    private static final String ORIGINAL_TEMPLATE = "^(%s).*";

    /** Select All on Practis Set Tab. */
    public void selectAllPractisSets() {
        practisSetTab().getPractisSetsSelectAllCheckbox().click();
    }

    /** Assign Label to Practis Set Tab. */
    public void assignLabelToPractisSets(String label) {
        practisSetTab().getActionButton().parent().click();
        practisSetTab().getAssignLabelsBulkAction().click();
        labelModuleService().selectLabel(label);
        labelModule().getApplyButton().click();
    }

    /** Find team labels. */
    public SelenideElement findPsLabelCounter(final String practisSet) {
        final var practisSetRow =
                practisSetTab().getPractisSetRow().find(Condition.matchText(practisSet));
        return practisSetRow.$("[data-test='library-practis-sets-item-labels']");
    }

    /** Filter by archived Practis Sets. */
    public void filterByArchivedPs() {
        libraryPage().getFiltersButton().click();
        await().pollDelay(FIVE_SECONDS).until(() -> true);
        filter().getLibraryClearButton().click();
        statusModuleService().selectArchivedStatus();
        jsClick(filter().getApplyLibraryFilterButton());
    }

    /** Filter by archived Practis Sets. */
    public void filterByDraftPs() {
        libraryPage().getFiltersButton().click();
        filter().getLibraryClearButton().click();
        statusModuleService().selectDraftStatus();
        jsClick(filter().getApplyLibraryFilterButton());
    }

    // Single Actions

    /** Click 3-dot menu for the Practis Set. */
    public void clickSingleAction(final String practisSet) {
        final var practisSetRow =
                practisSetTab().getPractisSetRow().find(Condition.matchText(practisSet));
        practisSetRow.$("div[data-test='library-practis-sets-item-menu-button']").click();
    }

    /** Click on 3-dot menu for the Practis Set. */
    public void clickViewSingleAction() {
        practisSetTab().getViewSingleAction().click();
    }

    /** Click 'Assign Labels' on 3-dot menu for the team. */
    public void clickAssignLabelsSingleAction() {
        practisSetTab().getAssignLabelsSingleAction().click();
    }

    /** Click 'Duplicate' on 3-dot menu for the team. */
    public void clickDuplicateSingleAction() {
        practisSetTab().getDuplicateSingleAction().click();
    }

    /** Click 'Delete' on 3-dot menu for the team. */
    public void clickArchiveSingleAction() {
        practisSetTab().getArchiveSingleAction().click();
    }

    /** Click 'Restore' on 3-dot menu for the team. */
    public void clickRestoreSingleAction() {
        jsClick(practisSetTab().getRestoreSingleAction());
    }

    /** Click 'Delete' on 3-dot menu for the team. */
    public void clickDeleteSingleAction() {
        practisSetTab().getDeleteSingleAction().click();
    }

    public GridRow getOriginalPs(final String name) {
        return grid().getRow(format(ORIGINAL_TEMPLATE, name));
    }

    public GridRow getDuplicatedPs(final String name) {
        return grid().getRow(format(DUPLICATED_TEMPLATE, name));
    }

    /** Find Status in the row. */
    public SelenideElement findStatus(final String practisSet) {
        final var practisSetRow =
                practisSetTab().getPractisSetRow().find(Condition.matchText(practisSet));
        return practisSetRow.$("[data-test='library-practis-sets-item-status']");
    }

    /** Search Practis Set on grid by Name. */
    public GridRow searchPS(final String name) {
        await().pollDelay(TWO_SECONDS).until(() -> true);
        search().search(name);
        return awaitGridRowExists(5, () -> grid().getRow(name));
    }
}
