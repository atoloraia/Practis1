package com.practis.configuration.testrail;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TestRailStatus {

  PASSED(1),
  BLOCKED(2),
  UNTESTED(3),
  RETEST(4),
  FAILED(5);

  private final int statusId;
}
