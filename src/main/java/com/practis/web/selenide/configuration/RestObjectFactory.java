package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.rest.service.PractisApiService;

public class RestObjectFactory {

    private static PractisApiService PRACTIS_API_SERVICE;

    /** Create or return existing PractisApiService. */
    public static PractisApiService practisApi() {
        if (isNull(PRACTIS_API_SERVICE)) {
            PRACTIS_API_SERVICE = new PractisApiService();
        }
        return PRACTIS_API_SERVICE;
    }
}
