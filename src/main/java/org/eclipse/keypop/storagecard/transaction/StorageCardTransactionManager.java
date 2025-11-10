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
package org.eclipse.keypop.storagecard.transaction;

import org.eclipse.keypop.reader.transaction.spi.CardTransactionManager;
import org.eclipse.keypop.storagecard.card.ProductType;
import org.eclipse.keypop.storagecard.card.StorageCard;

/**
 * Provides methods to manage APDU exchanges with a storage card.
 *
 * <p>This interface allows to:
 *
 * <ul>
 *   <li>Prepare read and write operations to the card
 *   <li>Process prepared commands in a single transaction
 *   <li>Manage the communication channel with the card
 * </ul>
 *
 * @since 1.0.0
 */
public interface StorageCardTransactionManager extends CardTransactionManager {

  /**
   * Prepares the reading of the system block from the storage card when present.
   *
   * <p>Not all storage card types include a system block. This method should only be called for
   * card types that support system block access.
   *
   * <p>Once this command is processed, the result is available in {@link StorageCard}.
   *
   * @return The current instance.
   * @throws UnsupportedOperationException If the current card type does not support system block
   *     access.
   * @since 1.0.0
   */
  StorageCardTransactionManager prepareReadSystemBlock();

  /**
   * Prepares the writing of data to the system block of the storage card when present.
   *
   * <p>System blocks contain card-specific metadata and configuration data. Not all storage card
   * types include a system block that can be written to. This method should only be called for card
   * types that support system block write access.
   *
   * <p>The data length must match the block size defined by the card's {@link ProductType}.
   *
   * <p><strong>Important:</strong> After execution of this write command, the {@link StorageCard}
   * memory image is <strong>not automatically updated</strong>. Some storage card technologies do
   * not provide reliable status codes to confirm successful write operations. To ensure data
   * consistency, an explicit read operation must be performed after the write to refresh the memory
   * image and verify the actual content stored on the card.
   *
   * @param data The data to be written to the system block. The length must match the card's block
   *     size.
   * @return The current instance.
   * @throws IllegalArgumentException If data is null or its length does not match the block size.
   * @throws UnsupportedOperationException If the current card type does not support system block
   *     write access.
   * @see ProductType#getBlockSize()
   * @since 1.0.0
   */
  StorageCardTransactionManager prepareWriteSystemBlock(byte[] data);

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
  StorageCardTransactionManager prepareReadBlock(int blockAddress);

  /**
   * Prepares the reading of a range of blocks from the storage card.
   *
   * <p>Block addresses start at 0 and the maximum value is equal to {@link
   * ProductType#getBlockCount()} - 1.
   *
   * <p>Once this command is processed, the result is available in {@link StorageCard}.
   *
   * @param fromBlockAddress The starting block address (inclusive).
   * @param toBlockAddress The ending block address (inclusive).
   * @return The current instance.
   * @throws IllegalArgumentException If one of the arguments is out of range.
   * @see ProductType#getBlockCount()
   * @since 1.0.0
   */
  StorageCardTransactionManager prepareReadBlocks(int fromBlockAddress, int toBlockAddress);

  /**
   * Prepares the writing of blocks of data to the storage card starting from a specific block
   * number offset.
   *
   * <p>The provided data should be a byte array representing the content of the blocks to be
   * written. The number of blocks that will be written is determined by the length of the data
   * array divided by the block size of the storage card. The block size is provided by {@link
   * ProductType#getBlockSize()}.
   *
   * <p><strong>Important:</strong> Some storage card technologies do not provide reliable status
   * codes to confirm successful write operations. For such cards (e.g., SRT512/ST25), the library
   * automatically performs a verification read after each write to ensure that the data has been
   * correctly stored. For cards that provide a reliable write acknowledgment, no additional read is
   * performed. In all cases, the library guarantees the integrity of the written data, and the
   * application does not need to explicitly perform verification reads.
   *
   * @param fromBlockAddress The offset from which the blocks will be written.
   * @param data The data to be written to the storage card.
   * @return The current instance of the {@link StorageCardTransactionManager}.
   * @throws IllegalArgumentException If data is null or its length is not a multiple of the block
   *     size.
   * @see ProductType#getBlockCount()
   * @since 1.0.0
   */
  StorageCardTransactionManager prepareWriteBlocks(int fromBlockAddress, byte[] data);
}
