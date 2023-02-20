package com.practis.web.selenide.page.company.scenario;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ScenarioTab {

    private final SelenideElement selectAllCheckbox =
            $("input[data-test='library-scenarios-master-checkbox-input-partially-checked']");
    private final SelenideElement scenariosColumn =
            $("th[data-test='library-scenarios-title-column']");
    private final SelenideElement scenariosStatusColumn =
            $("th[data-test='library-scenarios-status-column']");
    private final SelenideElement scenariosDurationColumn =
            $("th[data-test='library-scenarios-duration-column']");
    private final SelenideElement scenariosLastUpdatedColumn =
            $("th[data-test='library-scenarios-date-column']");

    private final ElementsCollection scenarioRow = $$("tr[data-test='library-scenarios-item']");

    private final SelenideElement noResultMatchFilterCriteriaIcon =
            $("div[data-test='library-no-filtered-scenarios-icon']");
    private final SelenideElement noResultMatchFilterCriteriaText =
            $("div[data-test='library-no-filtered-scenarios-label']");

    // Selection modal - Action
    private final SelenideElement actionButton = $("div[data-test='assign-table-action-label']");
    private final SelenideElement assignLabelsBulkAction =
            $("div[data-test='assign-labels-table-action']");

    // 3-dot menu
    private final SelenideElement singleActionOnPsTab = $("div[data-test='edit-challenge-action']");
    private final SelenideElement editSingleAction = $("div[data-test='edit-challenge-action']");
    private final SelenideElement assignLabelsSingleAction =
            $("div[data-test='assign-labels-challenge-action']");
    private final SelenideElement duplicateSingleAction =
            $("div[data-test='duplicate-challenge-action']");
    private final SelenideElement generateChallengeSingleAction =
            $("div[data-test='generate-challenge-action']");
    private final SelenideElement downloadPDFSingleAction =
            $("div[data-test='download-pdf-challenge-action']");
    private final SelenideElement archiveSingleAction =
            $("div[data-test='archive-challenge-action']");
    private final SelenideElement restoreSingleAction =
            $("div[data-test='restore-challenge-action']");
    private final SelenideElement deleteSingleAction =
            $("div[data-test='delete-challenge-action']");
}