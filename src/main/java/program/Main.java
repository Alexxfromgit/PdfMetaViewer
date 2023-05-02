package program;

import extractor.PDFExtractor;
import utilities.PdfMetaViewerConstants;

public class Main {

    private static void main(String[] args) {
        PDFExtractor pdfExtractor = new PDFExtractor();
        pdfExtractor.storeDocumentRecordAs(pdfExtractor.getDocumentRecordFromImportedPDF(
                PdfMetaViewerConstants.RESOURCES_EXAMPLE_PATH), false);
    }
}


