package com.mindhub.todolist.controller.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@org.springframework.security.test.context.support.WithMockUser(authorities = {"USER", "ADMIN"})
public @interface WithMockUser {

}
