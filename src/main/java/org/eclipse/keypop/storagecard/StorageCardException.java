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
 * Base class for exceptions occurring during the execution of a command on a {@link StorageCard}.
 *
 * <p>It provides additional context about the block address involved in the error, when applicable.
 *
 * @since 1.0.0
 */
public abstract class StorageCardException extends RuntimeException {
  private final Integer blockAddress;

  /**
   * Creates a new exception indicating an error during the execution of a storage card command.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @since 1.0.0
   */
  StorageCardException(Integer blockAddress, String message) {
    super(message);
    this.blockAddress = blockAddress;
  }

  /**
   * Creates a new exception indicating an error during the execution of a storage card command,
   * with an underlying cause.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @param cause The underlying cause of the exception.
   * @since 1.0.0
   */
  StorageCardException(Integer blockAddress, String message, Throwable cause) {
    super(message, cause);
    this.blockAddress = blockAddress;
  }

  /**
   * Returns the address of the block involved in the error, if applicable.
   *
   * @return The block address that caused the error, or {@code null} if not relevant.
   * @since 1.0.0
   */
  Integer getBlockAddress() {
    return blockAddress;
  }
}
