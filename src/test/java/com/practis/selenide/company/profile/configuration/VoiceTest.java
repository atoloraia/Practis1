package com.practis.selenide.company.profile.configuration;

import static com.codeborne.selenide.Condition.enabled;
import static com.practis.web.selenide.configuration.PageObjectFactory.companySettingsPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.bottomMenuService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.companySettingsCompanyService;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertChangedSampleText;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertClarityTooltip;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertGeneratingMode;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertListeningMode;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertNewRepVoiceTab;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertStabilityTooltip;
import static com.practis.web.selenide.validator.company.CompanySettingsCompanyValidator.assertVoiceTab;
import static org.awaitility.Awaitility.await;
import static org.awaitility.Duration.TWO_SECONDS;

import com.practis.support.PractisCompanyTestClass;
import com.practis.support.SelenideTestClass;
import com.practis.support.TestRailTest;
import com.practis.support.TestRailTestClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

        // assert Stability tooltip
        companySettingsCompanyService().clickOnStabilityTooltip();
        assertStabilityTooltip();

        // assert Clarity tooltip
        companySettingsCompanyService().clickOnClarityTooltip();
        assertClarityTooltip();
    }

    @Disabled
    // @TestRailTest(caseId = 32278)
    @DisplayName("Company Settings: Voice: Update Role")
    void checkElementsChangedCompanyConfigVoice() {

        // Set Representative voice
        companySettingsCompanyService().setRepresentativeVoice();
        companySettingsCompanyService().clickOnVoice();

        // Assert Representative voice view
        assertNewRepVoiceTab();

        // assert Stability tooltip
        companySettingsCompanyService().clickOnStabilityTooltip();
        assertStabilityTooltip();

        // assert Clarity tooltip
        companySettingsCompanyService().clickOnClarityTooltip();
        assertClarityTooltip();
    }

    @TestRailTest(caseId = 32299)
    @DisplayName("Company Settings: Voice: Update")
    void checkCompanyConfigVoiceUpdate() {

        // update and verify Customer voice settings
        companySettingsCompanyService().changeVoiceSetting();
        companySettingsPage().getApplyButton().shouldBe(enabled);
        companySettingsCompanyService().clickOnSave();
        await().pollDelay(TWO_SECONDS).until(() -> true);
        companySettingsCompanyService().clickOnDefaultButton();
        companySettingsCompanyService().clickOnSave();

        // update and verify Representative voice settings
        //        await().pollDelay(TWO_SECONDS).until(() -> true);
        //        companySettingsCompanyService().setRepresentativeVoice();
        //        companySettingsCompanyService().changeVoiceSetting();
        //        companySettingsPage().getApplyButton().shouldBe(enabled);
        //        companySettingsCompanyService().clickOnSave();
        //        companySettingsCompanyService().clickOnDefaultButton();
        //        companySettingsCompanyService().clickOnSave();
    }

    @TestRailTest(caseId = 32300)
    @DisplayName("Company Settings: Voice: Verify Sample Text field")
    void checkCompanyConfigVoiceSampleText() {

        // Assert Sample Text for Customer mode
        companySettingsCompanyService().clickOnListenButton();
        assertGeneratingMode();
        assertListeningMode();

        companySettingsCompanyService().clickOnStopButton();
        companySettingsCompanyService().enterSampleText();
        assertChangedSampleText();

        // Assert Sample Text for Representative mode
        //        await().pollDelay(TWO_SECONDS).until(() -> true);
        //        companySettingsCompanyService().changeVoiceSetting();
        //        companySettingsCompanyService().clickOnListenButton();
        //        assertGeneratingMode();
        //        assertListeningMode();
        //
        //        companySettingsCompanyService().clickOnStopButton();
        //        companySettingsCompanyService().enterSampleText();
        //        assertChangedSampleText();
    }
}
