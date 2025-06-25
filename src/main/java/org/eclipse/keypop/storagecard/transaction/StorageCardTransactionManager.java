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

import org.eclipse.keypop.storagecard.CardIOException;
import org.eclipse.keypop.storagecard.ReaderIOException;
import org.eclipse.keypop.storagecard.UnexpectedCommandStatusException;
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
public interface StorageCardTransactionManager {

  /**
   * Prepares the reading of a specific block from the storage card.
   *
   * <p>Block addresses start at 0 and the maximum value is provided by {@link
   * ProductType#getBlockCount()}.
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
   * <p>Block addresses start at 0 and the maximum value is provided by {@link
   * ProductType#getBlockCount()}.
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
   * ProductType#getBlockCount()}.
   *
   * <p>Once this command is processed, the data will be available in {@link StorageCard} using the
   * dedicated block management methods.
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

  /**
   * Processes all previously prepared commands and closes the physical channel if requested.
   *
   * <p>All APDUs corresponding to the prepared commands are sent to the card, their responses are
   * retrieved and used to update the {@link StorageCard} associated with the transaction.
   *
   * <p>For write commands, the {@link StorageCard} is updated only when the command is successful.
   *
   * <p>The process is interrupted at the first failed command.
   *
   * @param channelControl Policy for managing the physical channel after executing commands to the
   *     card.
   * @return The current instance.
   * @throws ReaderIOException If a communication error with the card reader or the cryptographic
   *     module reader occurs.
   * @throws CardIOException If a communication error with the card occurs.
   * @throws UnexpectedCommandStatusException If one of the prepared command failed.
   * @since 1.0.0
   */
  StorageCardTransactionManager processCommands(ChannelControl channelControl)
      throws ReaderIOException, CardIOException, UnexpectedCommandStatusException;
}
