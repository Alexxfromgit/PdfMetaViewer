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

    public void importPDF(String filePath, boolean isContentIncluded) {
        try {
            pdfParser.parse(Files.newInputStream(Paths.get(filePath)), handler, metadata, parseContext);
            DocumentRecord documentRecord = new DocumentRecord(getMetadata(), getDocumentText());
            DocumentRepository.storeMetadataRecord(documentRecord, isContentIncluded);
        } catch (TikaException | SAXException | IOException e) {
            e.printStackTrace();
        }
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
