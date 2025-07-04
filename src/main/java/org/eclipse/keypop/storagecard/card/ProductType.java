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

/**
 * The ProductType enum represents the different types of storage cards supported by the library.
 *
 * @since 1.0.0
 */
public enum ProductType {

  /**
   * NXP Mifare Ultralight
   *
   * @since 1.0.0
   */
  MIFARE_ULTRALIGHT(16, 4, false),

  /**
   * ST Microelectronics ST25 / SRT512
   *
   * @since 1.0.0
   */
  ST25_SRT512(16, 4, true);

  private final int blockCount;
  private final int blockSize;
  private final boolean hasSystemBlock;

  /**
   * Constructor.
   *
   * @param blockCount The number of blocks in the storage card.
   * @param blockSize The size of each block in bytes.
   * @param hasSystemBlock Whether this card type has an accessible system block.
   */
  ProductType(int blockCount, int blockSize, boolean hasSystemBlock) {
    this.blockCount = blockCount;
    this.blockSize = blockSize;
    this.hasSystemBlock = hasSystemBlock;
  }

  /**
   * Returns the number of blocks in a storage card of a specific type.
   *
   * <p>This number reflects the quantity of blocks contained in the main memory area. Please note
   * that there might be additional "system" blocks not included in this count.
   *
   * @return The number of blocks in the storage card.
   * @since 1.0.0
   */
  public int getBlockCount() {
    return blockCount;
  }

  /**
   * Returns the size of each block in bytes.
   *
   * @return The size of each block in bytes.
   * @since 1.0.0
   */
  public int getBlockSize() {
    return blockSize;
  }

  /**
   * Indicates whether this card type has an accessible system block.
   *
   * <p>For ST25/SRT512 cards, the system block is accessible at address 255. For MIFARE Ultralight
   * cards, no system block is accessible through the API.
   *
   * <p>When a system block is available, it can be read using the appropriate prepare methods
   * during card selection or transaction processing.
   *
   * @return true if this card type has an accessible system block, false otherwise.
   * @since 1.0.0
   */
  public boolean hasSystemBlock() {
    return hasSystemBlock;
  }
}
