package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.verifyMobileNumberPage;

import com.codeborne.selenide.CollectionCondition;

public class VerifyMobileNumberValidator {

    /** Assert elements on 'Verify Mobile Number' page. */
    public static void assertElementsOnVerifyMobilePage(String number) {
        verifyMobileNumberPage().getPractisLogo().shouldBe(visible);
        verifyMobileNumberPage().getVerifyMobileNumberTitle().shouldBe(visible);
        verifyMobileNumberPage()
                .getVerifyMobileNumberTitle()
                .shouldBe(exactText("Verify Mobile Number"));
        verifyMobileNumberPage().getDescriptionText().shouldBe(visible);
        verifyMobileNumberPage()
                .getDescriptionText()
                .shouldBe(exactText("Enter the 5-digit code we sent to:"));
        verifyMobileNumberPage().getMobileNumber().shouldBe(visible);
        verifyMobileNumberPage().getMobileNumber().shouldBe(matchText(number));
        verifyMobileNumberPage().getMobileCell().shouldHave(CollectionCondition.size(5));
        verifyMobileNumberPage().getVerifyButton().shouldBe(visible);
        verifyMobileNumberPage().getVerifyButton().shouldBe(exactText("Verify"));
    }
}
