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

import org.eclipse.keypop.reader.ReaderCommunicationException;
import org.eclipse.keypop.storagecard.card.StorageCard;

/**
 * Indicates an input/output error that occurred while communicating with the card reader.
 *
 * <p>This exception reflects low-level reader communication failures, such as a lost connection,
 * hardware malfunction, or driver-related issues, preventing the command from being transmitted to
 * the {@link StorageCard}.
 *
 * @since 1.0.0
 */
public final class SCReaderCommunicationException extends ReaderCommunicationException
    implements StorageCardException {

  private final Integer blockAddress;

  /**
   * Creates a new exception indicating a reader communication error during the execution of a
   * storage card command.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @since 1.0.0
   */
  public SCReaderCommunicationException(Integer blockAddress, String message) {
    super(message);
    this.blockAddress = blockAddress;
  }

  /**
   * Creates a new exception indicating a reader communication error during the execution of a
   * storage card command, with an underlying cause.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @param cause The underlying cause of the exception.
   * @since 1.0.0
   */
  public SCReaderCommunicationException(Integer blockAddress, String message, Throwable cause) {
    super(message, cause);
    this.blockAddress = blockAddress;
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.0.0
   */
  @Override
  public Integer getBlockAddress() {
    return blockAddress;
  }
}
