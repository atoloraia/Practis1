package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class AssignUsersModule {

  private final SelenideElement cancelButton = $("button[title='Cancel']");
  private final SelenideElement applyButton = $("button[title='Apply']");

}
