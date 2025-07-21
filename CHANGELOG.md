# Changelog
All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

[unreleased]

## [0.3.0] - 2025-07-21
### Added
- **Exception hierarchy**: New base class `StorageCardException` with block address context support:
  - `StorageCardException.getBlockAddress()` method to retrieve the block address involved in errors.
- **Enhanced exception handling**: All storage card exceptions now extend `StorageCardException` and include block
  address context:
  - `CardIOException` constructor now accepts `blockAddress` parameter.
  - `ReaderIOException` constructor now accepts `blockAddress` parameter.
  - `UnexpectedCommandStatusException` constructor now accepts `blockAddress` parameter.
* **Product type capability**:
  New `ProductType.hasWriteAcknowledgment()` method to indicate whether a storage card provides a reliable
  acknowledgment after write operations.
### Changed
- **Write operation behavior**: API contract updated for `prepareWriteBlocks()` method regarding verification reads:
  - The API now specifies that implementations must handle verification reads transparently for storage cards without
    reliable write acknowledgment (e.g., SRT512/ST25).
  - For cards with reliable write acknowledgment, no additional reads are required by the implementation.
  - Applications can now rely on the API contract that data integrity is guaranteed without explicitly performing
    verification reads.
### Removed
- **Deprecated exception**: Removed `InconsistentDataException` class as it's no longer needed with the new exception
  hierarchy.

## [0.2.0] - 2025-06-27
### Added
- **System block management**: New methods to handle system blocks for compatible storage card types:
    - `StorageCardTransactionManager.prepareReadSystemBlock()` method.
    - `StorageCardTransactionManager.prepareWriteSystemBlock(byte[] data)` method.
    - `StorageCard.getSystemBlock()` method.
    - `ProductType.hasSystemBlock()` method to indicate system block support by card type.
### Changed
- Parameter naming consistency across all APIs: renamed from `blockNumber` to `blockAddress`:
    - `StorageCardTransactionManager.prepareReadBlock(int blockAddress)` method.
    - `StorageCardTransactionManager.prepareReadBlocks(int fromBlockAddress, int toBlockAddress)` method.
    - `StorageCardTransactionManager.prepareWriteBlocks(int fromBlockAddress, byte[] data)` method.
    - `StorageCardSelectionExtension.prepareReadBlock(int blockAddress)` method.
    - `StorageCardSelectionExtension.prepareReadBlocks(int fromBlockAddress, int toBlockAddress)` method.
    - `StorageCard.getBlock(int blockAddress)` method.
    - `StorageCard.getBlocks(int fromBlockAddress, int toBlockAddress)` method.
- Documentation improvements:
    - Clarified that block addresses start at 0 and maximum value is `getBlockCount() - 1`.
    - Enhanced documentation for zero-filled behavior when blocks haven't been read.
    - Fixed incorrect reference from `ProductType.getBlockCount()` to `ProductType.getBlockSize()` in write methods.
- **Write operation behavior**: Write commands no longer automatically update the `StorageCard` memory image due to
  storage card technology limitations. Applications must perform explicit read operations after writes to verify actual
  card content.

## [0.1.0] - 2025-06-18
This is the initial release.

[unreleased]: https://github.com/eclipse-keypop/keypop-storagecard-java-api/compare/0.3.0...HEAD
[0.3.0]: https://github.com/eclipse-keypop/keypop-storagecard-java-api/compare/0.2.0...0.3.0
[0.2.0]: https://github.com/eclipse-keypop/keypop-storagecard-java-api/compare/0.1.0...0.2.0
[0.1.0]: https://github.com/eclipse-keypop/keypop-storagecard-java-api/releases/tag/0.1.0
