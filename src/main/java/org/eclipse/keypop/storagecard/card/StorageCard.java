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
   * Retrieves the data block at the specified block address.
   *
   * @param blockAddress The address of the block to retrieve
   * @return The data block as a byte array.
   * @throws IllegalArgumentException If the block address is out of range.
   * @since 1.0.0
   */
  byte[] getBlock(int blockAddress);

  /**
   * Retrieves the data blocks within the specified range from the memory image of the storage card.
   * The returned array contains the blocks in order, from {@code fromBlockAddress} to {@code
   * toBlockAddress}. If a block has not been previously read and stored in memory, its value is
   * replaced with 0s.
   *
   * @param fromBlockAddress The starting block address (inclusive).
   * @param toBlockAddress The ending block address (inclusive).
   * @return A byte array containing the data blocks within the specified range.
   * @throws IllegalArgumentException If {@code fromBlockAddress} is greater than {@code
   *     toBlockAddress}, if either block address is negative, or if they exceed the available range
   *     of the memory image.
   */
  byte[] getBlocks(int fromBlockAddress, int toBlockAddress);
}
