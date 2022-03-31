package com.practis.web.mapper.grid;

import static org.openqa.selenium.By.cssSelector;

import com.practis.web.mapper.GridMapper;
import com.practis.web.model.grid.PractisSetGrid;
import java.util.List;
import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class PractisSetGridRowMapper implements GridMapper<PractisSetGrid> {

  @Getter
  private final Class<PractisSetGrid> modelClass = PractisSetGrid.class;

  @Override
  public PractisSetGrid toModel(final WebElement rowElement, final String columnSelector) {
    return PractisSetGrid.builder()
        .rowElement(rowElement)
        .title(parseName(rowElement, columnSelector))
        .build();
  }

  private static String parseName(final WebElement row, final String columnsSelector) {
    return getFields(row, columnsSelector).get(1).getText();
  }

  private static List<WebElement> getFields(final WebElement row, final String columnsSelector) {
    return row.findElements(cssSelector(columnsSelector));
  }
}
