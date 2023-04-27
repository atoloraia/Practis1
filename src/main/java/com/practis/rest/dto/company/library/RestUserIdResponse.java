package com.practis.rest.dto.company.library;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestUserIdResponse {

    Integer id;
    String firstName;
    String lastName;
    String email;
}
