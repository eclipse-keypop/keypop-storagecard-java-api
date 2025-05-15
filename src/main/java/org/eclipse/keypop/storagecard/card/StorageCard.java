/* ******************************************************************************
 * Copyright (c) 2025 Calypso Networks Association https://calypsonet.org/
 *
 * This program and the accompanying materials are made available under the
 * terms of the MIT License which is available at
 * https://opensource.org/licenses/MIT.
 *
 * SPDX-License-Identifier: MIT
 ****************************************************************************** */
package org.eclipse.keypop.storagecard.card;

import org.eclipse.keypop.reader.selection.spi.SmartCard;

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
   * Retrieves the data block at the specified block number.
   *
   * @param blockNumber The number of the block to retrieve
   * @return The data block as a byte array.
   * @throws IllegalArgumentException If the block number is out of range.
   * @since 1.0.0
   */
  byte[] getBlock(int blockNumber);

  /**
   * Retrieves the data blocks within the specified range from the memory image of the storage card.
   * The returned array contains the blocks in order, from {@code fromBlockNumber} to {@code
   * toBlockNumber}. If a block has not been previously read and stored in memory, its value is
   * replaced with 0s.
   *
   * @param fromBlockNumber The starting block number (inclusive).
   * @param toBlockNumber The ending block number (inclusive).
   * @return A byte array containing the data blocks within the specified range.
   * @throws IllegalArgumentException If {@code fromBlockNumber} is greater than {@code
   *     toBlockNumber}, if either block number is negative, or if they exceed the available range
   *     of the memory image.
   */
  byte[] getBlocks(int fromBlockNumber, int toBlockNumber);
}
