package com.practis.rest.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateAdminRequest {

    String email;
}
