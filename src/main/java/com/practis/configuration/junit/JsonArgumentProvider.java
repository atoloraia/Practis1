package com.practis.configuration.junit;

import static com.practis.configuration.testrail.TestRailService.getTestRailInput;

import com.practis.configuration.testrail.TestRailTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

public class JsonArgumentProvider implements ArgumentsProvider {

  @Override
  public Stream<? extends Arguments> provideArguments(final ExtensionContext context) {
    final var annotation = context.getTestMethod().orElseThrow().getAnnotation(TestRailTest.class);
    final var input = getTestRailInput(annotation.caseId(), annotation.inputDataClass());
    return Stream.of(Arguments.of(input));
  }
}
