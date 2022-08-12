package com.practis.support.extension.practis;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;

@Target({ElementType.METHOD})
@Retention(RUNTIME)
@ExtendWith({
    InviteUsersExtension.class
})
public @interface InviteUserExtension {

  /**
   * Limit.
   */
  int limit();

  /**
   * Company.
   * todo get company from current user settings
   */
  String company();

  /**
   * Role.
   * //todo search role by name
   */
  int role();
}
