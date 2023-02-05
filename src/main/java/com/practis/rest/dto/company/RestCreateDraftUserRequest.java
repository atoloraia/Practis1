package com.practis.rest.dto.company;

import com.practis.rest.dto.user.DraftUserRequest;
import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestCreateDraftUserRequest {

    String name;
    List<DraftUserRequest> users;
}
