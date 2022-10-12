package com.practis.rest.mapper;

import com.practis.dto.NewChallengeInput;
import com.practis.rest.dto.company.audio.SaveFileResponse;
import com.practis.rest.dto.company.library.RestCreateChallenge;
import com.practis.rest.dto.company.library.RestCreateChallenge.Challenge;
import com.practis.rest.dto.company.library.RestCreateChallenge.Line;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ChallengeMapper {

  /**
   * Maps input to rest request body.
   */
  public static RestCreateChallenge toRestCreateChallenge(final NewChallengeInput input,
      final SaveFileResponse audio) {
    return RestCreateChallenge.builder()
        .challenge(Challenge.builder().title(input.getTitle()).build())
        .lines(toChallengeLines(input, audio)).build();
  }

  private static List<Line> toChallengeLines(final NewChallengeInput input,
      final SaveFileResponse audio) {
    final var position = new AtomicInteger();
    return input.getCustomerLines().stream().map(s -> {
      final var result = new ArrayList<Line>();
      result.add(toLine(audio, position, "CUSTOMER", s));
      return result;
    }).flatMap(Collection::stream).collect(Collectors.toList());
  }

  private static Line toLine(final SaveFileResponse audio, final AtomicInteger position,
      final String speaker, final String text) {
    return Line.builder().audioId(audio.getId())
        .duration(audio.getMetadata().getFormat().getDuration()).position(position.addAndGet(1))
        .speaker(speaker).text(text).build();
  }
}
