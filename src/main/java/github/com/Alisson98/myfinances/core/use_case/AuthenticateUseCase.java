package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.adapter.web.exception.AuthenticateErrorException;
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
        if(user.isEmpty()){ throw new AuthenticateErrorException("Resource with name %s not found".formatted(email));}
        if(!user.get().getPassword().equals(password)) {
            throw new AuthenticateErrorException("invalid password");
        }
        return user.get();
    }
}
