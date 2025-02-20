package vpunko.spotify.core.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class EventResponse {

    private Embedded _embedded;
    private Links _links;
    private Page page;

    @Getter
    @Setter
    public static class Embedded {
        private List<Event> events;
    }

    @Getter
    @Setter
    public static class Event {
        private String name;
        private String type;
        private String id;
        private boolean test;
        private String url;
        private String locale;
        private List<Image> images;
        private Sales sales;
        private Dates dates;
        private List<Classification> classifications;
        private Promoter promoter;
        private List<Promoter> promoters;
        private List<PriceRange> priceRanges;
        private Ticketing ticketing;
        private Links _links;
        private EmbeddedVenuesAttractions _embedded;

        @Getter
        @Setter
        public static class Image {
            private String ratio;
            private String url;
            private int width;
            private int height;
            private boolean fallback;
        }

        @Getter
        @Setter
        public static class Sales {
            private Public publicSale;

            @Getter
            @Setter
            public static class Public {
                private String startDateTime;
                private boolean startTBD;
                private boolean startTBA;
                private String endDateTime;
            }
        }

        @Getter
        @Setter
        public static class Dates {
            private Start start;
            private String timezone;
            private Status status;
            private boolean spanMultipleDays;

            @Getter
            @Setter
            public static class Start {
                private String localDate;
                private String localTime;
                private String dateTime;
                private boolean dateTBD;
                private boolean dateTBA;
                private boolean timeTBA;
                private boolean noSpecificTime;
            }

            @Getter
            @Setter
            public static class Status {
                private String code;
            }
        }

        @Getter
        @Setter
        public static class Classification {
            private boolean primary;
            private Segment segment;
            private Genre genre;
            private SubGenre subGenre;
            private boolean family;

            @Getter
            @Setter
            public static class Segment {
                private String id;
                private String name;
            }

            @Getter
            @Setter
            public static class Genre {
                private String id;
                private String name;
            }

            @Getter
            @Setter
            public static class SubGenre {
                private String id;
                private String name;
            }
        }

        @Getter
        @Setter
        public static class Promoter {
            private String id;
            private String name;
        }

        @Getter
        @Setter
        public static class PriceRange {
            private String type;
            private String currency;
            private double min;
            private double max;
        }

        @Getter
        @Setter
        public static class Ticketing {
            private SafeTix safeTix;
            private String id;

            @Getter
            @Setter
            public static class SafeTix {
                private boolean enabled;
            }
        }

        @Getter
        @Setter
        public static class Links {
            private Self self;
            private List<AttractionLink> attractions;
            private List<VenueLink> venues;

            @Getter
            @Setter
            public static class Self {
                private String href;
            }

            @Getter
            @Setter
            public static class AttractionLink {
                private String href;
            }

            @Getter
            @Setter
            public static class VenueLink {
                private String href;
            }
        }

        @Getter
        @Setter
        public static class EmbeddedVenuesAttractions {
            private List<Venue> venues;
            private List<Attraction> attractions;

            @Getter
            @Setter
            public static class Venue {
                private String name;
                private String type;
                private String id;
                private boolean test;
                private String url;
                private String locale;
                private List<Image> images;
                private String postalCode;
                private String timezone;
                private City city;
                private Country country;
                private Address address;
                private Location location;
                private UpcomingEvents upcomingEvents;
                private Links _links;

                @Getter
                @Setter
                public static class City {
                    private String name;
                }

                @Getter
                @Setter
                public static class Country {
                    private String name;
                    private String countryCode;
                }

                @Getter
                @Setter
                public static class Address {
                    private String line1;
                }

                @Getter
                @Setter
                public static class Location {
                    private String longitude;
                    private String latitude;
                }
            }

            @Getter
            @Setter
            public static class Attraction {
                private String name;
                private String type;
                private String id;
                private boolean test;
                private String url;
                private String locale;
                private Map<String, List<ExternalLink>> externalLinks;
                private List<Image> images;
                private List<Classification> classifications;
                private UpcomingEvents upcomingEvents;
                private Links _links;

                @Getter
                @Setter
                public static class ExternalLink {
                    private String url;
                }
            }
        }
    }

    @Getter
    @Setter
    public static class UpcomingEvents {
        private int mfxNl;
        private int _total;
        private int _filtered;

        // Lombok will generate getters and setters for all fields
    }

    @Getter
    @Setter
    public static class Links {
        private Link first;
        private Link self;
        private Link next;
        private Link last;

        @Getter
        @Setter
        public static class Link {
            private String href;
        }
    }

    @Getter
    @Setter
    public static class Page {
        private int size;
        private int totalElements;
        private int totalPages;
        private int number;
    }
}