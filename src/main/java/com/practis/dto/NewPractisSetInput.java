package com.practis.dto;

import java.util.List;
import lombok.Builder;
import lombok.Data;
import lombok.Value;

@Data
@Builder
public class NewPractisSetInput {

  String title;
  String description;
  List<String> labels;

}
