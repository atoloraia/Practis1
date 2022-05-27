package com.practis.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewChallengeInput {

  String title;
  String description;
  String customerLine;
  String repLine;
  String label;
  List<String> customerLines;
  List<String> repLines;
  List<String> labels;

}
