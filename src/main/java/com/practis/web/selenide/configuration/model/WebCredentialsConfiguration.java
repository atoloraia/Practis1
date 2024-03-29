package com.practis.web.selenide.configuration.model;

import static com.practis.utils.ConfigurationLoader.loadConfig;
import static java.lang.Integer.valueOf;
import static java.lang.System.getenv;
import static java.util.Objects.isNull;
import static java.util.Optional.ofNullable;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

@Getter
@Setter(AccessLevel.PRIVATE)
public class WebCredentialsConfiguration {

    private static WebCredentialsConfiguration INSTANCE;

    /** Initialize WebCredentialsConfiguration. */
    public static WebCredentialsConfiguration webCredentialsConfig() {
        if (isNull(INSTANCE)) {
            INSTANCE =
                    loadConfig(
                            "/configuration/web/credentials.json",
                            WebCredentialsConfiguration.class);
            ofNullable(getenv("WEB_USER_ID"))
                    .filter(StringUtils::isNotEmpty)
                    .ifPresent(value -> INSTANCE.setId(valueOf(value)));
            ofNullable(getenv("WEB_USER_LOGIN"))
                    .filter(StringUtils::isNotEmpty)
                    .ifPresent(value -> INSTANCE.setLogin(value));
            ofNullable(getenv("WEB_USER_PASSWORD"))
                    .filter(StringUtils::isNotEmpty)
                    .ifPresent(value -> INSTANCE.setPassword(value));
        }
        return INSTANCE;
    }

    Integer id;
    Integer adminId;
    String login;
    String password;
}
