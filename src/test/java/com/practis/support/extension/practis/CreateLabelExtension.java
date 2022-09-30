package com.practis.support.extension.practis;

import static com.practis.utils.StringUtils.timestamp;
import static com.practis.web.selenide.configuration.RestObjectFactory.practisApi;
import static java.lang.String.format;

import com.practis.dto.NewLabelInput;
import com.practis.rest.dto.company.RestCreateLabelResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class CreateLabelExtension implements
    BeforeEachCallback, AfterEachCallback, ParameterResolver {

  private final List<RestCreateLabelResponse> labelsToRemove = new ArrayList<>();
  private final AtomicInteger integer = new AtomicInteger();

  @Override
  public void beforeEach(final ExtensionContext context) throws Exception {
    final var annotation = context.getTestMethod().orElseThrow()
        .getAnnotation(LabelExtension.class);

    IntStream.range(0, annotation.count()).forEach(idx -> {
      final var label = practisApi().createLabel(
          NewLabelInput.builder()
              .name(String.format("test-%s-%s", integer.addAndGet(1), timestamp())).build());

      //final var labelInput =
      // NewLabelInput.builder().name(String.format("test-%s", timestamp())).build();
      //final var label = practisApi().createLabel(labelInput);
      labelsToRemove.add(label);
    });
  }

  @Override
  public void afterEach(final ExtensionContext context) throws Exception {
    labelsToRemove.forEach(label -> practisApi().deleteLabel(label.getName()));
  }

  @Override
  public boolean supportsParameter(final ParameterContext parameterContext,
      final ExtensionContext extensionContext) throws ParameterResolutionException {
    return parameterContext.getParameter().getParameterizedType().getTypeName()
        .equals(format("java.util.List<%s>", RestCreateLabelResponse.class.getName()));
  }

  @Override
  public Object resolveParameter(
      final ParameterContext parameterContext, final ExtensionContext extensionContext)
      throws ParameterResolutionException {
    return labelsToRemove;
  }
}
