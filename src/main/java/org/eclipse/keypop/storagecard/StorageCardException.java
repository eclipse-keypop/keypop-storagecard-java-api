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

import org.eclipse.keypop.storagecard.card.StorageCard;

/**
 * Interface for exceptions occurring during the execution of a command on a {@link StorageCard}.
 *
 * <p>It provides additional context about the block address involved in the error, when applicable.
 *
 * @since 1.0.0
 */
public interface StorageCardException {

  /**
   * Returns the address of the block involved in the error, if applicable.
   *
   * @return The block address that caused the error, or {@code null} if not relevant.
   * @since 1.0.0
   */
  Integer getBlockAddress();
}
