package vpunko.spotify.core.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vpunko.spotify.core.client.SpotifyClient;
import vpunko.spotify.core.dto.ArtistDto;

@RestController
public class SpotifyController {

    private final SpotifyClient spotifyClient;

    public SpotifyController(SpotifyClient spotifyClient) {
        this.spotifyClient = spotifyClient;
    }

    @GetMapping("/getArtist")
    public ArtistDto getArticle() {
        return spotifyClient.getArtist();
    }

    @GetMapping("/getToken")
    public String getToken() {
        return spotifyClient.getAccessToken();
    }
}
