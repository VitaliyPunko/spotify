package vpunko.spotify.core.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vpunko.spotify.core.TestUtils;
import vpunko.spotify.core.dto.MusicEventDto;
import vpunko.spotify.core.dto.TicketMasterEventResponse;

import java.util.List;

class TicketMasterMapperTest {

    private TicketMasterMapper mapper = new TicketMasterMapper();

    @Test
    void successWithAllFieldsTest() {
        //given
        String jsonFile = "mapper/ticketmastermapper/success/input.json";
        var given = TestUtils.parse(jsonFile, TicketMasterEventResponse.class);

        //when
        List<MusicEventDto> musicEventDtoList = mapper.map(given);

        //then
        String jsonExpected = "mapper/ticketmastermapper/success/expected.json";
        var expected = TestUtils.parse(jsonExpected, MusicEventDto.class);
        List<MusicEventDto> expectedList = List.of(expected);

        Assertions.assertEquals(musicEventDtoList.size(), expectedList.size());
        Assertions.assertEquals(expectedList, musicEventDtoList);
    }

    @Test
    void successNotAllFieldsPresentFieldsTest() {
        //given
        String jsonFile = "mapper/ticketmastermapper/notAllFieldsPresentSuccess/input.json";
        var given = TestUtils.parse(jsonFile, TicketMasterEventResponse.class);

        //when
        List<MusicEventDto> musicEventDtoList = mapper.map(given);

        //then
        String jsonExpected = "mapper/ticketmastermapper/notAllFieldsPresentSuccess/expected.json";
        var expected = TestUtils.parse(jsonExpected, MusicEventDto.class);
        List<MusicEventDto> expectedList = List.of(expected);

        Assertions.assertEquals(musicEventDtoList.size(), expectedList.size());
        Assertions.assertEquals(expectedList, musicEventDtoList);
    }

}