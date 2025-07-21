/* **************************************************************************************
 * Copyright (c) 2025 Calypso Networks Association https://calypsonet.org/
 *
 * See the NOTICE file(s) distributed with this work for additional information
 * regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the terms of the
 * MIT License which is available at https://opensource.org/licenses/MIT
 *
 * SPDX-License-Identifier: MIT
 ************************************************************************************** */
package org.eclipse.keypop.storagecard;

import org.eclipse.keypop.storagecard.card.StorageCard;

/**
 * Indicates that a command returned an unexpected or invalid status while interacting with the
 * {@link StorageCard}.
 *
 * <p>This exception is typically thrown when the card responds with a status code that does not
 * match the expected outcome of the executed command, suggesting that the operation could not be
 * completed as intended (e.g., write or read command rejected by the card).
 *
 * @since 1.0.0
 */
public final class UnexpectedCommandStatusException extends StorageCardException {

  /**
   * Creates a new exception indicating a card status code error during the execution of a storage
   * card command, with an underlying cause.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @param cause The underlying cause of the exception.
   * @since 1.0.0
   */
  public UnexpectedCommandStatusException(Integer blockAddress, String message, Throwable cause) {
    super(blockAddress, message, cause);
  }
}
