package com.practis.rest.dto.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestLoginResponse {

    String token;
    LoginUserResponse user;

    @Value
    @Builder
    public static class LoginUserResponse {
        Integer id;
    }
}
