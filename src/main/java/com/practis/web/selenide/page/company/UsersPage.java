package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UsersPage {
  private final ElementsCollection tabs = $$(".sc-uWBKu.hYcdUn");
  private final SelenideElement noUsersFoundIcon = $(".sc-gpZsfs.HJlJi");
  private final SelenideElement noUsersFoundText = $(".sc-lliPGf.feEuiR");


}
