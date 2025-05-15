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
  MIFARE_ULTRALIGHT(16, 4),

  /**
   * ST Microelectronics ST25 / SRT512
   *
   * @since 1.0.0
   */
  ST25_SRT512(16, 4);

  private final int blockCount;
  private final int blockSize;

  /**
   * Constructor.
   *
   * @param blockCount The number of blocks in the storage card.
   * @param blockSize The size of each block in bytes.
   */
  ProductType(int blockCount, int blockSize) {
    this.blockCount = blockCount;
    this.blockSize = blockSize;
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
}
