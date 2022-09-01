package repository;

import java.util.Map;
import java.util.UUID;

public class DocumentRecord {
    private String id;
    private Map<String,String> metadata;
    private String content;

    public DocumentRecord(){
    }

    public DocumentRecord(Map<String, String> metadata, String content){
        this.id = UUID.randomUUID().toString();
        this.metadata = metadata;
        this.content = content;
    }

    public String getContent(){
        return this.content;
    }

    public Map<String, String> getMetadata(){
        return this.metadata;
    }
}
