package com.practis.web.selenide.service;

import static com.codeborne.selenide.Selenide.open;
import static com.practis.web.selenide.configuration.ComponentObjectFactory.bottomProfileMenu;
import static com.practis.web.selenide.configuration.PageObjectFactory.homePage;
import static com.practis.web.selenide.configuration.PageObjectFactory.loginPage;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.addMobileService;
import static com.practis.web.selenide.configuration.ServiceObjectFactory.loginService;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.util.SelenidePageLoadAwait.awaitAjaxComplete;
import static com.practis.web.util.SelenidePageLoadAwait.awaitFullPageLoad;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;

import com.practis.web.selenide.configuration.model.WebCredentialsConfiguration;

public class LoginService {

    /** Get Email Validation Message. */
    public void fillFormAndLogin(final String email, final String password) {
        fillLoginForm(email, password);
        await().pollDelay(3, SECONDS).until(() -> true);
        clickLogin();
    }

    /** Fill email and click 'Login'. */
    public void fillEmailLogin(final String email) {
        fillEmail(email);
        clickLogin();
    }

    /** Click 'Login' on empty form. */
    public void emptyFormLogin() {
        clickLogin();
    }

    /** Fill login form with email and password. */
    public void fillLoginForm(final String email, final String password) {
        fillEmail(email);
        fillPassword(password);
    }

    /** Fill login form with email. */
    public void fillEmail(final String email) {
        loginPage().getEmailField().setValue(email.substring(0, email.length() - 1));
        loginPage().getEmailField().append(email.substring(email.length() - 1));
    }

    /** Fill login form with password. */
    public void fillPassword(final String password) {
        loginPage().getPasswordField().setValue(password.substring(0, password.length() - 1));
        loginPage().getPasswordField().append(password.substring(password.length() - 1));
    }

    /** Click 'Login' button. */
    public void clickLogin() {
        loginPage().getLoginButton().click();
    }

    /** Logout */
    public void logOut() {
        bottomProfileMenu().getUserName().click();
        bottomProfileMenu().getUserSelector().get(1).click();
    }

    /** Log in as Practis Admin */
    public void loginAsPractisAdmin(WebCredentialsConfiguration credentials) {
        open(webApplicationConfig().getUrl());

        homePage().getLoginButton().click();
        awaitFullPageLoad(10);
        loginService().fillFormAndLogin(credentials.getLogin(), credentials.getPassword());
        awaitAjaxComplete(20);
        addMobileService().clickAddLater();
    }

    /** Click on Login with Email & Password */
    public void logInWithEmailAndPassword() {
        loginPage().getLoginWithEmailAndPassword().click();
    }
}
