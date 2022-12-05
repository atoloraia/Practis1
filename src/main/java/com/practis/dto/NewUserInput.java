package com.practis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewUserInput {

    Integer id;
    String firstName;
    String lastName;
    String email;
    Integer roleId;
    Integer companyId;
    String password;
    String invitationCode;
}
