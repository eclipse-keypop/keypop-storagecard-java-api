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

import org.eclipse.keypop.reader.ChannelControl;
import org.eclipse.keypop.reader.transaction.spi.CardTransactionManager;
import org.eclipse.keypop.storagecard.MifareClassicKeyType;
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
 * <p><b>Note about processing commands</b>
 *
 * <p>The inherited {@link #processCommands(ChannelControl)} method processes all previously
 * prepared commands and closes the physical channel if requested.
 *
 * <p>All APDUs corresponding to the prepared commands are sent to the card, their responses are
 * retrieved and used to update the {@link StorageCard} associated with the transaction.
 *
 * <p><strong>For read commands:</strong> The {@link StorageCard} memory image is updated with the
 * data retrieved from the card.
 *
 * <p><strong>For write commands:</strong> The {@link StorageCard} memory image is <strong>not
 * updated</strong> even when the command appears successful, as some storage card technologies do
 * not provide reliable confirmation of write completion. Applications should perform explicit read
 * operations after writes to verify the actual card content and update the memory image.
 *
 * <p>The process is interrupted at the first failed command.
 *
 * @since 1.0.0
 */
public interface StorageCardTransactionManager
    extends CardTransactionManager<StorageCardTransactionManager> {

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
   * @deprecated Use {@link #prepareSt25ReadSystemBlock()} instead. This method will be removed in a
   *     future version.
   */
  @Deprecated
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
   * @deprecated Use {@link #prepareSt25WriteSystemBlock(byte[])} instead. This method will be
   *     removed in a future version.
   */
  @Deprecated
  StorageCardTransactionManager prepareWriteSystemBlock(byte[] data);

  /**
   * Prepares the reading of the system block from an ST25/SRT512 storage card.
   *
   * <p>This method is specific to ST25 and SRT512 card types which provide access to a system block
   * at address 255 containing card-specific metadata and configuration data.
   *
   * <p>Once this command is processed, the result is available in {@link StorageCard}.
   *
   * @return The current instance.
   * @throws UnsupportedOperationException If the current card type is not ST25/SRT512.
   * @since 1.1.0
   */
  StorageCardTransactionManager prepareSt25ReadSystemBlock();

  /**
   * Prepares the writing of data to the system block of an ST25/SRT512 storage card.
   *
   * <p>This method is specific to ST25 and SRT512 card types which provide access to a system block
   * at address 255 containing card-specific metadata and configuration data.
   *
   * <p>The data length must match the block size defined by the card's {@link ProductType}.
   *
   * <p><strong>Important:</strong> After execution of this write command, the {@link StorageCard}
   * memory image is <strong>not automatically updated</strong>. ST25/SRT512 cards do not provide
   * reliable status codes to confirm successful write operations. To ensure data consistency, an
   * explicit read operation must be performed after the write to refresh the memory image and
   * verify the actual content stored on the card.
   *
   * @param data The data to be written to the system block. The length must match the card's block
   *     size.
   * @return The current instance.
   * @throws IllegalArgumentException If data is null or its length does not match the block size.
   * @throws UnsupportedOperationException If the current card type is not ST25/SRT512.
   * @see ProductType#getBlockSize()
   * @since 1.1.0
   */
  StorageCardTransactionManager prepareSt25WriteSystemBlock(byte[] data);

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

  /**
   * Prepares a Mifare Classic authentication command using a provided key.
   *
   * <p>This method is specific to Mifare Classic cards and must be called before reading from or
   * writing to protected sectors. The authentication applies to the entire sector containing the
   * specified block address.
   *
   * <p>The key must be a 6-byte array representing the Mifare Classic key value.
   *
   * <p>When the key value is provided this way, it will be sent to the reader to be stored as a
   * volatile key at index 0 (see Load Key command of the PC/SC standard). This volatile key is
   * temporary and will be erased after usage, when the reader is powered off.
   *
   * <p><strong>Security Note:</strong> This method transmits the key value over the communication
   * channel between the application and the reader. For production environments and
   * security-sensitive applications, it is recommended to use {@link
   * #prepareMifareClassicAuthenticate(int, MifareClassicKeyType, int)} instead, which references a
   * pre-stored key in the reader without transmitting the key value.
   *
   * <p>Once authenticated, subsequent read and write operations within the same sector can be
   * performed without re-authentication, until the card is removed from the field or another sector
   * is accessed.
   *
   * @param blockAddress The address of any block within the sector to authenticate.
   * @param mifareClassicKeyType The type of key to use (Key A or Key B).
   * @param key The 6-byte key data for authentication.
   * @return The current instance.
   * @throws IllegalArgumentException If the block address is out of range, or if the key is null or
   *     not exactly 6 bytes long.
   * @throws UnsupportedOperationException If the current card type does not support authentication.
   * @since 1.1.0
   */
  StorageCardTransactionManager prepareMifareClassicAuthenticate(
      int blockAddress, MifareClassicKeyType mifareClassicKeyType, byte[] key);

  /**
   * Prepares a Mifare Classic authentication command using a key stored in the reader.
   *
   * <p>This method is specific to Mifare Classic cards and must be called before reading from or
   * writing to protected sectors. The authentication applies to the entire sector containing the
   * specified block address.
   *
   * <p>The key is referenced by its storage index in the reader's key storage. This allows using
   * pre-configured keys without transmitting them over the communication channel, providing
   * enhanced security.
   *
   * <p>Once authenticated, subsequent read and write operations within the same sector can be
   * performed without re-authentication, until the card is removed from the field or another sector
   * is accessed.
   *
   * @param blockAddress The address of any block within the sector to authenticate.
   * @param mifareClassicKeyType The type of key to use (Key A or Key B).
   * @param keyNumber The index of the key in the reader's key storage.
   * @return The current instance.
   * @throws IllegalArgumentException If the block address is out of range, or if the key number is
   *     invalid.
   * @throws UnsupportedOperationException If the current card type does not support authentication.
   * @since 1.1.0
   */
  StorageCardTransactionManager prepareMifareClassicAuthenticate(
      int blockAddress, MifareClassicKeyType mifareClassicKeyType, int keyNumber);
}
