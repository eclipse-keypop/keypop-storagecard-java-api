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
 * Indicates an input/output error that occurred while communicating with the {@link StorageCard}.
 *
 * <p>This exception reflects errors in the card communication process, such as transmission
 * failures, card removal during processing, or any error preventing the expected response from the
 * card.
 *
 * <p>It is also thrown when an automatic verification read, performed after a write operation on
 * storage cards that do not provide reliable write acknowledgment, fails to confirm that the
 * expected data was correctly stored.
 *
 * @since 1.0.0
 */
public final class CardIOException extends CardCommunicationException
    implements StorageCardException {

  private final Integer blockAddress;

  /**
   * Creates a new exception indicating a card communication error during the execution of a storage
   * card command.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @since 1.0.0
   */
  public CardIOException(Integer blockAddress, String message) {
    super(message);
    this.blockAddress = blockAddress;
  }

  /**
   * Creates a new exception indicating a card communication error during the execution of a storage
   * card command, with an underlying cause.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @param cause The underlying cause of the exception.
   * @since 1.0.0
   */
  public CardIOException(Integer blockAddress, String message, Throwable cause) {
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
