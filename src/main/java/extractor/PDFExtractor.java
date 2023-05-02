package extractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import repository.DocumentRecord;
import repository.DocumentRepository;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.SAXException;

public class PDFExtractor {
    BodyContentHandler handler = new BodyContentHandler(-1);
    Metadata metadata = new Metadata();
    ParseContext parseContext = new ParseContext();
    PDFParser pdfParser = new PDFParser();

    public DocumentRecord getDocumentRecordFromImportedPDF(String filePath) {
        DocumentRecord documentRecord = null;
        try {
            pdfParser.parse(Files.newInputStream(Paths.get(filePath)), handler, metadata, parseContext);
            documentRecord = new DocumentRecord(getMetadata(), getDocumentText());
        } catch (TikaException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return documentRecord;
    }

    public void storeDocumentRecordAs(DocumentRecord documentRecord, boolean isContentIncluded) {
        DocumentRepository.storeMetadataRecord(documentRecord, isContentIncluded);
    }

    public void storeDocumentRecordAs(DocumentRecord documentRecord, String filePath, boolean isContentIncluded) {
        DocumentRepository.storeMetadataRecord(documentRecord, filePath, isContentIncluded);
    }

    private String getDocumentText() {
        return handler.toString();
    }

    private Map<String, String> getMetadata() {
        Map<String, String> metaMap = new HashMap<>();
        for (String name : metadata.names()) {
            metaMap.put(name, metadata.get(name));
        }
        return metaMap;
    }
}
