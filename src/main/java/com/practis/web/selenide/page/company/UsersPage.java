package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UsersPage {

    // Registered list
    private final SelenideElement usersHeader = $(".sc-eZhSfn.ezfhJL");
    private final ElementsCollection selectedTab = $$(".sc-inrCKc.ceaNnH.is-active");
    private final ElementsCollection tabs = $$(".sc-inrCKc.ceaNnH");
    private final SelenideElement updatedTimestampText =
            $("span[data-test='table-timestamp-label']");
    private final SelenideElement updateTimestampButton =
            $("button[data-test='table-timestamp-refresh']");

    private final SelenideElement searchField = $("div[data-test='table-search-input']");
    private final SelenideElement searchFieldIcon = $("div[data-test='table-search-input-icon']");
    private final SelenideElement searchFieldCrossButton =
            $("div[data-test='table-search-input-clear']");
    private final SelenideElement filtersButton = $(".sc-fBgrOm.jHYvKW");
    private final SelenideElement disabledFiltersButton = $(".sc-fBgrOm.eMJweq");
    private final SelenideElement itemsCounterText = $("div[data-test='table-paging-counter']");
    private final SelenideElement previousPageArrow = $("button[data-test='table-paging-prev']");
    private final SelenideElement nextPageArrow = $("button[data-test='table-paging-next']");

    private final SelenideElement selectAllCheckbox = $(".sc-kYHenr.hqKtAx");
    private final ElementsCollection listColumns = $$(".sc-cTgIxk.ckCjnW");
    private final ElementsCollection disabledListColumns = $$(".sc-cTgIxk.fesHYS");
    private final ElementsCollection listValues = $$("td[data-test='table-cell']");
    private final ElementsCollection labelsIcon = $$(".sc-zCnrT.iqQkuC");
    private final SelenideElement threeDotMenu = $(".sc-gXRoDt.iAvKeU");

    private final SelenideElement noUsersFoundIcon = $(".sc-fkqjzy.gTwUsI");
    private final SelenideElement noUsersFoundText = $(".sc-gdvdet.chqfSt");

    // Drafts list
    private final SelenideElement noDraftYetIcon = $(".sc-fkqjzy.gTwUsI");
    private final SelenideElement noDraftYetText = $(".sc-gdvdet.chqfSt");
}
