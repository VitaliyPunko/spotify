package vpunko.spotify.core.client;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.util.UriComponentsBuilder;
import vpunko.spotify.core.dto.SpotifyUser;

import java.util.Base64;
import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class SpotifyClientEx {

    private final String clientId;
    private final String clientSecret;
    private final
    RestClient restClient;
    private static final String SPOTIFY_AUTH_URL = "https://accounts.spotify.com/authorize";
    private static final String REDIRECT_URI = "http://localhost:8080/callback";
    private static final String USER_PROFILE_URI = "https://accounts.spotify.com/com/v1/me";

    public SpotifyClientEx(
            @Value("${spotify.auth.credentials.clientId}") String clientId,
            @Value("${spotify.auth.credentials.clientSecret}") String clientSecret
    ) {
        this.restClient = RestClient.builder().messageConverters(
                        c -> c.add(getMappingJackson2HttpMessageConverter()))
                .build();
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public SpotifyUser getUserProfile() {
        SpotifyUser body = restClient.get()
                .uri(USER_PROFILE_URI)
                .accept(APPLICATION_JSON)
//                .header("Authorization", "Bearer " + accessToken)
                .retrieve()
                .body(SpotifyUser.class);
        return body;
    }


//    public ArtistDto getArtist() {
//        String artistId = "4Z8W4fKeB5YxbusRsdQVPb";
//        // Get the access token
//        String accessToken = getAccessToken();
//
//        // Spotify API endpoint
//        String artistUri = "https://api.spotify.com/v1/artists/" + artistId;
//
//        return restClient.get()
//                .uri(artistUri)
//                .accept(APPLICATION_JSON)
//                .header("Authorization", "Bearer " + accessToken)
//                .retrieve()
//                .body(ArtistDto.class);
//    }

    public String getClientCredentialsAccessToken() {
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

    public String getAuthorizationFlowAccessToken(String code) {
        String authUri = "https://accounts.spotify.com/api/token";
        String credentials = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("code", code);
        body.add("redirect_uri", REDIRECT_URI);

        String authorization = restClient.post()
                .uri(authUri)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .header("Authorization", "Basic " + credentials)
                .body(body)
                .retrieve()
                .body(String.class);

        // Parse the token from JSON response
        JSONObject jsonResponse = new JSONObject(authorization);
        String accessToken = jsonResponse.getString("access_token");
        System.out.println(accessToken);
        return accessToken;
    }

    public String authorized() {
        var state = generateRandomString(16);
        var scope = "user-read-private user-read-email";
        // Get the access token

        String authUrl = UriComponentsBuilder.fromUriString(SPOTIFY_AUTH_URL)
                .queryParam("response_type", "code")
                .queryParam("client_id", clientId)
                .queryParam("scope", scope)
                .queryParam("redirect_uri", REDIRECT_URI)
                .queryParam("state", state)
                .toUriString();

        return "redirect:" + authUrl;
    }


    private String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt((int) (Math.random() * chars.length())));
        }
        return sb.toString();
    }


    public MappingJackson2HttpMessageConverter getMappingJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_FORM_URLENCODED));
        return mappingJackson2HttpMessageConverter;
    }

}
