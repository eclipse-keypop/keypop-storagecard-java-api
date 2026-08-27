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
import org.eclipse.keypop.storagecard.MifareClassicKeyType;
import org.eclipse.keypop.storagecard.StorageCardApiFactory;
import org.eclipse.keypop.storagecard.card.ProductType;
import org.eclipse.keypop.storagecard.card.StorageCard;

/**
 * Manages the APDU exchanges with a {@link StorageCard}, obtained via {@link
 * StorageCardApiFactory#createStorageCardTransactionManager}.
 *
 * <p>Commands are prepared with the {@code prepare} operations and processed through the inherited
 * {@link CardTransactionManager#processCommands()} operation. The overloads carrying an {@code
 * idCommand} allow the failing command to be identified on the resulting exception via {@code
 * StorageCardException.getIdCommand()}.
 *
 * <p>See <a
 * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#type_StorageCardTransactionManager">StorageCardTransactionManager</a>
 * for the normative contract.
 *
 * @since 1.0.0
 */
public interface StorageCardTransactionManager extends CardTransactionManager {

  /**
   * Prepares the reading of a single block.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCardTransactionManager_prepareReadBlock">StorageCardTransactionManager.prepareReadBlock</a>
   * for the normative contract.
   *
   * @param blockAddress The address of the block to read.
   * @return The current instance.
   * @throws IllegalArgumentException If the block address is out of range for the card {@link
   *     ProductType}.
   * @since 1.0.0
   */
  StorageCardTransactionManager prepareReadBlock(int blockAddress);

  /**
   * Prepares the reading of a range of blocks.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCardTransactionManager_prepareReadBlocks">StorageCardTransactionManager.prepareReadBlocks</a>
   * for the normative contract.
   *
   * @param fromBlockAddress The address of the first block to read.
   * @param toBlockAddress The address of the last block to read.
   * @return The current instance.
   * @throws IllegalArgumentException If one of the block addresses is out of range for the card
   *     {@link ProductType} or if the range is invalid.
   * @since 1.0.0
   */
  StorageCardTransactionManager prepareReadBlocks(int fromBlockAddress, int toBlockAddress);

  /**
   * Prepares the writing of one or more consecutive blocks.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCardTransactionManager_prepareWriteBlocks">StorageCardTransactionManager.prepareWriteBlocks</a>
   * for the normative contract.
   *
   * @param fromBlockAddress The address of the first block to write.
   * @param data The data to write, representing the expected final state of the blocks.
   * @return The current instance.
   * @throws IllegalArgumentException If the block address is out of range for the card {@link
   *     ProductType} or if the data is null or of an invalid length.
   * @since 1.0.0
   */
  StorageCardTransactionManager prepareWriteBlocks(int fromBlockAddress, byte[] data);

  /**
   * Prepares the writing of one or more consecutive blocks, with an application-supplied command
   * identifier.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCardTransactionManager_prepareWriteBlocks_withId">StorageCardTransactionManager.prepareWriteBlocks</a>
   * for the normative contract.
   *
   * @param fromBlockAddress The address of the first block to write.
   * @param data The data to write, representing the expected final state of the blocks.
   * @param idCommand The application-supplied identifier of this command.
   * @return The current instance.
   * @throws IllegalArgumentException If the block address is out of range for the card {@link
   *     ProductType} or if the data is null or of an invalid length.
   * @since 2.0.0
   */
  StorageCardTransactionManager prepareWriteBlocks(
      int fromBlockAddress, byte[] data, int idCommand);

  /**
   * Prepares the reading of the system block of an ST25 / SRT512 card.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCardTransactionManager_prepareSt25ReadSystemBlock">StorageCardTransactionManager.prepareSt25ReadSystemBlock</a>
   * for the normative contract.
   *
   * @return The current instance.
   * @throws UnsupportedOperationException If the card {@link ProductType} has no system block.
   * @since 1.1.0
   */
  StorageCardTransactionManager prepareSt25ReadSystemBlock();

  /**
   * Prepares the writing of the system block of an ST25 / SRT512 card.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCardTransactionManager_prepareSt25WriteSystemBlock">StorageCardTransactionManager.prepareSt25WriteSystemBlock</a>
   * for the normative contract.
   *
   * @param data The data to write, representing the expected final state of the system block.
   * @return The current instance.
   * @throws IllegalArgumentException If the data is null or of an invalid length.
   * @throws UnsupportedOperationException If the card {@link ProductType} has no system block.
   * @since 1.1.0
   */
  StorageCardTransactionManager prepareSt25WriteSystemBlock(byte[] data);

  /**
   * Prepares the writing of the system block of an ST25 / SRT512 card, with an application-supplied
   * command identifier.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCardTransactionManager_prepareSt25WriteSystemBlock_withId">StorageCardTransactionManager.prepareSt25WriteSystemBlock</a>
   * for the normative contract.
   *
   * @param data The data to write, representing the expected final state of the system block.
   * @param idCommand The application-supplied identifier of this command.
   * @return The current instance.
   * @throws IllegalArgumentException If the data is null or of an invalid length.
   * @throws UnsupportedOperationException If the card {@link ProductType} has no system block.
   * @since 2.0.0
   */
  StorageCardTransactionManager prepareSt25WriteSystemBlock(byte[] data, int idCommand);

  /**
   * Prepares the authentication to a MIFARE Classic sector using the provided key value.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCardTransactionManager_prepareMifareClassicAuthenticate_withKey">StorageCardTransactionManager.prepareMifareClassicAuthenticate</a>
   * for the normative contract.
   *
   * @param blockAddress The address of a block of the targeted sector.
   * @param mifareClassicKeyType The key type to use.
   * @param key The key value.
   * @return The current instance.
   * @throws IllegalArgumentException If a parameter is null or out of range.
   * @throws UnsupportedOperationException If the card {@link ProductType} has no authentication.
   * @since 1.1.0
   */
  StorageCardTransactionManager prepareMifareClassicAuthenticate(
      int blockAddress, MifareClassicKeyType mifareClassicKeyType, byte[] key);

  /**
   * Prepares the authentication to a MIFARE Classic sector using a key referenced by its number in
   * the reader.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCardTransactionManager_prepareMifareClassicAuthenticate_withKeyNumber">StorageCardTransactionManager.prepareMifareClassicAuthenticate</a>
   * for the normative contract.
   *
   * @param blockAddress The address of a block of the targeted sector.
   * @param mifareClassicKeyType The key type to use.
   * @param keyNumber The number of the key to use.
   * @return The current instance.
   * @throws IllegalArgumentException If a parameter is null or out of range.
   * @throws UnsupportedOperationException If the card {@link ProductType} has no authentication.
   * @since 1.1.0
   */
  StorageCardTransactionManager prepareMifareClassicAuthenticate(
      int blockAddress, MifareClassicKeyType mifareClassicKeyType, int keyNumber);
}
