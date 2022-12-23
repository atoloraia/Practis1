package com.practis.web.selenide.page.company.team;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class TrainingTab {

    // Training columns
    private final SelenideElement trainingTabSelectAllCheckbox = $(".sc-xiKGw.irzXNt");
    private final SelenideElement trainingPractisSetColumn =
            $("th[data-test='practis-sets-column']");
    private final SelenideElement trainingOverdueColumn = $("th[data-test='overdue-column']");
    private final SelenideElement trainingTeamMemberStatusColumns =
            $("th[data-test='team-member-status-column']");
    private final SelenideElement trainingNotStartedColumn =
            $("th[data-test='not-started-column']");
    private final SelenideElement trainingInProgressColumn =
            $("th[data-test='in-progress-column']");
    private final SelenideElement trainingCompletedColumn = $("th[data-test='completed-column']");
    private final SelenideElement trainingLastTrainingColumn =
            $("th[data-test='last-training-column']");
    // Training row values
    private final ElementsCollection teamRow = $$("tr[data-test='table-row']");
    private final SelenideElement practisSetValue = $(".sc-eVebrU.jhEVmF");

    private final ElementsCollection trainingLabelIcon = $$("div[data-test='table-labels']");
    // Training 3-dot menu
    private final SelenideElement trainingThreeDotMenu = $(".action-button-element");
    private final SelenideElement trainingViewProgressOption =
            $("div[data-test='view-progress-action-item']");
    private final SelenideElement trainingAssignUsersOption =
            $("div[data-test='assign-users-action-item']");
    private final SelenideElement trainingExportActionOption =
            $("div[data-test='export-report-action-item']");
    private final SelenideElement trainingEditPractisSetOption =
            $("div[data-test='edit-practisset-action-item']");
    // Bulk Action
    private final SelenideElement bulkActionButton = $(".sc-ioVvXZ.iAnXlo");
    private final SelenideElement bulkActionExportReport = $(".sc-PvhCy.hNVaLs");
    // Training empty values
    private final SelenideElement noTrainingIcon = $("div[data-test='no-trainings-yet-icon']");
    private final SelenideElement noTrainingText = $("div[data-test='no-trainings-yet-label']");
    private final SelenideElement trainingNoSearchResultsIcon =
            $("div[data-test='trainings-icon']");
    private final SelenideElement trainingNoSearchResultsText =
            $("div[data-test='trainings-label']");
    private final SelenideElement trainingNoFilterResultsIcon =
            $("div[data-test='no-filtering-criteria-icon']");
    private final SelenideElement trainingNoFilterResultsText =
            $("div[data-test='no-filtering-criteria-label']");

    // Search field
    private final SelenideElement trainingSearchField = $("input[data-test='table-search-input']");
    private final SelenideElement trainingSearchFieldIcon =
            $("div[data-test='table-search-input-icon']");
    private final SelenideElement trainingSearchFieldCrossButton =
            $("div[data-test='table-search-input-clear']");
    private final SelenideElement itemsCounterText = $("div[data-test='table-paging-counter']");
    private final SelenideElement trainingFiltersButton =
            $("button[data-test='team-filters-button']");
}
