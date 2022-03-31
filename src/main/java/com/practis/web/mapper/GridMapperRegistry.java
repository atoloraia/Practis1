package com.practis.web.mapper;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GridMapperRegistry {

  private final List<GridMapper<?>> mappers;

  /**
   * Maps grid row to model object.
   */
  public <T> T getModel(WebElement rowElement, final String columnSelector, Class<T> modelClass) {
    final var mapper = mappers.stream()
        .filter(it -> it.getModelClass() == modelClass)
        .findFirst().orElseThrow(() -> new RuntimeException("Grid row mapper not found"));

    return (T) mapper.toModel(rowElement, columnSelector);
  }

}
