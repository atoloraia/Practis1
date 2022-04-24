package com.practis.rest.dto.company.library;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RestPractisSetArchiveRequest {

  List<Integer> practisSetIds;
}
