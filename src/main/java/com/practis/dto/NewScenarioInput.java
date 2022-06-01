package com.practis.dto;

import java.util.List;
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
  List<String> customerLines;
  List<String> repLines;

}
