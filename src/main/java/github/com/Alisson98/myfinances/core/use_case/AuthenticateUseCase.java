package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.adapter.web.exception.InvalidPasswordException;
import github.com.Alisson98.myfinances.adapter.web.exception.ResourceNotFoundException;
import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class AuthenticateUseCase {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticateUseCase.class);

    private final UserRepository userRepository;

    public AuthenticateUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String email, String password) {
        logger.info("Fetching User with email = {}...", email);
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            logger.error("Resource with email {} not found", email);
            throw new ResourceNotFoundException("Resource with email %s not found".formatted(email));}
        if(!user.get().getPassword().equals(password)) {
            logger.error("Invalid password for the User with id = {}", user.get().getId());
            throw new InvalidPasswordException("invalid password");
        }
        logger.info("User with id {} successfully authenticated", user.get().getId());
        return user.get();
    }
}
