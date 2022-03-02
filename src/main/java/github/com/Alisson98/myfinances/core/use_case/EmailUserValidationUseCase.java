package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.adapter.web.exception.BusinessRuleException;
import github.com.Alisson98.myfinances.core.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class EmailUserValidationUseCase {

    private final UserRepository repository;

    public EmailUserValidationUseCase(UserRepository repository) {
        this.repository = repository;
    }

    public void execute(String email){
        if(!(repository.existsByEmail(email))) {
            throw new BusinessRuleException("There is already a user registered with this email");
        }

    }
}
