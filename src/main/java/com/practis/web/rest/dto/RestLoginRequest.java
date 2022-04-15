package com.practis.web.rest.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestLoginRequest {

  String email;
  String password;
}
