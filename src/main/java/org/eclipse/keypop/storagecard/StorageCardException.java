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
 * Interface implemented by every exception raised during the execution of a command on a {@link
 * StorageCard}, providing additional context about the failing command.
 *
 * <p>See <a
 * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#type_StorageCardException">StorageCardException</a>
 * for the normative contract.
 *
 * @since 1.0.0
 */
public interface StorageCardException {

  /**
   * Returns the address of the block involved in the error, when applicable.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCardException_getBlockAddress">StorageCardException.getBlockAddress</a>
   * for the normative contract.
   *
   * @return The block address that caused the error, or {@code null} if not relevant.
   * @since 1.0.0
   */
  Integer getBlockAddress();

  /**
   * Returns the application-supplied identifier of the command that caused the exception, when the
   * failing command was prepared with an {@code idCommand} overload.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCardException_getIdCommand">StorageCardException.getIdCommand</a>
   * for the normative contract.
   *
   * @return The command identifier, or {@code null} if no identifier was supplied for the failing
   *     command or if the exception is not attached to a specific command.
   * @since 2.0.0
   */
  Integer getIdCommand();
}
