package com.practis.web.selenide.component.selection;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class RoleModule {
    private final SelenideElement userRoleRadioButton = $("div[data-test='role-user']");
    private final SelenideElement adminRoleRadioButton = $("div[data-test='role-admin']");

    private final ElementsCollection assignRoleRadioButton = $$(".sc-bVMyXk.ijmZYw");
    private final ElementsCollection assignRoleSelectedStateRadioButton = $$(".sc-kTwdTh.cqjssd");
    private final ElementsCollection assignRoleUnselectedStateRadioButton = $$(".sc-kTwdTh.OJucT");
}
