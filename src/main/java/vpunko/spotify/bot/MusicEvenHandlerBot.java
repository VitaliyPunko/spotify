package vpunko.spotify.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import vpunko.spotify.bot.handler.UserInputHandler;

@Service
public class MusicEvenHandlerBot extends TelegramLongPollingBot {

    private final String botUsername;
    private final String botToken;
    private final UserInputHandler handler;

    public MusicEvenHandlerBot(
            @Value("${telegram.bot_username}") String botUsername,
            @Value("${telegram.bot_token}") String botToken,
            UserInputHandler handler) {
        this.botUsername = botUsername;
        this.botToken = botToken;
        this.handler = handler;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            // Handle user input
            String response = handler.handleUserInput(chatId, messageText);

            // Send response back to the user
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            message.setText(response);

            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }


}
