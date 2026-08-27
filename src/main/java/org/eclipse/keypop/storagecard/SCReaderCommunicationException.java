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
 * Indicates a low-level reader communication failure — lost connection, hardware malfunction,
 * driver issue — preventing the command from being transmitted to the {@link StorageCard}.
 *
 * <p>See <a
 * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#type_SCReaderCommunicationException">SCReaderCommunicationException</a>
 * for the normative contract.
 *
 * @since 1.0.0
 */
public final class SCReaderCommunicationException extends ReaderCommunicationException
    implements StorageCardException {

  private final Integer blockAddress;
  private final Integer idCommand;

  /**
   * Creates a new exception indicating a reader communication error during the execution of a
   * storage card command.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @since 1.0.0
   */
  public SCReaderCommunicationException(Integer blockAddress, String message) {
    this(blockAddress, null, message);
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
    this(blockAddress, null, message, cause);
  }

  /**
   * Creates a new exception indicating a reader communication error during the execution of a
   * storage card command identified by the provided command identifier.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param idCommand The identifier of the failing command, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @since 2.0.0
   */
  public SCReaderCommunicationException(Integer blockAddress, Integer idCommand, String message) {
    super(message);
    this.blockAddress = blockAddress;
    this.idCommand = idCommand;
  }

  /**
   * Creates a new exception indicating a reader communication error during the execution of a
   * storage card command identified by the provided command identifier, with an underlying cause.
   *
   * @param blockAddress The block address involved in the error, or {@code null} if not relevant.
   * @param idCommand The identifier of the failing command, or {@code null} if not relevant.
   * @param message The message describing the exception context.
   * @param cause The underlying cause of the exception.
   * @since 2.0.0
   */
  public SCReaderCommunicationException(
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
