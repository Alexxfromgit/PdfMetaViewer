# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Build
mvn compile
mvn clean package          # Produces uber-JAR via maven-shade-plugin

# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=GeneralConfigurationTests

# Run a specific test method
mvn test -Dtest=PdfConvertLogicTests#testPdfExtractor

# Run tests by group
mvn test -Dgroups=smoke
```

The packaged JAR main class is `gui.MetaViewerForm` (configured in maven-shade-plugin).

## Architecture

This is a Swing desktop application that extracts and displays PDF metadata using Apache Tika, with JSON export capability.

**Request flow:**
1. `MetaViewerForm` (GUI entry point) — user selects a PDF file
2. `PDFExtractor` — parses the PDF via Apache Tika, returns metadata `Map` and text content
3. `DocumentRecord` — immutable value object holding UUID, metadata map, and extracted text
4. `ConvertJavaMapToJson` — converts the metadata map to a JSON string (Jackson)
5. `DocumentRepository` — singleton that writes `DocumentRecord` to a `.json` file

`Main` is an alternative CLI entry point that processes the bundled example PDF without the GUI.

**Key packages:**
- `gui` — Swing UI (`MetaViewerForm`)
- `extractor` — Tika-based PDF parsing (`PDFExtractor`)
- `repository` — data model (`DocumentRecord`) and file persistence (`DocumentRepository`)
- `converters` — map-to-JSON conversion (`ConvertJavaMapToJson`)
- `utilities` — constants for paths, UI dimensions, and Swing look-and-feel options
- `program` — CLI entry point (`Main`)

## Tests

Test framework is TestNG with AssertJ assertions. Test suite is configured in `src/test/resources/testng.xml` with two groups: `smoke` (fast/config checks) and `ConvertLogic` (extraction logic).

A sample PDF (`Amazon-S3-Tutorial.pdf`) lives in both `src/main/resources/` and `src/test/resources/` and is used by `PdfConvertLogicTests`.

`TestFailureLog` is a `ITestListener` stub in `src/test/java/utils/` — its callback methods are currently empty placeholders.

## Notes

- Target: Java 8. No module-system considerations needed.
- The maven-jarsigner-plugin is configured in `pom.xml` but references a placeholder keystore path — JAR signing will fail unless a real keystore is provided.
- Error handling throughout is `e.printStackTrace()` — no structured logging despite SLF4J being on the classpath.
