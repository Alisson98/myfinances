package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {
    private static final Logger logger = LoggerFactory.getLogger(CreateUserUseCase.class);

    private final UserRepository userRepository;
    private final EmailUserValidationUseCase emailUserValidationUseCase;

    public CreateUserUseCase(UserRepository userRepository, EmailUserValidationUseCase emailUserValidationUseCase) {
        this.userRepository = userRepository;
        this.emailUserValidationUseCase = emailUserValidationUseCase;
    }

    public User execute(User user){
        logger.info("Saving User with name: {}", user.getName());
        emailUserValidationUseCase.execute(user.getEmail());
        User savedUSer = userRepository.save(user);
        logger.info("User with name {} saved successfully", user.getName());
        return savedUSer;
    }
}
