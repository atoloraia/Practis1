package com.practis.web.selenide.component;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class KeepTrackPopUp {

    public final SelenideElement keepTrackTitle = $(".sc-eaPOKa.iYbTgn");
    public final SelenideElement keepTrackDescription = $(".sc-inrCKc.gtiUUj");
    public final SelenideElement gotItButton = $(".sc-jcFkyM.iFlqaK.undefined.primary");
}
