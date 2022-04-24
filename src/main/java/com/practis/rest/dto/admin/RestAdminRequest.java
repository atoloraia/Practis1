package com.practis.rest.dto.admin;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestAdminRequest {

  String email;
  String password;
  String firstName;
  String lastName;
  @Builder.Default
  Integer roleId = 5;
  Integer companyId;
}
