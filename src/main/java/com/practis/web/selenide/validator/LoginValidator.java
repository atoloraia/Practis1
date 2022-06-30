package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.loginPage;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LoginValidator {

  /**
   * Assert elements on Login page.
   */
  public static void assertElementsLoginPage() {

    loginPage().getPractisLogo().shouldBe(visible);

    loginPage().getEmailField().shouldBe(visible);
    loginPage().getEmailField().sibling(0).shouldBe(exactText("Email Address"));

    loginPage().getPasswordField().shouldBe(visible);
    loginPage().getPasswordField().sibling(0).shouldBe(exactText("Password"));

    loginPage().getForgotPasswordButton().shouldBe(visible);
    loginPage().getForgotPasswordButton().shouldBe(exactText("Forgot Password?"));

    loginPage().getOrLoginWithText().shouldBe(visible);
    loginPage().getOrLoginWithText().shouldBe(exactText("or log in with"));

    loginPage().getMobileNumberButton().shouldBe(visible);
    loginPage().getMobileNumberButton().shouldBe(exactText("Mobile Number"));

  }

}
