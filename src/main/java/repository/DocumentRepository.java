package repository;

import converters.ConvertJavaMapToJson;
import utilities.PdfMetaViewerConstants;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DocumentRepository {

    private DocumentRepository() {
    }

    public static void storeMetadataRecord(DocumentRecord documentRecord, boolean isContentIncluded) {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                Files.newOutputStream(Paths.get(PdfMetaViewerConstants.RESOURCES_PATH)), StandardCharsets.UTF_8))) {
            writer.write(new ConvertJavaMapToJson().convertMapToJson(documentRecord.getMetadata()));
            if (isContentIncluded) writer.append(documentRecord.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void storeMetadataRecord(DocumentRecord documentRecord, String filePath, boolean isContentIncluded) {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                Files.newOutputStream(Paths.get(filePath)), StandardCharsets.UTF_8))) {
            writer.write(new ConvertJavaMapToJson().convertMapToJson(documentRecord.getMetadata()));
            if (isContentIncluded) writer.append(documentRecord.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
