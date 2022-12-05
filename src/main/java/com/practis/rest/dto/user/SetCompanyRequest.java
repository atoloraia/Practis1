package com.practis.rest.dto.user;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class SetCompanyRequest {

    Integer companyId;
}
