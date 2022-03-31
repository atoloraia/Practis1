package com.practis.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewScenarioInput {

  String title;
  String description;
  String customerLine;
  String repLine;


}
