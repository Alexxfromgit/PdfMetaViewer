package repository;

import converters.ConvertJavaMapToJson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DocumentRepository {

    private DocumentRepository(){

    }

    public static void storeMetadataRecord(DocumentRecord documentRecord, boolean isContentIncluded){

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                Files.newOutputStream(Paths.get("src/main/resources/metadata.json")), StandardCharsets.UTF_8))) {
            writer.write(new ConvertJavaMapToJson().convertMapToJson(documentRecord.getMetadata()));
            if (isContentIncluded) writer.append(documentRecord.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
