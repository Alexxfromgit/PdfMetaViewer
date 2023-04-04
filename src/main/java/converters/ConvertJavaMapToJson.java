package converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class ConvertJavaMapToJson {

    public String convertMapToJson(Map<String, String> mapToConvert) {
        String json = "";
        try {
            json = new ObjectMapper().writeValueAsString(mapToConvert);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
}
