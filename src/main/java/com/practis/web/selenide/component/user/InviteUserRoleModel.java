package com.practis.web.selenide.component.user;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class InviteUserRoleModel {
  private final SelenideElement userRoleRadioButton = $("div[data-test='role-user']");
  private final SelenideElement adminRoleRadioButton = $("div[data-test='role-admin']");
  private final SelenideElement editRoleUserRadioButton = $(".sc-euuaQX.vAZeO");

}
