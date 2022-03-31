package com.practis.dto.practis;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PractisSet {

  String title;
  String description;
  String totalDuration;
  Integer totalRepsRequired;
  String minimumAccuracy;
}
