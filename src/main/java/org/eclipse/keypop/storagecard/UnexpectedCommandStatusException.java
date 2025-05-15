/* ******************************************************************************
 * Copyright (c) 2025 Calypso Networks Association https://calypsonet.org/
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at
 * https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 ****************************************************************************** */
package org.eclipse.keypop.storagecard;

/**
 * Indicates that an unexpected command status was returned by the card.
 *
 * @since 1.0.0
 */
public final class UnexpectedCommandStatusException extends RuntimeException {

  /**
   * @param message Message to identify the exception context.
   * @param cause The cause.
   * @since 1.0.0
   */
  public UnexpectedCommandStatusException(String message, Throwable cause) {
    super(message, cause);
  }
}
