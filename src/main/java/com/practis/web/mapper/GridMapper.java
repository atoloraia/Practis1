package com.practis.web.mapper;

import org.openqa.selenium.WebElement;

public interface GridMapper<T> {

  Class<T> getModelClass();

  T toModel(WebElement rowElement, String columnSelector);

}
