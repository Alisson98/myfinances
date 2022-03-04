package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.adapter.web.exception.EmailAlreadyRegisteredException;
import github.com.Alisson98.myfinances.core.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailUserValidationUseCase {

    private final UserRepository repository;

    public EmailUserValidationUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public boolean execute(String email){
        if(repository.existsByEmail(email)) {
            throw new EmailAlreadyRegisteredException("There is already a user registered with this email");
        }
        return false;

    }
}
