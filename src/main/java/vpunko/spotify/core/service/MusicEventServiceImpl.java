package vpunko.spotify.core.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import vpunko.spotify.core.client.SpotifyRestClient;
import vpunko.spotify.core.client.TicketMasterRestClient;
import vpunko.spotify.core.dto.MusicEventDto;
import vpunko.spotify.core.dto.SpotifyUserTopAnswerDto;
import vpunko.spotify.core.dto.TicketMasterEventResponse;
import vpunko.spotify.core.mapper.TicketMasterMapper;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MusicEventServiceImpl {

    private static final String LIMIT = "5";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");

    private final TicketMasterMapper mapper;
    private final SpotifyRestClient spotifyRestClient;
    private final TicketMasterRestClient ticketMasterRestClient;

    public List<MusicEventDto> getMusicEventByArtist(String artist) {
        //get big response. Get only some simple fields
        String offsetDateTimeRequiredFormat = OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC).format(formatter);

        TicketMasterEventResponse response = ticketMasterRestClient.getEvent(artist, offsetDateTimeRequiredFormat);
        return mapper.map(response);
    }




    //todo: add logic here later
    public TicketMasterEventResponse getMusicEventByUserTopArtist() {
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
        String offsetDateTimeRequiredFormat = OffsetDateTime.now().withOffsetSameInstant(ZoneOffset.UTC).format(formatter);
        TicketMasterEventResponse event = ticketMasterRestClient.getEvent(firstName, offsetDateTimeRequiredFormat);

        return event;
    }

}
