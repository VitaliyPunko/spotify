package vpunko.spotify.core.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import vpunko.spotify.core.dto.SpotifyUser;
import vpunko.spotify.core.dto.SpotifyUserTopAnswerDto;

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

    public SpotifyUserTopAnswerDto getUserTopArtists() {
        SpotifyUserTopAnswerDto body = restClient.get()
                .uri(USER_TOP_ARTISTS_URI)
                .accept(APPLICATION_JSON)
                .attributes(clientRegistrationId("spotify"))
                .retrieve()
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
