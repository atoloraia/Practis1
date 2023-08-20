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

    private final ElementsCollection tabs = $$(".sc-YuGdA.bBDjRw");

    // Logo tab
    private final SelenideElement companyLogo = $("div[data-test='company-logo-container']");
    private final SelenideElement companyLogoCamera = $("div[data-test='company-logo-camera']");
    private final SelenideElement deleteCompanyLogo = $("div[data-test='company-logo-delete']");
    private final SelenideElement logoDescription = $(".sc-kTwdTh.hJtffO");
    private final SelenideElement guidelinesLink = $(".sc-fBFLWQ.ccWUmu");
    private final SelenideElement nextButton = $(".sc-fMFieO.jQpBxg");
    private final SelenideElement uploadField = $("input[data-test='company-logo-input']");
    private final SelenideElement uploadPhotoSubmitButton = $(".sc-hmjpBu.kuijHN");
}
