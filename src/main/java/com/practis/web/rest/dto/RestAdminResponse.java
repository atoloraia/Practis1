package com.practis.web.rest.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestAdminResponse {

  Integer id;
  String email;
  String password;
  String firstName;
  String lastName;
}
