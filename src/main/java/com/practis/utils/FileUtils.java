package com.practis.utils;

import java.io.File;
import java.net.URL;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FileUtils {

  @SneakyThrows
  public static File fromResource(final URL resource) {
    return new File(resource.toURI());
  }
}
