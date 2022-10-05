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

  private final ElementsCollection assignRoleRadioButton = $$(".sc-hnCQTB.cjLjuF");
  private final ElementsCollection assignRoleSelectedStateRadioButton = $$(".sc-cgLVfi.kNYzXM");
  private final ElementsCollection assignRoleUnselectedStateRadioButton = $$(".sc-cgLVfi.RYYau");

}
