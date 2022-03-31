package com.practis.web.mapper.grid;

import static java.util.Collections.emptyList;
import static org.openqa.selenium.By.cssSelector;

import com.practis.utils.WebGridUtils;
import com.practis.web.mapper.GridMapper;
import com.practis.web.model.grid.ChallengeGridRow;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

@Component
public class ChallengeGridRowMapper implements GridMapper<ChallengeGridRow> {

  @Getter
  private final Class<ChallengeGridRow> modelClass = ChallengeGridRow.class;

  @Override
  public ChallengeGridRow toModel(
      final WebElement rowElement,
      final String columnSelector) {
    return ChallengeGridRow.builder()
        .rowElement(rowElement)
        .name(parseName(rowElement, columnSelector))
        .lastUpdated(parseLastUpdated(rowElement, columnSelector))
        .build();
  }

  public static String titleFromGridRow(
      final WebElement row,
      final String columnsSelector) {
    return parseName(row, columnsSelector);
  }

  private static List<String> parseLabels(
      final WebElement row, final WebElement labelsRow, final String columnsSelector) {
    getFields(row, columnsSelector).get(3).click();
    if (labelsRow.getTagName().equals("empty")) {
      return emptyList();
    }
    return labelsRow.findElements(By.xpath(".//td")).stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  private static String parseName(final WebElement row, final String columnsSelector) {
    return getFields(row, columnsSelector).get(1).getText();
  }

  private static LocalDateTime parseLastUpdated(final WebElement row,
      final String columnsSelector) {
    return WebGridUtils.parseDate(getFields(row, columnsSelector).get(2).getText());
  }

  private static List<WebElement> getFields(final WebElement row, final String columnsSelector) {
    return row.findElements(cssSelector(columnsSelector));
  }
}
