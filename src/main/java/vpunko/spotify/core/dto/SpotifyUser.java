package vpunko.spotify.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SpotifyUser {

    private String country;
    private String display_name;
    private String email;
    private ExplicitContent explicit_content;
    private ExternalUrls external_urls;
    private Followers followers;
    private String href;
    private String id;
    private List<Object> images; // Assuming images can be any type of object
    private String product;
    private String type;
    private String uri;


    // Nested classes for nested JSON objects
    @Getter
    @Setter
    public static class ExplicitContent {
        private boolean filter_enabled;
        private boolean filter_locked;
    }

    @Getter
    @Setter
    public static class ExternalUrls {
        private String spotify;
    }

    @Getter
    @Setter
    public static class Followers {
        private String href;
        private int total;

    }

    @Override
    public String toString() {
        return "SpotifyUser{" +
                "country='" + country + '\'' +
                ", display_name='" + display_name + '\'' +
                ", email='" + email + '\'' +
                ", explicit_content=" + explicit_content +
                ", external_urls=" + external_urls +
                ", followers=" + followers +
                ", href='" + href + '\'' +
                ", id='" + id + '\'' +
                ", images=" + images +
                ", product='" + product + '\'' +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}

