package com.practis.rest.dto.company;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestSearchLabelResponse {

  Integer id;
  String name;
}
