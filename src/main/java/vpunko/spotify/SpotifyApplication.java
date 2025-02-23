package vpunko.spotify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import vpunko.spotify.bot.MusicEvenHandlerBot;

@SpringBootApplication
public class SpotifyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpotifyApplication.class, args);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new MusicEvenHandlerBot("MusicEvenHelperBot", "7941376949:AAHeus8Bg2E0aydGgWJEvtdOE1S3col-H1M"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
