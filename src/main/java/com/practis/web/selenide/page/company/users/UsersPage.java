package com.practis.web.selenide.page.company.users;

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
    private final ElementsCollection threeDotMenu = $$("div[data-test='list-item-menu-button']");

    private final SelenideElement noUsersFoundIcon = $(".sc-fkqjzy.gTwUsI");
    private final SelenideElement noUsersFoundText = $(".sc-gdvdet.chqfSt");

    // Drafts list
    private final SelenideElement noDraftYetIcon = $(".sc-fkqjzy.gTwUsI");
    private final SelenideElement noDraftYetText = $(".sc-gdvdet.chqfSt");

    // Filters on Registered and Pending Users tab
    private final ElementsCollection filterTitles = $$(".sc-jhBiJz.dkXFNO");
    private final ElementsCollection rolesCheckboxes = $$(".sc-xiKGw.hHpOfl");
    private final ElementsCollection rolesItems = $$(".sc-bilypg.ivMOPX");

    private final SelenideElement teamsSearchField = $("input[data-test='teams-searchbox-field']");
    private final SelenideElement teamsSearchFieldIcon =
            $("div[data-test='teams-searchbox-field-icon']");
    private final SelenideElement teamsSearchCrossButton =
            $("div[data-test='teams-searchbox-field-clear']");
    private final SelenideElement teamsSelectionText =
            $("span[data-test='teams-selected-caption']");
    private final SelenideElement teamsSelectAllButton = $("span[data-test='select-all-button']");
    private final ElementsCollection teamsItemCheckbox =
            $$("div[data-test='team-item-checkbox-view']");
    private final ElementsCollection teamsItemTitle = $$("div[data-test='team-item-title']");

    private final SelenideElement labelsSearchField =
            $("input[data-test='labels-searchbox-field']");
    private final SelenideElement labelsSearchFieldIcon =
            $("div[data-test='labels-searchbox-field-icon']");
    private final SelenideElement labelsSearchCrossButton =
            $("div[data-test='labels-searchbox-field-clear']");
    private final SelenideElement labelsEmptyStateIcon =
            $("div[data-test='labels-searchbox-no-items-icon']");
    private final SelenideElement labelsEmptyStateText =
            $("div[data-test='labels-searchbox-no-items-text']");

    private final SelenideElement invitedBySearchField = $("input[data-test='table-search-input']");
    private final SelenideElement invitedBySearchFieldIcon =
            $("div[data-test='table-search-input-icon']");
    private final SelenideElement invitedBySearchCrossButton =
            $("div[data-test='table-search-input-clear']");
    private final SelenideElement inviteSelectionText = $(".sc-jTeThh.fmBaYH");
    private final SelenideElement draftsNoUsersSelectedText = $(".sc-fLGwsD.dWKfbh");

    private final SelenideElement inviteSelectAllButton = $(".sc-bLenIk.dZzLeu");
    private final SelenideElement createdBySelectAllButton = $(".sc-hQqLSM.dtolxn");
    private final ElementsCollection createdByItem = $$(".sc-fzrPTx.eXKng");
    private final ElementsCollection inviteItemCheckbox = $$(".sc-kYHenr.hqKtAx");
    private final ElementsCollection inviteItem = $$(".sc-egXhLr.cxzrUS");

    private final SelenideElement selectedCountText = $(".sc-jrFDZH.dJmKGU");
    private final SelenideElement pendingSelectedCountText = $(".sc-hyxYSr.jfeinZ");
    private final SelenideElement clearButton = $(".sc-iAKVOt.cnzggA.inverse");
    private final SelenideElement pendingClearButton = $(".sc-iAKVOt.bwFkCh.inverse");
    private final SelenideElement applyButton = $(".sc-iAKVOt.bwFkCh.primary");

    // Users - Registered, single actions
    private final SelenideElement viewProfileAction = $("div[data-test='view-profile-action']");
    private final SelenideElement userSettingsAction = $("div[data-test='user-settings-action']");
    private final SelenideElement assignPractisSetsAction =
            $("div[data-test='assign-practis-sets-action']");
    private final SelenideElement assignLabelsAction = $("div[data-test='assign-labels-action']");
    private final SelenideElement nudgeUsersAction = $("div[data-test='nudge-user-action']");
    private final SelenideElement exportReportAction = $("div[data-test='export-report-action']");
    private final SelenideElement deleteUserAction = $("div[data-test='delete-user-action']");

    private final SelenideElement userRow = $("tr[data-test='table-row']");
}
