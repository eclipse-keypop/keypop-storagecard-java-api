/**
 * Contains the factories, builders, and exceptions for the Storage Card API.
 *
 * <p>This package provides the main entry points for interacting with storage cards:
 *
 * <ul>
 *   <li>{@link org.eclipse.keypop.storagecard.StorageCardApiFactory} - Factory for creating
 *       selection extensions and transaction managers
 *   <li>{@link org.eclipse.keypop.storagecard.MifareClassicKeyType} - Enumeration of Mifare Classic
 *       key types
 *   <li>{@link org.eclipse.keypop.storagecard.StorageCardException} - Base interface for all
 *       storage card exceptions
 *   <li>{@link org.eclipse.keypop.storagecard.SCReaderCommunicationException} - Exception for
 *       reader communication errors
 *   <li>{@link org.eclipse.keypop.storagecard.SCCardCommunicationException} - Exception for card
 *       communication errors
 *   <li>{@link org.eclipse.keypop.storagecard.SCInvalidCardResponseException} - Exception for
 *       invalid card responses
 *   <li>{@link org.eclipse.keypop.storagecard.SCAuthenticationFailedException} - Exception for
 *       authentication failures (Mifare Classic)
 * </ul>
 */
package org.eclipse.keypop.storagecard;
