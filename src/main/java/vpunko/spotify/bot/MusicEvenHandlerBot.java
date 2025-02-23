package vpunko.spotify.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static vpunko.spotify.bot.BotUserState.*;

public class MusicEvenHandlerBot extends TelegramLongPollingBot {

    private String botUsername;
    private String botToken;
    private UserStateManager userStateManager = new UserStateManager();

    public MusicEvenHandlerBot(String botUsername, String botToken) {
        this.botUsername = botUsername;
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            // Handle user input
            String response = handleUserInput(chatId, messageText);

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


    private String handleUserInput(long chatId, String messageText) {
        BotUserState userState = userStateManager.getUserState(chatId);

        if (messageText.equals("/start")) {
            userStateManager.setUserState(chatId, LOGIN_REQUESTED);
            return "Please enter your login:";
        } else if (userState.equals(LOGIN_REQUESTED)) {
            // Store the login and ask for the password
            userStateManager.setUserLogin(chatId, messageText);
            userStateManager.setUserState(chatId, PASSWORD_REQUESTED);
            return "Please enter your password:";
        } else if (userState.equals(PASSWORD_REQUESTED)) {
            // Validate credentials
            String login = userStateManager.getUserLogin(chatId);
            String password = messageText;

            boolean isValid = validateCredentials(login, password);
            if (isValid) {
                userStateManager.setUserState(chatId, AUTHENTICATED);
                return "Login successful! What would you like to do next? (e.g., /getEvents)";
            } else {
                userStateManager.clearUserState(chatId);
                return "Invalid credentials. Please try again.";
            }
        } else if (messageText.equals("/getEvents") && userState.equals(AUTHENTICATED)) {
            // Fetch events and return JSON
            String artist = "artist";
            String eventsJson = fetchEventsByArtist(artist);
            return formatJsonForUser(eventsJson);
        } else {
            return "Unknown command. Please try again.";
        }
    }

    public boolean validateCredentials(String login, String password) {
        // Implement your logic to validate credentials
        return true; // Replace with actual validation
    }

    public String fetchEventsByArtist(String artist) {
        // Call your API or database to fetch events
        return "[{\"event\": \"Concert\", \"date\": \"2023-12-25\"}]"; // Replace with actual data
    }

    private String formatJsonForUser(String json) {
        // Parse JSON and format it into a readable string
        return "Here are the events:\n" + json; // Replace with actual formatting logic
    }
}
