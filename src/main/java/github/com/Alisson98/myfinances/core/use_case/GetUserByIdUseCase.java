package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.adapter.web.exception.ResourceNotFoundException;
import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserByIdUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GetUserByIdUseCase.class);

    private final UserRepository userRepository;

    public GetUserByIdUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute (Long userID) {
        logger.info("Fetching user with id = {}...", userID);
        Optional<User> user = userRepository.findById(userID);
        return user.orElseThrow(() -> new ResourceNotFoundException("Resource with id %s not found".formatted(userID)));
    }
}
