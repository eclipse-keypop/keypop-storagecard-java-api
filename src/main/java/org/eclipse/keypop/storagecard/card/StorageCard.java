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
 * @since 1.0.0
 */
public interface StorageCard extends SmartCard {

  /**
   * Returns the product type of the storage card.
   *
   * @return The product type of the storage card.
   * @since 1.0.0
   */
  ProductType getProductType();

  /**
   * Retrieves the unique identifier (UID) of the storage card.
   *
   * @return A byte array representing the UID of the storage card.
   */
  byte[] getUID();

  /**
   * Retrieves the system block from the storage card when available.
   *
   * <p>The system block contains card-specific metadata and configuration data such as access
   * control settings. Not all storage card types provide access to system blocks.
   *
   * <p>The system block must have been previously read using a the {{@link
   * StorageCardTransactionManager#prepareReadSystemBlock()}) method.
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
   * @param fromBlockAddress The starting block address (inclusive).
   * @param toBlockAddress The ending block address (inclusive).
   * @return A byte array containing the data blocks within the specified range. Unread blocks are
   *     represented as zero-filled sections in the returned array.
   * @throws IndexOutOfBoundsException If {@code fromBlockAddress} is greater than {@code
   *     toBlockAddress}, if either block address is negative, or if they exceed the available range
   *     of the memory image.
   */
  byte[] getBlocks(int fromBlockAddress, int toBlockAddress);
}
