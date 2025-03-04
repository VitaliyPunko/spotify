package vpunko.spotify.core.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

public class SpotifyRestClientException extends RuntimeException {

    private HttpStatusCode statusCode;
    private HttpHeaders headers;

    public SpotifyRestClientException(String message) {
        super(message);
    }

    public SpotifyRestClientException(HttpStatusCode statusCode, HttpHeaders headers) {
        super(String.format("Spotify API request failed with status code: %s, headers: %s",
                statusCode, headers));
        this.statusCode = statusCode;
        this.headers = headers;
    }
}


