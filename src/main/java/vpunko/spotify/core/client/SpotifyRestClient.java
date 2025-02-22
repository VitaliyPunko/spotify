package vpunko.spotify.core.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import vpunko.spotify.core.dto.SpotifyUser;
import vpunko.spotify.core.dto.SpotifyUserTopAnswerDto;
import vpunko.spotify.core.exception.SpotifyRestClientException;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;

@Component
public class SpotifyRestClient {

    private final RestClient restClient;
    private static final String USER_PROFILE_URI = "https://accounts.spotify.com/com/v1/me";
    private static final String USER_TOP_TRACKS_URI = "/me/top/tracks";
    private static final String USER_TOP_ARTISTS_URI = "/me/top/artists";

    public SpotifyRestClient(
            RestClient.Builder restClient,
            @Value("${application.urls.spotify}") String spotifyBaseUrl
    ) {
        this.restClient = restClient
                .baseUrl(spotifyBaseUrl)
                .build();
    }

    public SpotifyUserTopAnswerDto getUserTopTracks() {
        SpotifyUserTopAnswerDto body = restClient.get()
                .uri(USER_TOP_TRACKS_URI)
                .accept(APPLICATION_JSON)
                .attributes(clientRegistrationId("spotify"))
              //  .header("Authorization", "Bearer " + accessToken)  interceptor past the token
                .retrieve()
                .body(SpotifyUserTopAnswerDto.class);
        return body;
    }

    //add limit?
    public SpotifyUserTopAnswerDto getUserTopArtists(Optional<String> limit) {
        SpotifyUserTopAnswerDto body = restClient.get()
                .uri(uriBuilder -> uriBuilder.path(USER_TOP_ARTISTS_URI)
                        .queryParamIfPresent("limit", limit).build()
                )
                .accept(APPLICATION_JSON)
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new SpotifyRestClientException(response.getStatusCode(), response.getHeaders());
                })
                .body(SpotifyUserTopAnswerDto.class);
        return body;
    }

    public SpotifyUser getUserProfile() {
        SpotifyUser body = restClient.get()
                .uri(USER_PROFILE_URI)
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(SpotifyUser.class);
        return body;
    }

}
