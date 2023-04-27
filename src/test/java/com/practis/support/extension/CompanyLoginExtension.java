package com.practis.support.extension;

import static com.codeborne.selenide.Selenide.localStorage;
import static com.codeborne.selenide.Selenide.open;
import static com.practis.rest.service.PractisApiService.getToken;
import static com.practis.rest.service.PractisApiService.getUserId;
import static com.practis.rest.service.PractisApiService.setCompany;
import static com.practis.web.selenide.configuration.model.WebApplicationConfiguration.webApplicationConfig;
import static com.practis.web.util.SelenidePageUtil.openPage;
import static java.util.Optional.of;

import com.practis.support.PractisCompanyTestClass;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class CompanyLoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        final var annotation =
                context.getTestClass().orElseThrow().getAnnotation(PractisCompanyTestClass.class);
        final var companyName =
                of(annotation.companyName())
                        .filter(value -> value.length() > 0)
                        .orElse(webApplicationConfig().getAutomationCompanyName());
        setCompany(getUserId(), companyName);

        final var homePage = webApplicationConfig().getUrl() + "/teams";
        open(homePage);

        localStorage().setItem("token", getToken());
        localStorage().setItem("userId", getUserId().toString());
        // localStorage().setItem("apiPlatform", "babylon");

        openPage(homePage);
    }
}
