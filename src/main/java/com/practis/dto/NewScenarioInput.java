package com.practis.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class NewScenarioInput {

  String title;
  String description;
  String customerLine;
  String repLine;

}
