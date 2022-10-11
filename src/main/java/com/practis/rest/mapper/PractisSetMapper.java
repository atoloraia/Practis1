package com.practis.rest.mapper;

import com.practis.dto.NewPractisSetInput;
import com.practis.rest.dto.company.audio.SaveFileResponse;
import com.practis.rest.dto.company.library.RestChallengeResponse;
import com.practis.rest.dto.company.library.RestCreateChallenge.Line;
import com.practis.rest.dto.company.library.RestPractisSetRequest;
import com.practis.rest.dto.company.library.RestPractisSetRequest.Content;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PractisSetMapper {

  /**
   * Maps input to rest request body.
   * @return
   */
  public static RestPractisSetRequest toRestCreatePractisSet(
      final NewPractisSetInput input, final List<RestChallengeResponse> challenges) {
    return RestPractisSetRequest.builder()
        .name(input.getName())
        .description(input.getDescription())
        .content(toPractisSetContent(challenges))
        .build();
  }

  private static List<Content> toPractisSetContent(List<RestChallengeResponse> challenges) {
    return challenges.stream().map(challenge -> Content.builder()
            .id(challenge.getId())
            .type("CHALLENGE")
            .build())
        .collect(Collectors.toList());
  }
}
