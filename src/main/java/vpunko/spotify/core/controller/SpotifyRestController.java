package vpunko.spotify.core.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import vpunko.spotify.core.client.SpotifyClientEx;
import vpunko.spotify.core.dto.SpotifyTracksAnswerDto;

@RestController
public class SpotifyRestController {

    private final SpotifyClientEx spotifyClient;

    public SpotifyRestController(SpotifyClientEx spotifyClient) {
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
    public SpotifyTracksAnswerDto getserTopTracks() {
        // Получаем данные пользователя
        return spotifyClient.getUserTopTracks();

    }


}
