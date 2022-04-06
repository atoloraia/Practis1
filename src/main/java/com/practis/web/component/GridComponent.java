package com.practis.web.component;

import static com.practis.utils.AwaitUtils.awaitSoft;
import static java.lang.String.format;
import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.web.mapper.GridMapperRegistry;
import com.practis.web.model.GridRow;
import java.util.List;
import java.util.stream.Collectors;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

@PractisPage
public class GridComponent {

  private final GridMapperRegistry mapperRegistry;

  @FindBy(how = CSS, css = "tbody tr[data-test='tableRow']")
  protected List<WebElement> rowElements;

  GridComponent(
      final AjaxElementLocatorFactory locatorFactory,
      final GridMapperRegistry mapperRegistry) {
    this.mapperRegistry = mapperRegistry;
    PageFactory.initElements(locatorFactory, this);
  }

  public <T> List<T> getRows(final Class<T> modelClass, final int expectedNumber) {
    awaitSoft(20, () -> rowElements.size() == expectedNumber);
    return getRows(modelClass);
  }

  /**
   * Returns parsed grid rows.
   */
  public <T> List<T> getRows(final Class<T> modelClass) {
    awaitSoft(20, () -> rowElements.size() > 0);
    return rowElements.stream()
        .map(rowElement -> mapperRegistry.getModel(
            rowElement, "td[data-test='table-cell']", modelClass))
        .map(modelClass::cast)
        .collect(Collectors.toList());
  }

  /**
   * Click on row.
   */
  public <T extends GridRow> void click(final Class<T> modelClass, final GridRow row) {
    getRows(modelClass).stream()
        .filter(gridRow -> gridRow.equals(row))
        .findFirst()
        .ifPresentOrElse(gridRow -> gridRow.getRowElement().click(),
            () -> {
              throw new RuntimeException(format("Can't find row '%s' in grid", row));
            });
  }
}
