package com.practis.rest.dto.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class SignUpUserResponseWrapper {

    InviteUserResponse user;
}
