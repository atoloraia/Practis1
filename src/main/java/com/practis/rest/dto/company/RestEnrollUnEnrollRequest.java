package com.practis.rest.dto.company;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestEnrollUnEnrollRequest {

    Integer practisSetId;
    Integer userId;
    ZonedDateTime dueDate;
}
