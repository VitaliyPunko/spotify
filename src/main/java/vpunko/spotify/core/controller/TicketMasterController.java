package vpunko.spotify.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vpunko.spotify.core.client.TicketMasterRestClient;
import vpunko.spotify.core.dto.EventResponse;

@RestController
@RequiredArgsConstructor
public class TicketMasterController {

    private final TicketMasterRestClient client;


    @GetMapping("/getEvent")
    public String getEvent(@RequestParam String keyWord) {
        EventResponse event = client.getEvent(keyWord);
        return event.toString();
    }

}
