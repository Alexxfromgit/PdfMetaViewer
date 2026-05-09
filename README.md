# PDF Meta Viewer

A Swing desktop application that extracts and displays PDF metadata using Apache Tika, with JSON export capability.

## Features

- Browse and select any PDF file via a file chooser dialog
- Extract and display all PDF metadata fields (author, title, creation date, page count, etc.)
- View full extracted text content
- Export metadata to a JSON file at a user-chosen location

## Architecture

```
MetaViewerForm (GUI)
    └─> PDFExtractor          — parses PDF via Apache Tika; returns DocumentRecord
            └─> DocumentRecord    — immutable value object (UUID, metadata map, text content)
            └─> DocumentRepository — writes DocumentRecord to a .json file
    └─> ConvertJavaMapToJson  — converts metadata Map to a JSON string (Jackson)
```

**Key packages:**

| Package | Description |
| --- | --- |
| `gui` | Swing UI (`MetaViewerForm`) |
| `extractor` | Tika-based PDF parsing (`PDFExtractor`) |
| `repository` | Data model (`DocumentRecord`) and file persistence (`DocumentRepository`) |
| `converters` | Map-to-JSON conversion (`ConvertJavaMapToJson`) |
| `utilities` | Constants for paths, UI dimensions, and Swing look-and-feel options |
| `program` | CLI entry point (`Main`) — processes the bundled example PDF without the GUI |

## Requirements

- Java 8
- Maven 3.x

## Build & Run

```bash
# Compile
mvn compile

# Run all tests
mvn test

# Package as an executable uber-JAR
mvn clean package
```

The packaged JAR (`target/PdfMetaViewer-1.0-SNAPSHOT-shaded.jar`) has `gui.MetaViewerForm` as its main class and can be launched directly:

```bash
java -jar target/PdfMetaViewer-1.0-SNAPSHOT-shaded.jar
```

## Running Tests

```bash
# All tests
mvn test

# Specific test class
mvn test -Dtest=GeneralConfigurationTests

# Specific test method
mvn test -Dtest=PdfConvertLogicTests#testPdfExtractor

# By group (smoke | ConvertLogic)
mvn test -Dgroups=smoke
```

Tests are configured via `src/test/resources/testng.xml` with two groups:
- `smoke` — fast configuration and extraction sanity checks
- `ConvertLogic` — PDF extraction logic tests

A sample PDF (`Amazon-S3-Tutorial.pdf`) is bundled under both `src/main/resources/` and `src/test/resources/`.

## Dependencies

| Library | Version | Purpose |
| --- | --- | --- |
| Apache Tika | 2.9.2 | PDF parsing and metadata extraction |
| Jackson | 2.14.2 | JSON serialization |
| SLF4J | 2.0.6 | Logging facade |
| TestNG | 7.10.2 | Test framework |
| AssertJ | 3.26.3 | Fluent test assertions |
| Mockito | 5.12.0 | Mocking in tests |

## Notes

- The `maven-jarsigner-plugin` in `pom.xml` references a placeholder keystore path — JAR signing will fail unless a real keystore is provided.
- Error handling currently uses `e.printStackTrace()` throughout; SLF4J is on the classpath but not yet wired up for structured logging.
