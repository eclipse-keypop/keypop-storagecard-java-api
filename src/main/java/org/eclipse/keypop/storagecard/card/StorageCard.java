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
package org.eclipse.keypop.storagecard.card;

import org.eclipse.keypop.reader.selection.spi.SmartCard;
import org.eclipse.keypop.storagecard.transaction.StorageCardTransactionManager;

/**
 * Represents a storage card with various methods to retrieve information and data from it.
 *
 * <p>See <a
 * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#type_StorageCard">StorageCard</a>
 * for the normative contract.
 *
 * @since 1.0.0
 */
public interface StorageCard extends SmartCard {

  /**
   * Returns the product type of the storage card.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCard_getProductType">StorageCard.getProductType</a>
   * for the normative contract.
   *
   * @return The product type of the storage card.
   * @since 1.0.0
   */
  ProductType getProductType();

  /**
   * Retrieves the unique identifier (UID) of the storage card.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCard_getUID">StorageCard.getUID</a>
   * for the normative contract.
   *
   * @return A byte array representing the UID of the storage card.
   * @since 1.0.0
   */
  byte[] getUID();

  /**
   * Retrieves the system block from the storage card when available.
   *
   * <p>The system block contains card-specific metadata and configuration data such as access
   * control settings. This feature is specific to ST25/SRT512 cards which provide access to a
   * system block at address 255.
   *
   * <p>The system block must have been previously read using the {@link
   * StorageCardTransactionManager#prepareSt25ReadSystemBlock()} method.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCard_getSystemBlock">StorageCard.getSystemBlock</a>
   * for the normative contract.
   *
   * @return The system block data as a byte array, or null if the system block has not been read
   *     yet.
   * @throws UnsupportedOperationException If the current card type does not support system block
   *     access.
   * @since 1.0.0
   */
  byte[] getSystemBlock();

  /**
   * Retrieves the data block at the specified block address.
   *
   * <p>If the block has not been previously read and stored in memory, the returned byte array will
   * be filled with zeros.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCard_getBlock">StorageCard.getBlock</a>
   * for the normative contract.
   *
   * @param blockAddress The address of the block to retrieve
   * @return The data block as a byte array, or a zero-filled byte array if the block has not been
   *     read yet.
   * @throws IndexOutOfBoundsException If the block address is out of range.
   * @since 1.0.0
   */
  byte[] getBlock(int blockAddress);

  /**
   * Retrieves the data blocks within the specified range from the memory image of the storage card.
   *
   * <p>The returned array contains the blocks in order, from {@code fromBlockAddress} to {@code
   * toBlockAddress}. If a block has not been previously read and stored in memory, its
   * corresponding bytes in the returned array will be filled with zeros.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCard_getBlocks">StorageCard.getBlocks</a>
   * for the normative contract.
   *
   * @param fromBlockAddress The starting block address (inclusive).
   * @param toBlockAddress The ending block address (inclusive).
   * @return A byte array containing the data blocks within the specified range. Unread blocks are
   *     represented as zero-filled sections in the returned array.
   * @throws IndexOutOfBoundsException If {@code fromBlockAddress} is greater than {@code
   *     toBlockAddress}, if either block address is negative, or if they exceed the available range
   *     of the memory image.
   * @since 1.0.0
   */
  byte[] getBlocks(int fromBlockAddress, int toBlockAddress);
}
