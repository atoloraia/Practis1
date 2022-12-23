package com.practis.rest.dto.company;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Builder
@Getter
public class RestTeamResponse {

    Integer id;
    String name;
    String query;
}
