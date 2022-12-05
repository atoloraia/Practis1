package com.practis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewTeamInput {

    private String name;
    private Integer id;
}
