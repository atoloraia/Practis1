package com.practis.rest.configuration;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.practis.rest.service.PractisApiBabylonService.getToken;
import static com.practis.web.selenide.configuration.model.WebRestConfiguration.webRestConfig;
import static java.lang.String.format;
import static java.util.Objects.isNull;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practis.rest.client.PractisApiClient;
import com.practis.rest.service.PractisApiBabylonService;
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

public class PractisApiClientConfiguration {

    private static PractisApiClient PRACTIS_API_CLIENT;

    /** Returns api client. */
    public static PractisApiClient practisApiClient() {
        if (isNull(PRACTIS_API_CLIENT)) {
            PRACTIS_API_CLIENT = getPractisApiClient();
        }
        return PRACTIS_API_CLIENT;
    }

    private static PractisApiClient getPractisApiClient() {
        return Feign.builder()
                .decoder(new JacksonDecoder(objectMapper()))
                .encoder(new FormEncoder(new JacksonEncoder(objectMapper())))
                .requestInterceptor(headerInterceptor())
                .logger(new Slf4jLogger(PractisApiClient.class))
                .errorDecoder(
                        (methodKey, response) -> {
                            if (response.status() == 401) {
                                PractisApiBabylonService.resetToken();
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
                .target(PractisApiClient.class, webRestConfig().getPractisApiUrl());
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
            if (!template.path().equals("/api/auth/login")) {
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
