/**
 * Provides the contracts to read and write GPS data from/to a variety of formats.
 *
 * <p>Pinetrail uses the service provider interface (SPI) available since Java SE 1.6 to allow
 * deploying new modules without modifying the original code base. Modules can be discovered by the
 * application, provided they create the appropriate configuration file below the META-INF/services
 * folder of their jar file. Please refer to the Java documentation for additional information about
 * SPI.
 *
 * @author Xavier Sosnovsky
 */
package io.pinesoft.trail.api.io;
