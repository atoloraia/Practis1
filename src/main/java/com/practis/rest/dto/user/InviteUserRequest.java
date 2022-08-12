package com.practis.rest.dto.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class InviteUserRequest {

  String firstName;
  String lastName;
  String email;
  Integer roleId;
  Integer companyId;
}
