package com.practis.rest.dto.company;

import com.practis.rest.dto.company.library.RestAssignPractisSetRequest;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestEnrollUnEnrollRequest {

  RestAssignPractisSetRequest enroll;

}
