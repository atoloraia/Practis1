package com.practis.web.selenide.page.company.users;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UsersPendingTab {

    // Columns
    private final SelenideElement userColumn = $("th[data-test='name-column']");
    private final SelenideElement emailColumn = $("th[data-test='email-column']");
    private final SelenideElement roleColumn = $("th[data-test='role-column']");
    private final SelenideElement invitedByColumn = $("th[data-test='inviter-column']");
    private final SelenideElement invitedOnColumn = $("th[data-test='invitation-date-column']");

    private final SelenideElement labelsIcon = $("div[data-test='table-labels']");

    private final SelenideElement noUsersFoundIcon = $("div[data-test='no-found-results-icon']");
    private final SelenideElement noUsersFoundText = $("div[data-test='no-found-results-label']");

    private final SelenideElement noPendingUserIcon = $("div[data-test='no-results-icon']");
    private final SelenideElement noPendingUserText = $("div[data-test='no-results-label']");

    private final SelenideElement noFilteredResultsIcon =
            $("div[data-test='no-filtered-results-icon']");
    private final SelenideElement noFilteredResultsText =
            $("div[data-test='no-filtered-results-label']");

    // Filters by Role and Labels
    private final ElementsCollection filterTitles = $$(".sc-jhBiJz.dkXFNO");
    private final ElementsCollection rolesCheckboxes = $$(".sc-xiKGw.hHpOfl");
    private final ElementsCollection rolesItems = $$(".sc-bilypg.ivMOPX");

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

    // Users - Pending, single and bulk actions
    private final SelenideElement viewProfileAction = $("div[data-test='view-profile-action']");
    private final SelenideElement assignLabelsAction = $("div[data-test='assign-labels-action']");
    private final SelenideElement resendInviteAction = $("div[data-test='resend-invite-action']");
    private final SelenideElement copyInviteTextAction =
            $("div[data-test='copy-invite-text-action']");
    private final SelenideElement revokeAction = $("div[data-test='revoke-action']");

    // Bulk actions
    private final SelenideElement assignLabelsBulkAction =
            $("div[data-test='assign-labels-table-action']");
    private final SelenideElement resendInviteBulkAction =
            $("div[data-test='resend-invites-table-action']");
    private final SelenideElement revokeBulkAction = $("div[data-test='revoke-table-action']");
}
