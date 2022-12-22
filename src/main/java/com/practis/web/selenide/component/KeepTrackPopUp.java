package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class KeepTrackPopUp {

    public final SelenideElement keepTrackTitle = $(".sc-jwRisT.MbOfb");
    public final SelenideElement keepTrackDescription = $(".sc-cgLHPZ.eKVclz");
    public final SelenideElement gotItButton = $(".sc-iAKVOt.kvhLwn.primary");
}
