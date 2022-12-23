package com.practis.web.selenide.configuration;

import static java.util.Objects.isNull;

import com.practis.rest.service.PractisApiBabylonService;

public class RestObjectFactory {

    private static PractisApiBabylonService PRACTIS_API_SERVICE;

    /** Create or return existing PractisApiService. */
    public static PractisApiBabylonService practisApi() {
        if (isNull(PRACTIS_API_SERVICE)) {
            PRACTIS_API_SERVICE = new PractisApiBabylonService();
        }
        return PRACTIS_API_SERVICE;
    }
}
