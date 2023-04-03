package com.practis.web.selenide.service;

import static com.practis.web.selenide.configuration.ComponentObjectFactory.createAnAccountPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.createAnAccountService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static java.lang.String.format;

import com.codeborne.selenide.Selenide;

public class CreateAnAccountService {

    /** Create password. */
    public void createPassword(final String password) {
        createAnAccountPage().getPasswordField().append(password);
        createAnAccountPage().getConfirmPasswordField().append(password);
        createAnAccountPage().getTermsCheckboxView().click();
        createAnAccountPage().getContinueButton().click();
    }

    /** Create account. */
    public void createAccount(final String password, final String code) {
        final var url = format("%s/registration/?token=%s", webApplicationConfig().getUrl(), code);
        Selenide.open(url);
        createAnAccountService().createPassword(password);
    }
}
