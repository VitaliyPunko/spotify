package vpunko.spotify.core.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

public class TicketMasterClientException extends RuntimeException {

    private HttpStatusCode statusCode;
    private HttpHeaders headers;

    public TicketMasterClientException(String message) {
        super(message);
    }

    public TicketMasterClientException(HttpStatusCode statusCode, HttpHeaders headers) {
        this.statusCode = statusCode;
        this.headers = headers;
    }
}


