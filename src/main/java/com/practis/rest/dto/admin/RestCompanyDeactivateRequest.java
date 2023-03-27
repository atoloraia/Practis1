package com.practis.rest.dto.admin;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestCompanyDeactivateRequest {

    List<Integer> companyId;
}
