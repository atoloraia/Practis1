package com.practis.web.selenide.page;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class ThisDidNotWorkPage {

    private final SelenideElement InvalidInviteImg = $("img[alt='Invalid invite']");
    private final SelenideElement thisDidNotWorkTitle = $(".sc-dOceEX.Gwwsu");
    private final ElementsCollection thisDidNotWorkDescription = $$(".sc-hWgxVL.kmDdfc");
}
