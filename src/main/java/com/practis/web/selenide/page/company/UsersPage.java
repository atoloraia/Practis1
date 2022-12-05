package com.practis.web.selenide.page.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class UsersPage {
    private final ElementsCollection tabs = $$(".sc-dTAZSv.hvGImv");
    private final SelenideElement noUsersFoundIcon = $(".sc-cxBQeN.kRsBFa");
    private final SelenideElement noUsersFoundText = $(".sc-gpZsfs.dyKWQW");
    private final SelenideElement threeDotMenu = $(".sc-gXRoDt.iAvKeU");
}
