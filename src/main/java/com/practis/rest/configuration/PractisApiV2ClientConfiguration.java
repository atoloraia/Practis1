package com.practis.rest.configuration;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.practis.rest.service.PractisApiService.getToken;
import static com.practis.web.selenide.configuration.model.WebRestConfiguration.webRestConfig;
import static java.lang.String.format;
import static java.util.Objects.isNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practis.rest.client.PractisApiClientV2;
import com.practis.rest.service.PractisApiService;
import feign.Feign;
import feign.Logger.Level;
import feign.RequestInterceptor;
import feign.Response;
import feign.RetryableException;
import feign.form.FormEncoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import lombok.SneakyThrows;

public class PractisApiV2ClientConfiguration {

    private static PractisApiClientV2 PRACTIS_API_CLIENT_V2;

    /** Returns api client v2. */
    public static PractisApiClientV2 practisApiClientV2() {
        if (isNull(PRACTIS_API_CLIENT_V2)) {
            PRACTIS_API_CLIENT_V2 = getPractisApiClientV2();
        }
        return PRACTIS_API_CLIENT_V2;
    }

    private static PractisApiClientV2 getPractisApiClientV2() {
        return Feign.builder()
                .decoder(new JacksonDecoder(objectMapper()))
                .encoder(new FormEncoder(new JacksonEncoder(objectMapper())))
                .requestInterceptor(headerInterceptor())
                .logger(new Slf4jLogger(PractisApiClientV2.class))
                .errorDecoder(
                        (methodKey, response) -> {
                            if (response.status() == 401) {
                                PractisApiService.resetToken();
                                return new RetryableException(
                                        response.status(),
                                        "token seems to be expired",
                                        response.request().httpMethod(),
                                        null,
                                        response.request());
                            }
                            final var message =
                                    String.format(
                                            "exception handled for response: %s. Method: %s. Body:"
                                                    + " %s",
                                            response.status(), methodKey, getBodyString(response));
                            return new RuntimeException(message);
                        })
                .logLevel(Level.FULL)
                .target(PractisApiClientV2.class, webRestConfig().getPractisApiV2Url());
    }

    @SneakyThrows
    private static String getBodyString(final Response response) {
        StringBuilder textBuilder = new StringBuilder();
        try (Reader reader =
                new BufferedReader(
                        new InputStreamReader(
                                response.body().asInputStream(),
                                Charset.forName(StandardCharsets.UTF_8.name())))) {
            int c = 0;
            while ((c = reader.read()) != -1) {
                textBuilder.append((char) c);
            }
        }
        return textBuilder.toString();
    }

    private static RequestInterceptor headerInterceptor() {
        return template -> {
            if (!template.path().equals("/auth/login")) {
                template.header("authorization", format("JWT %s", getToken()));
            }
        };
    }

    private static ObjectMapper objectMapper() {
        final var mapper = new ObjectMapper();
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        return mapper;
    }
}
