package com.practis.rest.dto.company.library;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestPractisSetResponse {

  Integer id;
  String name;
}
