package com.practis.web.selenide.configuration.model;

import static com.practis.utils.ConfigurationLoader.loadConfig;
import static java.lang.System.getenv;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor
@Setter(AccessLevel.PRIVATE)
@Getter
public class WebApplicationConfiguration {

    private static WebApplicationConfiguration INSTANCE;

    /** Initialize WebApplicationConfiguration. */
    public static WebApplicationConfiguration webApplicationConfig() {
        if (isNull(INSTANCE)) {
            INSTANCE =
                    loadConfig(
                            "/configuration/web/application.json",
                            WebApplicationConfiguration.class);
            ofNullable(getenv("WEB_APP_URL"))
                    .filter(StringUtils::isNotEmpty)
                    .ifPresent(value -> INSTANCE.setUrl(value));
            ofNullable(getenv("WEB_APP_ADMIN_URL"))
                    .filter(StringUtils::isNotEmpty)
                    .ifPresent(value -> INSTANCE.setAdminUrl(value));
        }
        return INSTANCE;
    }

    String url;
    String adminUrl;
    String browser;
    String adminCompanyName;
    String automationCompanyName;
}
