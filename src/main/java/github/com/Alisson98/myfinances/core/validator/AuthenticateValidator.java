package github.com.Alisson98.myfinances.core.validator;

import github.com.Alisson98.myfinances.adapter.web.exception.InvalidPasswordException;
import github.com.Alisson98.myfinances.core.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class AuthenticateValidator {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticateValidator.class);

    public void execute(User user, String receivedPassword) {
        logger.info("Logging the User with id = {}...", user.getId());

        if(!user.getPassword().equals(receivedPassword)) {
            logger.error("Invalid password for the User with id = {}", user.getId());
            throw new InvalidPasswordException("invalid password");
        }
        logger.info("User with id {} successfully authenticated", user.getId());
    }
}
