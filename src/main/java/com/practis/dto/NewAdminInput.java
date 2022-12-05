package com.practis.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewAdminInput {

    String password;
    String email;
    String firstName;
    String lastName;
}
