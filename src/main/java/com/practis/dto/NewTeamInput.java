package com.practis.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class NewTeamInput {

  private String name;
  private Integer id;

}
