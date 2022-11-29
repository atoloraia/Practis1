package com.practis.support.extension.practis;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;

@Target({ElementType.METHOD})
@Retention(RUNTIME)
@ExtendWith({
    CreateTeamWithUsersExtension.class
})
public @interface TeamExtensionWithUsers {

  /**
   * Number of Users to signUp.
   */
  int users() default 0;

  /**
   * Number of PractisSets to create.
   */
  int practisSets() default 0;
}
