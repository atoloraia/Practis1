package com.practis.rest.dto.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CreateAdminResponse {

    Integer id;
    String firstName;
    String lastName;
    String email;
    Integer roleId;
    Integer companyId;
    String code;
    String phoneNumber;
    String invitationCode;
    String password;
}
