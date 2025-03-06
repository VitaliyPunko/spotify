package vpunko.spotify;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import vpunko.spotify.security.TestConfig;


@SpringBootTest()
@Import(TestConfig.class)
class SpotifyApplicationTests {

    @Test
    void contextLoads() {
    }

}
