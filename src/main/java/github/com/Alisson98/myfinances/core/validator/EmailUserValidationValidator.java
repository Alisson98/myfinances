package github.com.Alisson98.myfinances.core.validator;

import github.com.Alisson98.myfinances.adapter.web.exception.EmailAlreadyRegisteredException;
import github.com.Alisson98.myfinances.core.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailUserValidationValidator {

    private final UserRepository repository;

    public EmailUserValidationValidator(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String email){
        if(repository.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException("There is already a user registered with this email");
        }
        return false;

    }
}
