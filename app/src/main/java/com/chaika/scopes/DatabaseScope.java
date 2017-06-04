package com.chaika.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Los Scopes en Dagger2 facilitan la reutilzación de recursos.
 *
 * Created by Gato on 01/05/2017.
 */
@Scope
@Retention(RetentionPolicy.CLASS)
public @interface DatabaseScope {
}
