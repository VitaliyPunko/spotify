package vpunko.spotify.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vpunko.spotify.core.client.SpotifyRestClient;
import vpunko.spotify.core.client.TicketMasterRestClient;
import vpunko.spotify.core.dto.SpotifyUserTopAnswerDto;
import vpunko.spotify.core.dto.TicketMasterEventResponse;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MusicEventServiceImpl {

    private static final String LIMIT = "5";

    private final SpotifyRestClient spotifyRestClient;
    private final TicketMasterRestClient ticketMasterRestClient;

    public TicketMasterEventResponse getMusicEventByArtist() {
        var userTopArtists = spotifyRestClient.getUserTopArtists(Optional.of(LIMIT));
        if (userTopArtists == null) {
            log.warn("UserTopArtists is null");
            return null;
        }

        List<SpotifyUserTopAnswerDto.Item> items = userTopArtists.getItems();
        List<String> nameOfArtists = items.stream()
                .map(e -> e.getName())
                .toList();

        String firstName = nameOfArtists.get(3);

        //get big response. Get only some simple fields
        TicketMasterEventResponse event = ticketMasterRestClient.getEvent(firstName, OffsetDateTime.now());

        return event;
    }


}
