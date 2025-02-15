package vpunko.spotify.core.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import vpunko.spotify.core.dto.SpotifyTracksAnswerDto;
import vpunko.spotify.core.dto.SpotifyUser;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.oauth2.client.web.client.RequestAttributeClientRegistrationIdResolver.clientRegistrationId;

@Component
public class SpotifyClientEx {

    private final RestClient restClient;
    private static final String USER_PROFILE_URI = "https://accounts.spotify.com/com/v1/me";
    private static final String USER_TOP_TRACKS_URI = "/me/top/tracks";

    public SpotifyClientEx(
            RestClient restClient
    ) {
        this.restClient = restClient;
    }

    public SpotifyTracksAnswerDto getUserTopTracks() {
        SpotifyTracksAnswerDto body = restClient.get()
                .uri(USER_TOP_TRACKS_URI)
                .accept(APPLICATION_JSON)
                .attributes(clientRegistrationId("spotify"))
              //  .header("Authorization", "Bearer " + accessToken)  interceptor past the token
                .retrieve()
                .body(SpotifyTracksAnswerDto.class);
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
