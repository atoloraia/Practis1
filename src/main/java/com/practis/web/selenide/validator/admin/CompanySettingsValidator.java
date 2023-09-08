package com.practis.web.selenide.validator.admin;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.hidden;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;

import com.practis.dto.NewUserInput;
import java.util.List;

public class CompanySettingsValidator {

    /** Assert elements on Company Settings page. */
    public static void assertElementsOnCompanySettingsPage() {
        companySettingsPage().getCompanySettingsTitle().shouldBe(matchText("Company Settings"));
        companySettingsPage().getCompanySettingsTitleCompany().shouldBe(visible);
        companySettingsPage().getCrossButton().shouldBe(visible);

        String companyName = companySettingsPage().getCompanySettingsTitleCompany().text();
        companySettingsPage().getCompanyNameField().shouldBe(visible);
        companySettingsPage().getCompanyNameField().shouldBe(enabled);
        // companySettingsPage().getCompanyNameField().shouldHave(attribute("value", companyName));
        companySettingsPage().getCompanyName().shouldBe(visible);
        companySettingsPage().getCompanyName().shouldBe(exactText("Name"));
        companySettingsPage().getWorkspaceUrl().shouldBe(visible);
        companySettingsPage().getWorkspaceUrl().shouldBe(exactText("Workspace URL"));
        companySettingsPage().getWorkspaceUrlField().shouldBe(visible);
        // companySettingsPage().getWorkspaceUrlField().shouldBe(exactText(companyName +
        // ".gopractis.com"));
        companySettingsPage().getWorkspaceUrlField().shouldBe(disabled);
        companySettingsPage().getAccountOwner().shouldBe(visible);
        companySettingsPage().getAccountOwner().shouldBe(exactText("Account Owner"));
        companySettingsPage().getAccountOwnerField().shouldBe(visible);
        companySettingsPage().getAccountOwnerField().shouldBe(enabled);
        companySettingsPage().getAccountOwnerField().shouldBe(exactText("No Account Owner"));

        companySettingsPage().getSections().get(0).shouldBe(visible);
        companySettingsPage().getSections().get(0).shouldBe(exactText("Details"));
        companySettingsPage().getSections().get(1).shouldBe(visible);
        companySettingsPage().getSections().get(1).shouldBe(exactText("Logo"));
        companySettingsPage().getSections().get(2).shouldBe(visible);
        companySettingsPage().getSections().get(2).shouldBe(exactText("Licensed Seats"));

        companySettingsPage().getApplyButton().shouldBe(visible);
        companySettingsPage().getApplyButton().shouldBe(disabled);
        companySettingsPage().getApplyButton().shouldBe(attribute("color", "default"));
        companySettingsPage().getApplyButton().shouldBe(attribute("type", "submit"));
    }

    /** Assert Active Badge. */
    public static void assertActiveBadge() {
        companySettingsPage().getCompanyActiveBadge().shouldBe(visible);
        companySettingsPage().getCompanyActiveBadge().shouldBe(exactText("Active"));
    }

    /** Assert Inactive Badge. */
    public static void assertInactiveBadge() {
        companySettingsPage().getCompanyInactiveBadge().shouldBe(visible);
        companySettingsPage().getCompanyInactiveBadge().shouldBe(exactText("Inactive"));
    }

    public static void assertElementsOnActiveActionsPage(String status) {

        companySettingsPage().getCompanyActiveBadge().shouldBe(visible);
        companySettingsPage().getCompanyActiveBadge().shouldBe(exactText(status));
        companySettingsPage().getDeactivateButton().shouldBe(visible);
        companySettingsPage().getDeactivateButton().shouldBe(exactText("Deactivate"));
        companySettingsPage().getDeactivateButton().shouldBe(attribute("type", "submit"));
        companySettingsPage().getDeactivateButton().shouldBe(attribute("color", "warning"));
        companySettingsPage().getChangeStatusText().shouldBe(visible);
        companySettingsPage().getChangeStatusText().shouldBe(exactText("Change company status"));
        companySettingsPage().getInfoText().shouldBe(visible);
        companySettingsPage()
                .getInfoText()
                .shouldBe(exactText("All Practis Admins will be informed about this action."));
    }

    public static void assertElementsOnInactiveActionsPage(String status) {

        companySettingsPage().getStatusBadge().shouldBe(visible);
        companySettingsPage().getStatusBadge().shouldBe(exactText(status));
        companySettingsPage().getActivateButton().shouldBe(visible);
        companySettingsPage().getActivateButton().shouldBe(exactText("Activate"));
        companySettingsPage().getActivateButton().shouldBe(attribute("type", "submit"));
        companySettingsPage().getActivateButton().shouldBe(attribute("color", "default"));
        companySettingsPage().getChangeStatusText().shouldBe(visible);
        companySettingsPage().getChangeStatusText().shouldBe(exactText("Change company status"));
        companySettingsPage().getInfoText().shouldBe(visible);
        companySettingsPage()
                .getInfoText()
                .shouldBe(exactText("All Practis Admins will be informed about this action."));
    }

    /** Assert status and action button on Company Settings page. */
    public static void assertStatusChangesCompanySettings(String status, String button) {
        companySettingsPage().getStatusBadge().shouldBe(visible);
        companySettingsPage().getStatusBadge().shouldBe(exactText(status));
        companySettingsPage().getActivationButton().shouldBe(visible);
        companySettingsPage().getActivationButton().shouldBe(exactText(button));
    }

    /** Assert Deactivated Logs. */
    public static void assertDeactivatedLogs(String text) {
        companySettingsPage().getActionsLogs().get(1).shouldBe(visible);
        companySettingsPage().getActionsLogs().get(1).shouldBe(matchText(text));
    }

    /** Assert Activated Logs. */
    public static void assertActivatedLogs(String text) {
        companySettingsPage().getActionsLogs().get(0).shouldBe(visible);
        companySettingsPage().getActionsLogs().get(0).shouldBe(matchText(text));
    }

    /** Assert Limited Users Elements. */
    public static void assertLimitedUsersElement() {
        companySettingsPage()
                .getLimitUsersTitle()
                .shouldBe(exactText("0 of 5 licensed seats have been used"));
        companySettingsPage().getSetLimitCount().shouldBe(exactText("5"));
        companySettingsPage().getTotalSeatsTakenCount().shouldBe(exactText("0"));
        companySettingsPage().getRegisteredCounter().shouldBe(visible);
        companySettingsPage().getRegisteredCounter().shouldBe(matchText("0 Registered"));
        companySettingsPage().getPendingCounter().shouldBe(visible);
        companySettingsPage().getPendingCounter().shouldBe(exactText("0 Pending Registration"));
        companySettingsPage().getDeactivatedCounter().shouldBe(visible);
        companySettingsPage().getDeactivatedCounter().shouldBe(matchText("0 Deactivated"));

        companySettingsPage().getLimitedRadioButton().shouldBe(visible);
        companySettingsPage().getUnlimitedRadioButton().shouldBe(visible);

        companySettingsPage().getUnlimitedUsersTitle().shouldBe(visible);
        companySettingsPage()
                .getUnlimitedUsersTitle()
                .shouldBe(exactText("Unlimited Licensed Seats"));
        companySettingsPage().getUnlimitedUsersDescription().shouldBe(visible);
        companySettingsPage()
                .getUnlimitedUsersDescription()
                .shouldBe(
                        exactText(
                                "Company can have as many users in different roles and statuses as"
                                        + " they wish."));

        companySettingsPage().getLimitUsersTitle().shouldBe(visible);
        companySettingsPage().getLimitedUsersField().shouldBe(visible);
        companySettingsPage().getLimitedUsersField().shouldNotBe(empty);
        companySettingsPage().getLimitedUsersField().shouldNotBe(disabled);
        companySettingsPage().getLimitedUsersText().shouldBe(exactText("Limit licensed seats to"));
        companySettingsPage()
                .getLimitedUsersDescription()
                .shouldBe(
                        exactText(
                                "Company won't be able to have more than this number of seats."
                                        + " Deactivated user seats still count towards the licensed"
                                        + " seat count."));
        companySettingsPage().getLimitedUsersError().shouldBe(hidden);
    }

    /** Assert Users Counter on Users Limit tab. */
    public static void assertUsersCounterUsersLimit() {
        companySettingsPage().getRegisteredCounter().shouldBe(visible);
        companySettingsPage().getRegisteredCounter().shouldBe(matchText("Registered"));
        companySettingsPage().getPendingCounter().shouldBe(visible);
        companySettingsPage().getPendingCounter().shouldBe(matchText("Pending Registration"));
        companySettingsPage().getDeactivatedCounter().shouldBe(visible);
        companySettingsPage().getDeactivatedCounter().shouldBe(matchText("Deactivated"));
    }

    /** Assert Limited Users error. */
    public static void assertLimitedUsersErrorText() {
        companySettingsPage().getLimitedUsersError().shouldBe(visible);
        companySettingsPage()
                .getLimitedUsersError()
                .shouldBe(
                        exactText(
                                "Important: You've set a limit that's lower than the existing"
                                    + " number of licensed user seats in this account. Once you"
                                    + " apply this limit, existing users in this account will be"
                                    + " able to continue using the product, but the account won't"
                                    + " be able to invite new users until a Practis Admin deletes"
                                    + " users below this new limit. Seats can also be freed up by"
                                    + " revoking pending/unaccepted invitations and assigning them"
                                    + " to other users."));
    }

    /** Assert Updated Company name. */
    public static void assertUpdatedName() {
        companySettingsPage().getCompanySettingsTitle().shouldBe(visible);
        companySettingsPage()
                .getCompanySettingsTitle()
                .shouldBe(exactText("Company Settings • CompanyAuto1"));
        companySettingsPage().getCompanyNameInput().shouldBe(attribute("value", "CompanyAuto1"));
    }

    /** Assert Account Owner dropdown. */
    public static void assertAccountOwnerDropdown(final List<NewUserInput> user) {
        companySettingsPage().getAccountOwnerValue().get(0).shouldBe(visible);
        companySettingsPage().getSelectedAccountOwnerValue().shouldBe(visible);
        companySettingsPage()
                .getSelectedAccountOwnerValue()
                .shouldBe(exactText("No Account Owner"));
        companySettingsPage().getAccountOwnerValue().get(1).shouldNot(exist);
        companySettingsPage()
                .getAccountOwnerValue()
                .get(0)
                .shouldBe(matchText(user.get(0).getFirstName()));
        companySettingsPage()
                .getAccountOwnerValue()
                .get(0)
                .shouldBe(matchText(user.get(0).getLastName()));
    }

    /** Assert Account Owner Updated field. */
    public static void assertUpdatedAccountOwnerField(final List<NewUserInput> user) {
        companySettingsPage().getAccountOwnerField().shouldBe(visible);
        companySettingsPage()
                .getAccountOwnerField()
                .shouldBe(matchText(user.get(0).getFirstName()));
        companySettingsPage().getAccountOwnerField().shouldBe(matchText(user.get(0).getLastName()));
    }

    /** Assert No Account Owner. */
    public static void assertNoAccountOwner() {
        companySettingsPage().getAccountOwnerField().shouldBe(visible);
        companySettingsPage().getAccountOwnerField().shouldBe(exactText("No Account Owner"));
    }

    /** Assert Audit Log Default state. */
    public static void assertAuditLogDefaultState() {
        companySettingsPage().getAuditLogItem().get(0).shouldBe(visible);
        companySettingsPage().getAuditLogItem().shouldBe(size(1));
        companySettingsPage()
                .getAuditLogItem()
                .get(0)
                .shouldBe(matchText("Company Account created by Automation User on"));
        companySettingsPage()
                .getAuditLogItem()
                .get(0)
                .shouldBe(
                        matchText(
                                "Limit licensed seats set to: Unlimited. Company Account owner:"
                                        + " none."));
    }

    /** Assert Audit Limit updated to limited state. */
    public static void assertAuditLogLimitUpdatedLimited() {
        companySettingsPage().getAuditLogItem().get(0).shouldBe(visible);
        companySettingsPage().getAuditLogItem().get(1).shouldBe(visible);
        companySettingsPage().getAuditLogItem().shouldBe(size(2));
        companySettingsPage()
                .getAuditLogItem()
                .get(1)
                .shouldBe(matchText("Company Account created by Automation User on"));
        companySettingsPage()
                .getAuditLogItem()
                .get(1)
                .shouldBe(
                        matchText(
                                "Limit licensed seats set to: Unlimited. Company Account owner:"
                                        + " none."));
        companySettingsPage()
                .getAuditLogItem()
                .get(0)
                .shouldBe(
                        matchText(
                                "Licensed seats limit changed from Unlimited to 10 by Automation"
                                        + " User on"));
    }

    /** Assert Audit Limit updated to unlimited state. */
    public static void assertAuditLogLimitUpdatedUnlimited() {
        companySettingsPage().getAuditLogItem().get(0).shouldBe(visible);
        companySettingsPage().getAuditLogItem().get(1).shouldBe(visible);
        companySettingsPage().getAuditLogItem().get(2).shouldBe(visible);
        companySettingsPage().getAuditLogItem().shouldBe(size(3));
        companySettingsPage()
                .getAuditLogItem()
                .get(2)
                .shouldBe(matchText("Company Account created by Automation User on"));
        companySettingsPage()
                .getAuditLogItem()
                .get(2)
                .shouldBe(
                        matchText(
                                "Limit licensed seats set to: Unlimited. Company Account owner:"
                                        + " none."));
        companySettingsPage()
                .getAuditLogItem()
                .get(1)
                .shouldBe(
                        matchText(
                                "Licensed seats limit changed from Unlimited to 10 by Automation"
                                        + " User on"));
        companySettingsPage()
                .getAuditLogItem()
                .get(0)
                .shouldBe(
                        matchText(
                                "Licensed seats limit changed from 10 to Unlimited by Automation"
                                        + " User on"));
    }

    /** Assert Audit Limit after company deactivation. */
    public static void assertAuditLogDeactivate() {
        companySettingsPage().getAuditLogItem().get(0).shouldBe(visible);
        companySettingsPage().getAuditLogItem().get(1).shouldBe(visible);
        companySettingsPage().getAuditLogItem().get(2).shouldBe(visible);
        companySettingsPage().getAuditLogItem().get(3).shouldBe(visible);
        companySettingsPage().getAuditLogItem().shouldBe(size(4));
        companySettingsPage()
                .getAuditLogItem()
                .get(3)
                .shouldBe(matchText("Company Account created by Automation User on"));
        companySettingsPage()
                .getAuditLogItem()
                .get(3)
                .shouldBe(
                        matchText(
                                "Limit licensed seats set to: Unlimited. Company Account owner:"
                                        + " none."));
        companySettingsPage()
                .getAuditLogItem()
                .get(2)
                .shouldBe(
                        matchText(
                                "Licensed seats limit changed from Unlimited to 10 by Automation"
                                        + " User on"));
        companySettingsPage()
                .getAuditLogItem()
                .get(1)
                .shouldBe(
                        matchText(
                                "Licensed seats limit changed from 10 to Unlimited by Automation"
                                        + " User on"));
        companySettingsPage()
                .getAuditLogItem()
                .get(0)
                .shouldBe(matchText("Company Account deactivated by Automation User on"));
    }

    /** Assert Audit Limit after company deactivation. */
    public static void assertAuditLogActivate() {
        companySettingsPage().getAuditLogItem().get(0).shouldBe(visible);
        companySettingsPage().getAuditLogItem().get(1).shouldBe(visible);
        companySettingsPage().getAuditLogItem().get(2).shouldBe(visible);
        companySettingsPage().getAuditLogItem().get(3).shouldBe(visible);
        companySettingsPage().getAuditLogItem().get(4).shouldBe(visible);
        companySettingsPage().getAuditLogItem().shouldBe(size(5));
        companySettingsPage()
                .getAuditLogItem()
                .get(4)
                .shouldBe(matchText("Company Account created by Automation User on"));
        companySettingsPage()
                .getAuditLogItem()
                .get(4)
                .shouldBe(
                        matchText(
                                "Limit licensed seats set to: Unlimited. Company Account owner:"
                                        + " none."));
        companySettingsPage()
                .getAuditLogItem()
                .get(3)
                .shouldBe(
                        matchText(
                                "Licensed seats limit changed from Unlimited to 10 by Automation"
                                        + " User on"));
        companySettingsPage()
                .getAuditLogItem()
                .get(2)
                .shouldBe(
                        matchText(
                                "Licensed seats limit changed from 10 to Unlimited by Automation"
                                        + " User on"));
        companySettingsPage()
                .getAuditLogItem()
                .get(1)
                .shouldBe(matchText("Company Account deactivated by Automation User on"));
        companySettingsPage()
                .getAuditLogItem()
                .get(0)
                .shouldBe(matchText("Company Account activated by Automation User on"));
    }
}
