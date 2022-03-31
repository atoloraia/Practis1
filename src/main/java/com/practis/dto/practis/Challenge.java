package com.practis.dto.practis;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Challenge {

  String name;
  String description;
  String status;
  LocalDateTime lastUpdated;
  List<String> labels;
  List<String> customerLines;
}
