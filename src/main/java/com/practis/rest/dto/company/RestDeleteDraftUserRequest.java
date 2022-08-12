package com.practis.rest.dto.company;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestDeleteDraftUserRequest {

  List<Integer> stagingIds;
}
