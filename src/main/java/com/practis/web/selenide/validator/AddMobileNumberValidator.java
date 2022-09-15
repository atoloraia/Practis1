package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.disabled;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.addMobileNumberPage;

public class AddMobileNumberValidator {

  /**
   * Assert elements on 'Add Mobile Number' page.
   */
  public static void assertElementsOnAddMobilePage() {
    addMobileNumberPage().getPractisLogo().shouldBe(visible);
    addMobileNumberPage().getAddMobileTitle().shouldBe(visible);
    addMobileNumberPage().getAddMobileTitle().shouldBe(exactText("Add Mobile Number"));
    addMobileNumberPage().getDescriptionText().shouldBe(visible);
    addMobileNumberPage().getDescriptionText()
        .shouldBe(exactText("Adding a mobile number improves security "
            + "and gives you more options to log in later."));
    addMobileNumberPage().getMobileField().shouldBe(visible);
    addMobileNumberPage().getMobileInput().shouldBe(attribute("maxlength", "50"));
    addMobileNumberPage().getMobileInput().shouldBe(attribute("type", "tel"));
    addMobileNumberPage().getMobileFieldText()
        .shouldBe(exactText("We will send an SMS to verify your mobile number. "
            + "Carrier fees may apply."));

    addMobileNumberPage().getSendCodeButton().shouldBe(visible);
    addMobileNumberPage().getSendCodeButton().shouldBe(disabled);
    addMobileNumberPage().getSendCodeButton().shouldBe(exactText("Send Code"));
    addMobileNumberPage().getSendCodeButton().lastChild().shouldBe(attribute("height","56px"));
    addMobileNumberPage().getSendCodeButton().lastChild().shouldBe(attribute("font-size","15"));

    addMobileNumberPage().getAddLaterButton().shouldBe(visible);
    addMobileNumberPage().getAddLaterButton().shouldBe(exactText("Add Later"));
    addMobileNumberPage().getAddLaterButton().lastChild().shouldBe(attribute("height","56px"));
    addMobileNumberPage().getAddLaterButton().lastChild().shouldBe(attribute("font-size","15"));
  }
}
