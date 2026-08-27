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

/**
 * Storage Card API properties.
 *
 * <p>See <a
 * href="https://docs.terminal-api.calypsonet.org/calypsonet-terminal-storagecard-uml-api/2.0.0-SNAPSHOT/YYMMDD-SP-CNATerminalAPI-StorageCard_v2.0.0-SNAPSHOT.html#type_StorageCardApiProperties">StorageCardApiProperties</a>
 * for the normative contract.
 *
 * @since 1.0.0
 */
public final class StorageCardApiProperties {

  /**
   * Version of the API implemented by this binding, as a "MAJOR.MINOR" dotted decimal: {@value}
   *
   * @since 1.0.0
   */
  public static final String VERSION = "2.0";

  /** Private constructor */
  private StorageCardApiProperties() {}
}
