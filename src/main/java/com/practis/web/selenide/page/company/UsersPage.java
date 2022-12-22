package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UsersPage {
    private final ElementsCollection tabs = $$(".sc-inrCKc.ceaNnH");
    private final SelenideElement noUsersFoundIcon = $(".sc-fkqjzy.gTwUsI");
    private final SelenideElement noUsersFoundText = $(".sc-gdvdet.chqfSt");
    private final SelenideElement threeDotMenu = $(".sc-gXRoDt.iAvKeU");
}
