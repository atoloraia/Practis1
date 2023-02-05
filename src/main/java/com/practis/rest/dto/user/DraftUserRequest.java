package com.practis.rest.dto.user;

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DraftUserRequest {

    String firstName;
    String lastName;
    String email;
    Integer roleId;
    @Builder.Default List<Integer> practisSetIDs = new ArrayList<>();
    @Builder.Default List<Integer> teamIDs = new ArrayList<>();
}
