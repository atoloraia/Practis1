package com.practis.web.component;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;
import static org.openqa.selenium.support.How.CSS;

import com.practis.configuration.selenium.support.PractisPage;
import com.practis.web.mapper.NewItemMapper;
import com.practis.web.model.NewItemElement;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

@PractisPage
public class NewItemComponent {

  private final NewItemMapper newItemMapper;

  @FindBy(how = CSS, css = "div[data-test='actionDropDownToggleButton']")
  private WebElement newEntitySelector;

  @FindBy(how = CSS, css = "a[data-test='dropDownListLink']")
  private List<WebElement> newEntityRows;

  NewItemComponent(
      final AjaxElementLocatorFactory locatorFactory,
      final NewItemMapper newItemMapper) {
    this.newItemMapper = newItemMapper;
    PageFactory.initElements(locatorFactory, this);
  }

  public NewItemComponent clickNewItem() {
    newEntitySelector.click();
    return this;
  }

  /**
   * Click row under new item dropdown.
   */
  public void clickRow(final String name) {
    final var dropdownItems = newItemMapper.toItem(newEntityRows);
    dropdownItems.stream()
        .filter(item -> item.getName().equals(name))
        .findFirst()
        .ifPresentOrElse(item -> item.getElement().click(),
            () -> {
              throw new RuntimeException(
                  format("Can't find row '%s' under new item dropdown. Available items: '%s'",
                      name,
                      dropdownItems.stream().map(NewItemElement::getName).collect(joining(","))));
            });
  }

}
