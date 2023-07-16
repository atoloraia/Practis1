package com.practis.rest.dto.admin;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserStatsResponse {

    Integer total;
}
