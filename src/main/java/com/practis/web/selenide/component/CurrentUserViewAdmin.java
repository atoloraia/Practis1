package com.practis.web.selenide.component;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.companySelector;
import static com.practis.web.util.AwaitUtils.awaitElementExists;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CurrentUserViewAdmin {

  private final SelenideElement userAvatar = $("div[data-test='user-profile-area-avatar']");
  private final SelenideElement userName = $("div[data-test='user-profile-area-name']");
  private final SelenideElement userRole = $("div[data-test='user-profile-area-role']");
  private final ElementsCollection userSelector = $$("a[data-test='dropDownListLink'");
}
