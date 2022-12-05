package com.practis.web.selenide.validator;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.practis.web.selenide.configuration.PageObjectFactory.loginPage;

import lombok.experimental.UtilityClass;

@UtilityClass
public class LoginValidator {

    /** Assert elements on Login page. */
    public static void assertElementsLoginPage() {

        loginPage().getPractisLogo().shouldBe(visible);
        loginPage().getPractisLogo().shouldBe(attribute("width", "100%"));
        loginPage().getPractisLogo().shouldBe(attribute("height", "100%"));

        loginPage().getEmailField().shouldBe(visible);
        loginPage().getEmailField().shouldBe(attribute("name", "email"));
        loginPage().getEmailField().shouldBe(attribute("type", "text"));
        loginPage().getEmailField().shouldBe(attribute("font-size", "15px"));
        loginPage().getEmailField().sibling(0).shouldBe(exactText("Email Address"));

        loginPage().getPasswordField().shouldBe(visible);
        loginPage().getPasswordField().shouldBe(attribute("name", "password"));
        loginPage().getPasswordField().shouldBe(attribute("type", "password"));
        loginPage().getPasswordField().shouldBe(attribute("font-size", "15px"));
        loginPage().getPasswordField().sibling(0).shouldBe(exactText("Password"));

        loginPage().getForgotPasswordButton().shouldBe(visible);
        loginPage().getForgotPasswordButton().shouldBe(exactText("Forgot Password?"));

        loginPage().getLoginButton().shouldBe(visible);
        loginPage().getLoginButton().shouldBe(exactText("Log In"));
        loginPage().getLoginButton().shouldBe(attribute("width", "100%"));
        loginPage().getLoginButton().shouldBe(attribute("color", "default"));

        loginPage().getOrLoginWithText().shouldBe(visible);
        loginPage().getOrLoginWithText().shouldBe(exactText("or log in with"));

        loginPage().getMobileNumberButton().shouldBe(visible);
        loginPage().getMobileNumberButton().shouldBe(exactText("Mobile Number"));
        loginPage().getMobileNumberButton().shouldBe(attribute("width", "100%"));
        loginPage().getMobileNumberButton().shouldBe(attribute("color", "default"));
    }
}
