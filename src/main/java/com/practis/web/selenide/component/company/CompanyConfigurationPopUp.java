package com.practis.web.selenide.component.company;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

@Getter
public class CompanyConfigurationPopUp {

    private final SelenideElement companyConfigurationTitle = $(".sc-egkNLF.iEDLCb");
    private final SelenideElement companyName = $(".sc-kdQfok.ijeJoO");
    private final SelenideElement closeButton = $(".sc-dRXgEy.kYDqiz");

    private final ElementsCollection tabs = $$(".sc-edjiGW.bTTquU");

    private final ElementsCollection links = $$(".sc-gqkXZR.goswMP");

    // Logo tab
    private final SelenideElement companyLogo = $("div[data-test='company-logo-container']");
    private final SelenideElement companyLogoImage = $("div[data-test='company-logo-image']");
    private final SelenideElement companyLogoCamera = $("div[data-test='company-logo-camera']");
    private final SelenideElement deleteCompanyLogo = $("div[data-test='company-logo-delete']");
    private final SelenideElement logoDescription = $(".sc-kTwdTh.hJtffO");
    private final SelenideElement guidelinesLink = $(".sc-kTwdTh.hJtffO");
    private final SelenideElement nextButton = $(".sc-levAbf.bMMJaM");
    private final SelenideElement uploadField = $("input[data-test='company-logo-input']");
    private final SelenideElement uploadPhotoSubmitButton = $(".sc-hmjpBu.kuijHN");

    // Licensed Seats tab

    private final SelenideElement unlimitedText = $("div[data-test='unlimited-seats-radio-label']");
    private final SelenideElement unlimitedRadiobutton =
            $("div[data-test='unlimited-seats-radio-view']");
    private final SelenideElement unlimitedDescription =
            $("div[data-test='unlimited-seats-description']");
    private final SelenideElement limitText = $("div[data-test='limited-seats-radio-label']");
    private final SelenideElement limitRadiobutton = $("div[data-test='limited-seats-radio-view']");
    private final SelenideElement limitField = $("input[data-test='limited-seats-input']");
    private final SelenideElement limitDescription =
            $("div[data-test='limited-seats-description']");

    private final SelenideElement applyButton = $("button[data-test='apply-button']");
}
