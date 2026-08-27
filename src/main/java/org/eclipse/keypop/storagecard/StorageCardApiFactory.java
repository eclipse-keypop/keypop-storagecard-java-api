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

import org.eclipse.keypop.reader.CardReader;
import org.eclipse.keypop.storagecard.card.ProductType;
import org.eclipse.keypop.storagecard.card.StorageCard;
import org.eclipse.keypop.storagecard.card.StorageCardSelectionExtension;
import org.eclipse.keypop.storagecard.transaction.StorageCardTransactionManager;

/**
 * Storage Card API Factory.
 *
 * <p>See <a
 * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#type_StorageCardApiFactory">StorageCardApiFactory</a>
 * for the normative contract.
 *
 * @since 1.0.0
 */
public interface StorageCardApiFactory {

  /**
   * Creates a new instance of {@link StorageCardSelectionExtension}.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCardApiFactory_createStorageCardSelectionExtension">StorageCardApiFactory.createStorageCardSelectionExtension</a>
   * for the normative contract.
   *
   * @param productType The targeted product type.
   * @return A new instance of {@link StorageCardSelectionExtension}.
   * @since 1.0.0
   */
  StorageCardSelectionExtension createStorageCardSelectionExtension(ProductType productType);

  /**
   * Creates an instance of {@link StorageCardTransactionManager}.
   *
   * <p>See <a
   * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#op_StorageCardApiFactory_createStorageCardTransactionManager">StorageCardApiFactory.createStorageCardTransactionManager</a>
   * for the normative contract.
   *
   * @param reader The reader through which the card communicates.
   * @param card The initial card data provided by the selection process.
   * @return A not null reference.
   * @since 1.0.0
   */
  StorageCardTransactionManager createStorageCardTransactionManager(
      CardReader reader, StorageCard card);
}
