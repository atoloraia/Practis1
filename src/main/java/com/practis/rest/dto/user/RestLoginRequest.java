package com.practis.rest.dto.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestLoginRequest {

  String email;
  String password;
}
