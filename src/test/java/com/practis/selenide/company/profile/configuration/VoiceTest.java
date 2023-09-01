package com.practis.selenide.company.profile.configuration;

import static com.codeborne.selenide.Condition.exactText;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.snackbar;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.bottomMenuService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companySettingsCompanyService;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertChangedVoiceSettings;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertVoiceTab;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

@PractisCompanyTestClass
@SelenideTestClass
@TestRailTestClass
public class VoiceTest {

    @BeforeEach
    void init() {
        bottomMenuService().clickOnBottomMenu();
        bottomMenuService().clickOnCompanySettings();
        companySettingsCompanyService().clickOnVoice();
    }

    @TestRailTest(caseId = 32277)
    @DisplayName("Company Settings: Voice: Check Elements")
    void checkElementsCompanyConfigVoice() {

        // assert elements on Voice tab
        assertVoiceTab();
    }

    @TestRailTest(caseId = 32278)
    @DisplayName("Company Settings: Voice: Update")
    void checkCompanyConfigVoiceUpdate() {

        // update voice settings
        companySettingsCompanyService().changeVoiceSetting();
        companySettingsCompanyService().clickOnSave();
        assertChangedVoiceSettings();
        snackbar().getMessage().shouldBe(exactText("Voice settings updated"));

        // reset voice settings to default
        companySettingsCompanyService().clickOnReset();
        assertVoiceTab();
        snackbar().getMessage().shouldBe(exactText("Voice settings has been reset to default"));
    }
}
