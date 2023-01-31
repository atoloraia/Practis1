package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class RoleModule {

    private final SelenideElement userRoleTitle = $("span[data-test='role-filter-title']");

    private final SelenideElement userRoleRadioButtonInviteUser =
            $("div[data-test='user-role-radio-view']");
    private final SelenideElement adminRoleRadioButtonInviteUser =
            $("div[data-test='admin-role-radio-view']");

    private final SelenideElement userRoleRadioButtonEditInviteUser =
            $("div[data-test='role-user']");
    private final SelenideElement adminRoleRadioButtonEditInviteUser =
            $("div[data-test='role-admin']");

    private final SelenideElement userRoleCheckbox = $("input[data-test='user-role-checkbox']");
    private final SelenideElement adminRoleCheckbox = $("input[data-test='admin-role-checkbox']");

    private final SelenideElement userRoleCheckboxLabel =
            $("input[data-test='user-role-checkbox-label']");
    private final SelenideElement adminRoleCheckboxLabel =
            $("input[data-test='admin-role-checkbox-label']");

    private final ElementsCollection assignUserRoleRadioButtonInvite =
            $$("input[data-test*='user-role-radio']");
    private final ElementsCollection assignAdminRoleRadioButtonInvite =
            $$("input[data-test*='admin-role-radio']");
    private final ElementsCollection assignRoleRadioButtonInvite =
            $$("div[data-test='role-admin']");

    private final ElementsCollection assignRoleSelectedStateRadioButton = $$(".sc-jpMCM.leepZw");
    private final ElementsCollection assignRoleUnselectedStateRadioButton = $$(".sc-jpMCM.tkuiC");

    private final SelenideElement assignUserRolePartiallyStateRadioButton =
            $("input[data-test='user-role-radio-partially-checked']");
    private final SelenideElement assignAdminRolePartiallyStateRadioButton =
            $("input[data-test='admin-role-radio-partially-checked']");
}
