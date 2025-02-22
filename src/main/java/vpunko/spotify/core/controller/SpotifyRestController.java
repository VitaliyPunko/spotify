package vpunko.spotify.core.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vpunko.spotify.core.client.SpotifyRestClient;
import vpunko.spotify.core.dto.SpotifyUserTopAnswerDto;

import java.util.Optional;

@RestController
public class SpotifyRestController {

    private final SpotifyRestClient spotifyClient;

    public SpotifyRestController(SpotifyRestClient spotifyClient) {
        this.spotifyClient = spotifyClient;
    }


    @GetMapping("/user")
    public String getUserInfo(@AuthenticationPrincipal OAuth2User oauth2User) {
        // Получаем данные пользователя
        String userId = oauth2User.getName(); // ID пользователя
        String email = oauth2User.getAttribute("email"); // Email пользователя
        String displayName = oauth2User.getAttribute("display_name"); // Имя пользователя

        return "User ID: " + userId + ", Email: " + email + ", Display Name: " + displayName;
    }

    @GetMapping("/userTopTracks")
    public SpotifyUserTopAnswerDto getserTopTracks() {
        return spotifyClient.getUserTopTracks();
    }

    @GetMapping("/userTopArtists")
    public SpotifyUserTopAnswerDto getUserTopArtists(@RequestParam String limit) {
        return spotifyClient.getUserTopArtists(Optional.ofNullable(limit));
    }


}
