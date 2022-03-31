package com.practis.dto;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewChallengeInput {

  String title;
  String description;
  String customerLine;
  String repLine;
  List<String> customerLines;
  List<String> labels;

}
