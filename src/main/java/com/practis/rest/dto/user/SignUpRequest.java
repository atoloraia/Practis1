package com.practis.rest.dto.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SignUpRequest {

  String firstName;
  String lastName;
  String email;
  String password;
  String invitationCode;
  String phoneNumber;
}
