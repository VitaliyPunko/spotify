package vpunko.spotify.core.client;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import vpunko.spotify.core.dto.SpotifyUserTopAnswerDto;
import vpunko.spotify.core.exception.SpotifyRestClientException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SpotifyRestClientTest {

    private MockWebServer mockWebServer;
    private SpotifyRestClient spotifyRestClient;

    @BeforeEach
    void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        RestClient.Builder restClientBuilder = RestClient.builder();
        spotifyRestClient = new SpotifyRestClient(restClientBuilder, mockWebServer.url("/").toString());
    }

    @AfterEach
    void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    void getUserTopTracksShouldReturnValidResponse() {
        // ✅ Mock Spotify API response
        String fakeJsonResponse = fakeJsonResponse();

        mockWebServer.enqueue(new MockResponse()
                .setBody(fakeJsonResponse)
                .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .setResponseCode(200));

        // ✅ Call the method
        SpotifyUserTopAnswerDto response = spotifyRestClient.getUserTopTracks();

        // ✅ Assertions
        assertThat(response).isNotNull();
        assertThat(response.getItems()).hasSize(2);
        assertThat(response.getItems().get(0).getName()).isEqualTo("Song A");
    }


    @Test
    void getUserTopTracksWithErrorCode() {
       mockWebServer.enqueue(new MockResponse().setResponseCode(500));

        // ✅ Call the method
       assertThrows(RuntimeException.class, () -> spotifyRestClient.getUserTopTracks());

        // ✅ Assertions
        assertThat(mockWebServer.getRequestCount()).isEqualTo(1);
    }

    @Test
    void getUserTopArtistsShouldReturnValidResponse() {
        // ✅ Mock Spotify API response
        String fakeJsonResponse = fakeJsonResponse();

        mockWebServer.enqueue(new MockResponse()
                .setBody(fakeJsonResponse)
                .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .setResponseCode(200));

        // ✅ Call the method
        SpotifyUserTopAnswerDto response = spotifyRestClient.getUserTopArtists(Optional.empty());

        // ✅ Assertions
        assertThat(response).isNotNull();
        assertThat(response.getItems()).hasSize(2);
        assertThat(response.getItems().get(0).getName()).isEqualTo("Song A");
    }


    @Test
    void getUserTopArtistsWithErrorCode() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404));

        // ✅ Call the method
        assertThrows(SpotifyRestClientException.class, () -> spotifyRestClient.getUserTopArtists(Optional.empty()));

        // ✅ Assertions
        assertThat(mockWebServer.getRequestCount()).isEqualTo(1);
    }



    private String fakeJsonResponse() {
        return """
                {
                  "href": "https://api.spotify.com/v1/artists/4Z8W4fKeB5YxbusRsdQVPb",
                  "items": [
                    {
                      "genres": [
                        "art rock",
                        "alternative rock"
                      ],
                      "id": "some id",
                      "name": "Song A",
                      "popularity": 84,
                      "type": "artist"
                    },
                    {
                      "genres": [
                        "art rock",
                        "alternative rock"
                      ],
                      "id": "some id",
                      "name": "Song B",
                      "popularity": 90,
                      "type": "artist"
                    }
                  ]
                }
                """;
    }

}