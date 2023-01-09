package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AssignUsersAndDueDatesModule {

    private final SelenideElement assignUsersAndDueDatesTitle = $(".sc-dhCIYQ.jVzYW");
    private final SelenideElement practisSetSubtitle = $(".sc-gKdcnr.gQTbjx");

    private final SelenideElement updatedTimestampText =
            $("span[data-test='table-timestamp-label']");
    private final SelenideElement updatedTimestampButton =
            $("button[data-test='table-timestamp-refresh']");

    private final SelenideElement searchFieldIcon = $("div[data-test='table-search-input-icon']");
    private final SelenideElement searchField = $("input[data-test='table-search-input']");
    private final ElementsCollection searchFields = $$("input[data-test='table-search-input']");
    private final SelenideElement searchFieldCrossButton =
            $("div[data-test='practisset-searchbox-field-clear']");
    private final SelenideElement searchFieldXButton = $(".sc-pVTma.iwyvnt");
    private final SelenideElement filtersButton = $(".sc-fBgrOm.eMJweq");

    // Users table
    // columns
    private final SelenideElement itemsCounterText = $("p[data-test='table-selected-item']");
    private final SelenideElement selectAllCheckbox = $(".sc-lhMhtZ.ftvXwI");
    private final ElementsCollection columns = $$(".sc-cTgIxk.ckCjnW");
    private final ElementsCollection dueDateColumn = $$(".sc-cTgIxk.fesHYS");
    // rows
    private final ElementsCollection userRow = $$("tr[data-test='table-row']");
    private final ElementsCollection userCheckbox = $$(".sc-lhMhtZ.ftvXwI");
    private final ElementsCollection userLabelRow = $$("div[data-test='table-labels']");
    private final ElementsCollection userNameRow = $$(".sc-bcGzrp.hdFzGI");
    private final ElementsCollection dueDateRow = $$(".sc-LQrcB.hsiNpg");
    private final ElementsCollection registeredOnRow = $$(".sc-bDONLa.gbtPTg.jss8");
    private final ElementsCollection threeDotButton = $$(".sc-hcZayR.ljVdd");

    private final SelenideElement noUserIcon = $(".sc-fkqjzy.gTwUsI");
    private final SelenideElement noUserText = $(".sc-gdvdet.chqfSt");
    private final SelenideElement noUserFoundIcon =
            $("div[data-test='practisset-searchbox-empty-result-icon']");
    private final SelenideElement noUserFoundText =
            $("div[data-test='practisset-searchbox-empty-result-label']");

    private final SelenideElement noUsersFoundIcon = $(".sc-fkqjzy.gTwUsI");
    private final SelenideElement noUsersFoundText = $(".sc-gdvdet.chqfSt");

    private final SelenideElement assignSelectedUsersButton = $(".sc-iAKVOt.hhZwJC.primary");
}
