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

import org.eclipse.keypop.reader.selection.spi.CardSelectionExtension;

/**
 * Extends the {@link CardSelectionExtension} interface of the "Keypop Reader API" to provide means
 * to define optional commands to be executed during the selection phase.
 *
 * @since 1.0.0
 */
public interface StorageCardSelectionExtension extends CardSelectionExtension {

  /**
   * Prepares the reading of a specific block from the storage card.
   *
   * <p>Block addresses start at 0 and the maximum value is equal to {@link
   * ProductType#getBlockCount()} - 1.
   *
   * <p>Once this command is processed, the result is available in {@link StorageCard}.
   *
   * @param blockAddress The address of the block to be read.
   * @return The current instance.
   * @throws IllegalArgumentException If the block address is out of range.
   * @see ProductType#getBlockCount()
   * @since 1.0.0
   */
  StorageCardSelectionExtension prepareReadBlock(int blockAddress);

  /**
   * Prepares the reading of a range of blocks from the storage card.
   *
   * <p>Block addresses start at 0 and the maximum value is equal to {@link
   * ProductType#getBlockCount()} - 1.
   *
   * <p>Once this command is processed, the result is available in {@link StorageCard} via {@link
   * StorageCard#getBlock} and {@link StorageCard#getBlocks(int, int)} methods.
   *
   * @param fromBlockAddress The starting block address (inclusive).
   * @param toBlockAddress The ending block address (inclusive).
   * @return The current instance.
   * @throws IllegalArgumentException If one of the arguments is out of range.
   * @see ProductType#getBlockCount()
   * @since 1.0.0
   */
  StorageCardSelectionExtension prepareReadBlocks(int fromBlockAddress, int toBlockAddress);
}
