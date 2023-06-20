package com.practis.rest.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InviteUserRequest {

    String firstName;
    String lastName;
    String password;
    String email;
    Integer roleId;
    Integer companyId;
}
