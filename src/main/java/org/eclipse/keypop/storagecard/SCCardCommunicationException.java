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
 * Indicates an input/output error during the dialog with the {@link StorageCard} — transmission
 * failure, card removal during processing, or failed automatic verification read after a write
 * operation on cards lacking reliable write acknowledgment.
 *
 * <p>See <a
 * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#type_SCCardCommunicationException">SCCardCommunicationException</a>
 * for the normative contract.
 *
 * @since 1.0.0
 */
public final class SCCardCommunicationException extends CardCommunicationException
    implements StorageCardException {

  private final Integer blockAddress;
  private final Integer idCommand;

  /**
   * Creates a new exception indicating a card communication error during the execution of a storage
   * card command.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @since 1.0.0
   */
  public SCCardCommunicationException(Integer blockAddress, String message) {
    this(blockAddress, null, message);
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
  public SCCardCommunicationException(Integer blockAddress, String message, Throwable cause) {
    this(blockAddress, null, message, cause);
  }

  /**
   * Creates a new exception indicating a card communication error during the execution of a storage
   * card command identified by the provided command identifier.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param idCommand The identifier of the failing command, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @since 2.0.0
   */
  public SCCardCommunicationException(Integer blockAddress, Integer idCommand, String message) {
    super(message);
    this.blockAddress = blockAddress;
    this.idCommand = idCommand;
  }

  /**
   * Creates a new exception indicating a card communication error during the execution of a storage
   * card command identified by the provided command identifier, with an underlying cause.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param idCommand The identifier of the failing command, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @param cause The underlying cause of the exception.
   * @since 2.0.0
   */
  public SCCardCommunicationException(
      Integer blockAddress, Integer idCommand, String message, Throwable cause) {
    super(message, cause);
    this.blockAddress = blockAddress;
    this.idCommand = idCommand;
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

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public Integer getIdCommand() {
    return idCommand;
  }
}
