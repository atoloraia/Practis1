package com.practis.support.extension.practis;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import org.junit.jupiter.api.extension.ExtendWith;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RUNTIME)
@ExtendWith({GenerateDraftNameExtension.class})
public @interface GeneratedDraftNameExtension {}
