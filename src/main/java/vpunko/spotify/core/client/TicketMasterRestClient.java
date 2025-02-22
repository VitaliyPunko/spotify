package vpunko.spotify.core.client;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import vpunko.spotify.core.dto.TicketMasterEventResponse;
import vpunko.spotify.core.exception.TicketMasterClientException;

import java.time.OffsetDateTime;
import java.util.Optional;

/**
 * <a href="https://developer.ticketmaster.com/products-and-docs/apis/discovery-api/v2/">Discovery ticketmaster API</a>
 */
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

    public TicketMasterEventResponse getEvent(String keyWord, OffsetDateTime startDate) {
        TicketMasterEventResponse body = restClient.get()
                .uri(uriBuilder -> uriBuilder.path("/events")
                        .queryParam("apikey", apiKey)
                        .queryParam("keyword", keyWord)
                        .queryParamIfPresent("startDateTime", Optional.ofNullable(startDate))
                        .build()
                        )
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new TicketMasterClientException(response.getStatusCode(), response.getHeaders());
                })
                .body(TicketMasterEventResponse.class);

        if (body == null) {
            throw new TicketMasterClientException("TicketMasterClient return null body");
        }

        return body;
    }
}
