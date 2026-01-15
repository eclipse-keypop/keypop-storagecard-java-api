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

import org.eclipse.keypop.reader.CardCommunicationException;
import org.eclipse.keypop.storagecard.card.StorageCard;

/**
 * Indicates that an authentication attempt on a {@link StorageCard} has failed.
 *
 * <p>This exception is thrown when authentication to a Mifare Classic sector fails, typically due
 * to incorrect key data or key type. Authentication is required before reading from or writing to
 * protected sectors on Mifare Classic cards.
 *
 * @since 1.1.0
 */
public final class SCAuthenticationFailedException extends CardCommunicationException
    implements StorageCardException {

  private final Integer blockAddress;

  /**
   * Creates a new exception indicating an authentication failure during the execution of a storage
   * card command.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @since 1.1.0
   */
  public SCAuthenticationFailedException(Integer blockAddress, String message) {
    super(message);
    this.blockAddress = blockAddress;
  }

  /**
   * Creates a new exception indicating an authentication failure during the execution of a storage
   * card command, with an underlying cause.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @param cause The underlying cause of the exception.
   * @since 1.1.0
   */
  public SCAuthenticationFailedException(Integer blockAddress, String message, Throwable cause) {
    super(message, cause);
    this.blockAddress = blockAddress;
  }

  /**
   * {@inheritDoc}
   *
   * @since 1.1.0
   */
  @Override
  public Integer getBlockAddress() {
    return blockAddress;
  }
}
