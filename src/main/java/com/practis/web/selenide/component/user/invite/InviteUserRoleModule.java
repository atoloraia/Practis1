package com.practis.web.selenide.component.user.invite;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class InviteUserRoleModule {
  private final SelenideElement userRoleRadioButton = $("div[data-test='role-user']");
  private final SelenideElement adminRoleRadioButton = $("div[data-test='role-admin']");
  private final SelenideElement editRoleUserRadioButton = $(".sc-euuaQX.vAZeO");

}
