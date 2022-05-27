package com.practis.rest.dto.company;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestCreateLabelResponse {
  Integer id;
  String name;

}
