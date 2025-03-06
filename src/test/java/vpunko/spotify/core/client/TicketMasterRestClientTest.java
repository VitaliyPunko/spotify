package vpunko.spotify.core.client;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import vpunko.spotify.core.exception.TicketMasterClientException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static vpunko.spotify.core.TestUtils.readFileAsString;

class TicketMasterRestClientTest {

    private MockWebServer mockWebServer;
    private TicketMasterRestClient ticketMasterRestClient;

    @BeforeEach
    void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();

        RestClient.Builder restClientBuilder = RestClient.builder();
        ticketMasterRestClient = new TicketMasterRestClient(
                restClientBuilder,
                "",
                mockWebServer.url("/").toString(),
                1);
    }

    @AfterEach
    void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    void getUserTopTracksShouldReturnValidResponse() {
        // ✅ Mock Spotify API response
        //given
        String jsonFile = "src/test/resources/client/ticketmaster/success.json";
        String successResponse = readFileAsString(jsonFile);

        mockWebServer.enqueue(new MockResponse()
                .setBody(successResponse)
                .setHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .setResponseCode(200));

        // ✅ Call the method
        var response = ticketMasterRestClient.getEvent("artist", null);

        // ✅ Assertions
        assertThat(response).isNotNull();
        assertThat(response.get_embedded()).isNotNull();
        assertThat(response.get_embedded().getEvents()).isNotNull();
        assertThat(response.get_embedded().getEvents().get(0).getName()).isEqualTo("Rammstein Tribute");
    }

    @Test
    void getUserTopArtistsWithErrorCode() {
        mockWebServer.enqueue(new MockResponse().setResponseCode(404));

        // ✅ Call the method
        assertThrows(TicketMasterClientException.class, () -> ticketMasterRestClient.getEvent("artist", null));

        // ✅ Assertions
        assertThat(mockWebServer.getRequestCount()).isEqualTo(1);
    }

}