package vpunko.spotify.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class TestUtils {

    public static <T> T parse(String path, Class<T> clazz) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        try (InputStream inputStream = TestUtils.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new FileNotFoundException("File not found: " + path);
            }
            return objectMapper.readValue(inputStream, clazz);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON file: " + path, e);
        }
    }

    public static <T> List<T> parseList(String path) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = TestUtils.class.getClassLoader().getResourceAsStream(path)) {
            if (inputStream == null) {
                throw new FileNotFoundException("File not found: " + path);
            }
            return objectMapper.readValue(inputStream, new TypeReference<List<T>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse JSON file: " + path, e);
        }
    }
}
