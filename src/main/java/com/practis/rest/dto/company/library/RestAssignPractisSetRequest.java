package com.practis.rest.dto.company.library;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestAssignPractisSetRequest {

    List<RestPractisSetEnrollmentRequest> practisSets;
    List<Integer> userId;

    @Value
    @Builder
    public static class RestPractisSetEnrollmentRequest {

        Integer practisSetId;
    }
}
