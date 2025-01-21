package vpunko.spotify.core.client;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import vpunko.spotify.core.dto.ArtistDto;

import java.util.Base64;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class SpotifyClient {

    private final String clientId;
    private final String clientSecret;
    private final RestClient restClient;

    public SpotifyClient(
            @Value("${spotify.auth.credentials.clientId}") String clientId,
            @Value("${spotify.auth.credentials.clientSecret}") String clientSecret
    ) {
        this.restClient = RestClient.create();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }


    public ArtistDto getArtist() {
        String artistId = "4Z8W4fKeB5YxbusRsdQVPb";
        // Get the access token
        String accessToken = getAccessToken();

        // Spotify API endpoint
        String artistUri = "https://api.spotify.com/v1/artists/" + artistId;

        return restClient.get()
                .uri(artistUri)
                .accept(APPLICATION_JSON)
                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .body(ArtistDto.class);
    }

    public String getAccessToken() {
        String authUri = "https://accounts.spotify.com/api/token";
        String credentials = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        String authorization = restClient.post()
                .uri(authUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization", "Basic " + credentials)
                .body("grant_type=client_credentials")
                .retrieve()
                .body(String.class);

        // Parse the token from JSON response
        JSONObject jsonResponse = new JSONObject(authorization);
        return jsonResponse.getString("access_token");
    }

}
