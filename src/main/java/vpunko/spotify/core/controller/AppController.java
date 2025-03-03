package vpunko.spotify.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vpunko.spotify.core.dto.MusicEventDto;
import vpunko.spotify.core.dto.TicketMasterEventResponse;
import vpunko.spotify.core.service.MusicEventServiceImpl;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AppController {

    private final MusicEventServiceImpl musicEventService;


    @GetMapping("/get-user-pref")
    public TicketMasterEventResponse getEventByUserPreference() {
        var musicEventByArtist = musicEventService.getMusicEventByUserTopArtist();
        return musicEventByArtist;
    }

    @GetMapping("/getEventByArtist")
    public List<MusicEventDto> getEventByArtist(@RequestParam String artist) {
        return musicEventService.getMusicEventByArtist(artist);
    }
}
