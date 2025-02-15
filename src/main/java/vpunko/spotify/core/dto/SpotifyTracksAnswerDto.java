package vpunko.spotify.core.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class SpotifyTracksAnswerDto {

    private String href;
    private int limit;
    private String next;
    private int offset;
    private String previous;
    private int total;
    private List<Item> items;

    @Getter
    @Setter
    public static class Item {
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

        @Getter
        @Setter
        public static class Image {
            private String url;
            private int height;
            private int width;
        }
    }
}
