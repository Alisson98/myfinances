package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.adapter.web.exception.ResourceNotFoundException;
import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserByEmailUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GetUserByEmailUseCase.class);

    private final UserRepository userRepository;

    public GetUserByEmailUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute (String userEmail) {
        logger.info("Fetching user with email = {}...", userEmail);
        Optional<User> user = userRepository.findByEmail(userEmail);
        return user.orElseThrow(() -> new ResourceNotFoundException("Resource with email %s not found".formatted(userEmail)));
    }
}
