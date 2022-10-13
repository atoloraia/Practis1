package com.practis.web.selenide.component.user.invite;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class FailedInvitingUsersPopUp {
  private final SelenideElement failedInvitingUsersTitle = $(".sc-gbpjgc.kfAyUH");
  private final SelenideElement descriptionMessage = $(".sc-cInOqb.feKeNi");
  private final SelenideElement gotItButton = $(".sc-jcFkyM.gNfFbi");
}
