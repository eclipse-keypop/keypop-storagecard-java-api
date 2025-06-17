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
 * @since 1.0.0
 */
public interface StorageCardApiFactory {

  /**
   * Creates a new instance of {@link StorageCardSelectionExtension}.
   *
   * @param productType The targeted product type.
   * @return A new instance of {@link StorageCardSelectionExtension}.
   * @since 1.0.0
   */
  StorageCardSelectionExtension createStorageCardSelectionExtension(ProductType productType);

  /**
   * Creates an instance of {@link StorageCardTransactionManager}.
   *
   * @param reader The reader through which the card communicates.
   * @param card The initial card data provided by the selection process.
   * @return A not null reference.
   * @since 1.0.0
   */
  StorageCardTransactionManager createStorageCardTransactionManager(
      CardReader reader, StorageCard card);
}
