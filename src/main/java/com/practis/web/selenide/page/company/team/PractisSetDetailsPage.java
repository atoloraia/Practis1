package com.practis.web.selenide.page.company.team;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class PractisSetDetailsPage {

    private final SelenideElement practisSetDetailsTitle = $(".sc-iRFufS.lbIdOH");
    private final SelenideElement practisSetDetailsSubtitle = $(".sc-eZhSfn.ezfhJL");
    private final SelenideElement backArrowButton = $("div[data-test='back-arrow-button']");
    private final ElementsCollection blueActionsIcon = $$(".sc-dNiwAP.iYXiTL");
    private final ElementsCollection blueActionButton = $$(".sc-jPGiPG.beORoh");
    private final SelenideElement updatedTimestampText =
            $("span[data-test='table-timestamp-label']");
    private final SelenideElement updatedTimestampButton =
            $("button[data-test='table-timestamp-refresh']");

    private final SelenideElement searchFieldIcon = $("div[data-test='table-search-input-icon']");
    private final SelenideElement searchField = $("input[data-test='table-search-input']");
    private final SelenideElement searchFieldCrossButton =
            $("div[data-test='table-search-input-clear']");
    private final SelenideElement filtersButton = $(".sc-fBgrOm.jHYvKW");
    private final SelenideElement itemsCounterText = $("div[data-test='table-paging-counter']");
    private final SelenideElement prevPageButton = $("button[data-test='table-paging-prev']");
    private final SelenideElement nextPageButton = $("button[data-test='table-paging-next']");

    private final ElementsCollection tableRow = $$("tr[data-test='table-row']");
    private final SelenideElement selectAllCheckbox = $(".sc-hJhJlY.enxZpD");
    private final ElementsCollection tableColumns = $$(".sc-fKeQbJ.dUIgeN");
    private final ElementsCollection questionMarkButton = $$(".sc-gwNMAO.kWgeGX");
    private final ElementsCollection threeDotButton = $$(".sc-ksHnTl.fOcWiQ.action-button-element");

    // No Search elements
    private final SelenideElement noSearchIcon = $(".sc-fkqjzy.gTwUsI");
    private final SelenideElement noSearchText = $(".sc-gdvdet.chqfSt");

    // No Filter elements
    private final SelenideElement noFilterIcon = $(".sc-fkqjzy.gTwUsI");
    private final SelenideElement noFilterText = $(".sc-gpZsfs.ggTyDf");
    private final SelenideElement filtersBlueDot = $(".sc-fMEUPF.bApCEj");

    // Action Menu
    private final SelenideElement selectedItemsCounter =
            $("span[data-test='table-selected-counter']");
    private final SelenideElement selectAllButton = $("button[data-test='table-select-all']");
    private final SelenideElement selectAllCounter =
            $("span[data-test='table-select-all-counter']");
    private final SelenideElement clearSelectionButton =
            $("button[data-test='table-clear-selection']");
}
