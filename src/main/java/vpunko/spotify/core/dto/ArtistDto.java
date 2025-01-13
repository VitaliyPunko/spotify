package vpunko.spotify.core.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ArtistDto {

    @JsonProperty("external_urls")
    private ExternalUrls externalUrls;

    private Followers followers;

    private List<String> genres;

    private String href;

    private String id;

    private List<Image> images;

    private String name;

    private int popularity;

    private String type;

    private String uri;


    // Nested DTO for "external_urls"
    @Data
    public static class ExternalUrls {

        private String spotify;
    }

    @Data
    // Nested DTO for "followers"
    public static class Followers {

        private String href;

        private int total;
    }

    // Nested DTO for "images"
    @Data
    public static class Image {

        private String url;

        private int height;

        private int width;
    }
}
