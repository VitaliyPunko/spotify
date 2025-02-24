package vpunko.spotify.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vpunko.spotify.security.entity.User;
import vpunko.spotify.security.repository.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TelegramAuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public boolean validateCredentials(String login, String password) {
        // Fetch the user from the database
        Optional<User> optionalUser = userRepository.findByEmail(login);
        if (optionalUser.isEmpty()) {
            return false; // User not found
        }
        // Compare the entered password with the hashed password in the database
        return passwordEncoder.matches(password, optionalUser.get().getPassword());
    }
}
