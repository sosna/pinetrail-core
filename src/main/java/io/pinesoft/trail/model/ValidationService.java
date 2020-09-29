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
