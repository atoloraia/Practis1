package com.practis.web.selenide.page.company.challenge;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ChallengeTab {

    private final SelenideElement selectAllCheckbox =
            $("input[data-test='library-challenges-master-checkbox-input-partially-checked']");
    private final SelenideElement challengeColumn =
            $("th[data-test='library-challenges-title-column']");
    private final SelenideElement challengeStatusColumn =
            $("th[data-test='library-challenges-status-column']");
    private final SelenideElement challengeLastUpdatedColumn =
            $("th[data-test='library-challenges-date-column']");

    private final ElementsCollection challengeRow = $$("tr[data-test='library-challenges-item']");

    private final SelenideElement noResultMatchFilterCriteriaIcon =
            $("div[data-test='library-no-filtered-challenges-icon']");
    private final SelenideElement noResultMatchFilterCriteriaText =
            $("div[data-test='library-no-filtered-challenges-label']");

    private final SelenideElement noScenarioYetIcon =
            $("div[data-test='library-no-challenges-icon']");
    private final SelenideElement noScenarioYetText =
            $("div[data-test='library-no-challenges-label']");

    // Selection modal - Action
    private final SelenideElement actionButton = $("div[data-test='assign-table-action-label']");
    private final SelenideElement assignLabelsBulkAction =
            $("div[data-test='assign-labels-table-action']");

    // 3-dot menu
    private final SelenideElement singleActionOnPsTab =
            $("div[data-test='library-challenges-item-menu-button']");
    private final SelenideElement viewSingleAction = $("div[data-test='edit-challenge-action']");
    private final SelenideElement assignLabelsSingleAction =
            $("div[data-test='assign-labels-challenge-action']");
    private final SelenideElement duplicateSingleAction =
            $("div[data-test='duplicate-challenge-action']");
    private final SelenideElement archiveSingleAction =
            $("div[data-test='archive-challenge-action']");
    private final SelenideElement restoreSingleAction =
            $("div[data-test='restore-challenge-action']");
    private final SelenideElement deleteSingleAction =
            $("div[data-test='delete-challenge-action']");
}
