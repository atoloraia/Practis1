package com.practis.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PractisUser {

  Integer id;
  String firstName;
  String lastName;
  String email;
  Integer roleId;
  Integer companyId;
}
