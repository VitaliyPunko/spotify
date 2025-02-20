package vpunko.spotify.core.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import vpunko.spotify.core.dto.EventResponse;
import vpunko.spotify.core.exception.TicketMasterClientException;

@Component
public class TicketMasterRestClient {

    private final String apiKey;
    private final RestClient restClient;

    public TicketMasterRestClient(RestClient.Builder restClient,
                                  @Value("${application.credentials.ticketmaster.apikey}")
                                  String apiKey,
                                  @Value("${application.urls.ticketmaster}")
                                  String baseUrl) {
        this.apiKey = apiKey;
        this.restClient = restClient.baseUrl(baseUrl).build();
    }

    public EventResponse getEvent(String keyWord) {
        EventResponse body = restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/events")
                        .queryParam("apikey", apiKey)
                        .queryParam("keyword", keyWord)
                        .queryParam("size", 1).build())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new TicketMasterClientException(response.getStatusCode(), response.getHeaders());
                })
                .body(EventResponse.class);

        if (body == null) {
            throw new TicketMasterClientException("TicketMasterClient return null body");
        }

        return body;
    }
}
