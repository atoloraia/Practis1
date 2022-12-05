package com.practis.support.extension;

import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.practis.rest.service.PractisApiService.getToken;
import static com.practis.rest.service.PractisApiService.setCompany;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.selenide.configuration.model.WebCredentialsConfiguration.webCredentialsConfig;
import static com.practis.web.util.SelenidePageLoadAwait.awaitAjaxComplete;
import static com.practis.web.util.SelenidePageUtil.openPage;

import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class CompanyLoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        setCompany(
                webCredentialsConfig().getId(), webApplicationConfig().getAutomationCompanyName());

        final var homePage = webApplicationConfig().getUrl() + "/teams";
        open(homePage);

        localStorage().setItem("token", getToken());
        localStorage().setItem("userId", webCredentialsConfig().getId().toString());

        openPage(homePage);

        // todo check if it works without await
        awaitAjaxComplete(10);
    }
}
