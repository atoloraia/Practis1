package com.practis.rest.dto.company;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestLabelResponse {

  Integer id;
  String name;
}
