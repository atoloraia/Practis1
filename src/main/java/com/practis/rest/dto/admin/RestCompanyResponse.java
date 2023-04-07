package com.practis.rest.dto.admin;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestCompanyResponse {

    Integer id;
    String name;
    String email;
    String status;
}
