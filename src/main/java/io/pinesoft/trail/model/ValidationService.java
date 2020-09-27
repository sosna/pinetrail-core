/*
 * Copyright (c) 2014, Xavier Sosnovsky <xso@sosna.ws>
 *
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES WITH
 * REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF MERCHANTABILITY
 * AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY SPECIAL, DIRECT,
 * INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER RESULTING FROM
 * LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT, NEGLIGENCE OR
 * OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE USE OR
 * PERFORMANCE OF THIS SOFTWARE.
 */
package io.pinesoft.trail.model;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Provides a method to instantiate validators.
 *
 * <p>According to the documentation of Hibernate Validator, it is recommended to work with one
 * factory instance within an application and this is the reason behind this singleton.
 *
 * <p>Both ValidatorFactory and Validator instances are thread-safe.
 *
 * @author Xavier Sosnovsky
 */
enum ValidationService {
  INSTANCE;

  private final transient ValidatorFactory factory;

  ValidationService() {
    factory = Validation.buildDefaultValidatorFactory();
  }

  Validator getValidator() {
    return factory.getValidator();
  }
}
