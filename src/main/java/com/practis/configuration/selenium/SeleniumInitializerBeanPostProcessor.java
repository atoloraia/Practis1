package com.practis.configuration.selenium;

import com.practis.configuration.selenium.locator.CustomAjaxElementLocatorFactory;
import com.practis.configuration.selenium.support.PractisPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SeleniumInitializerBeanPostProcessor implements BeanPostProcessor {

  private static final int SELECTOR_LOAD_TIMEOUT = 3;
  private WebDriver driver;

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName)
      throws BeansException {
    if (bean instanceof WebDriver) {
      driver = (WebDriver) bean;
    }
    return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
    final var beanClass = bean.getClass();
    if (beanClass.isAnnotationPresent(PractisPage.class)) {
      final var locatorFactory = new CustomAjaxElementLocatorFactory(driver, SELECTOR_LOAD_TIMEOUT);
      PageFactory.initElements(locatorFactory, bean);
    }
    return bean;
  }
}
