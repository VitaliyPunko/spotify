package vpunko.spotify.core.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;

public class TicketMasterClientException extends RuntimeException {

    private HttpStatusCode statusCode;
    private HttpHeaders headers;
    private String responseBody;

    public TicketMasterClientException(String message) {
        super(message);
    }

    public TicketMasterClientException(HttpStatusCode statusCode, HttpHeaders headers, String responseBody) {
        super(String.format("TicketMaster API request failed with status code: %s, headers: %s, response body: %s",
                statusCode, headers, responseBody));
        this.statusCode = statusCode;
        this.headers = headers;
        this.responseBody = responseBody;
    }
}


