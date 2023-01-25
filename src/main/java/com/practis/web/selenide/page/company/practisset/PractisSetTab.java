package com.practis.web.selenide.page.company.practisset;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PractisSetTab {

    private final SelenideElement practisSetsSelectAllCheckbox =
            $("div[data-test='library-practis-sets-master-checkbox-input-view']");
    private final SelenideElement selectAllCheckbox = $("tr[data-test='table-row']");
    private final SelenideElement practisSetsColumn =
            $("th[data-test='library-practis-sets-title-column']");
    private final SelenideElement practisSetsStatusColumn =
            $("th[data-test='library-practis-sets-status-column']");
    private final SelenideElement contentColumn =
            $("th[data-test='library-practis-sets-content-column']");
    private final SelenideElement practisSetsLastUpdatedColumn =
            $("th[data-test='library-practis-sets-date-column']");

    private final ElementsCollection practisSetRow =
            $$("tr[data-test='library-practis-sets-item']");

    private final SelenideElement emptyIconPsTab =
            $("div[data-test='library-no-filtered-practis-sets-icon']");
    private final SelenideElement emptyTextPsTab =
            $("div[data-test='library-no-filtered-practis-sets-label']");

    // Selection modal - Action
    private final SelenideElement actionButton = $(".sc-iyXEbO.iOipoT");
    private final SelenideElement assignLabelsBulkAction = $(".sc-gONa-Ds.bISsNL");

    // 3-dot menu
    private final SelenideElement singleActionOnPsTab =
            $("div[data-test='library-practis-sets-item-menu-button']");
    private final SelenideElement editSingleAction = $("div[data-test='edit-practis-set-action']");
    private final SelenideElement assignUsersSingleAction =
            $("div[data-test='assign-users-practis-set-action']");
    private final SelenideElement assignLabelsSingleAction =
            $("div[data-test='assign-labels-practis-set-action']");
    private final SelenideElement duplicateSingleAction =
            $("div[data-test='duplicate-practis-set-action']");
    private final SelenideElement archiveSingleAction =
            $("div[data-test='archive-practis-set-action']");
}
