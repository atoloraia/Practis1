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

    // No search results
    private final SelenideElement noSearchResultIcon = $("div[data-test='no-found-results-icon']");
    private final SelenideElement noSearchResultText = $("div[data-test='no-found-results-label']");

    // User row
    private final ElementsCollection userRow = $$("tr[data-test='user-item']");
}
