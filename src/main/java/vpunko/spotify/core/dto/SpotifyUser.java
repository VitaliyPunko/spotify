package vpunko.spotify.core.dto;

import java.util.List;


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

    // Getters and Setters
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ExplicitContent getExplicit_content() {
        return explicit_content;
    }

    public void setExplicit_content(ExplicitContent explicit_content) {
        this.explicit_content = explicit_content;
    }

    public ExternalUrls getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(ExternalUrls external_urls) {
        this.external_urls = external_urls;
    }

    public Followers getFollowers() {
        return followers;
    }

    public void setFollowers(Followers followers) {
        this.followers = followers;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Object> getImages() {
        return images;
    }

    public void setImages(List<Object> images) {
        this.images = images;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    // Nested classes for nested JSON objects
    public static class ExplicitContent {
        private boolean filter_enabled;
        private boolean filter_locked;

        // Getters and Setters
        public boolean isFilter_enabled() {
            return filter_enabled;
        }

        public void setFilter_enabled(boolean filter_enabled) {
            this.filter_enabled = filter_enabled;
        }

        public boolean isFilter_locked() {
            return filter_locked;
        }

        public void setFilter_locked(boolean filter_locked) {
            this.filter_locked = filter_locked;
        }
    }

    public static class ExternalUrls {
        private String spotify;

        // Getters and Setters
        public String getSpotify() {
            return spotify;
        }

        public void setSpotify(String spotify) {
            this.spotify = spotify;
        }
    }

    public static class Followers {
        private String href;
        private int total;

        // Getters and Setters
        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }
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

