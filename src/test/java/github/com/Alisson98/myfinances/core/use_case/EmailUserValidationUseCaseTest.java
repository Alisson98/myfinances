package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.adapter.web.exception.EmailAlreadyRegisteredException;
import github.com.Alisson98.myfinances.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class EmailUserValidationUseCaseTest {

    private UserRepository  userRepository;
    private EmailUserValidationUseCase emailUserValidationUseCase;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        emailUserValidationUseCase = new EmailUserValidationUseCase(userRepository);
    }

    @Nested
    class ExecuteTest {
        @Test
        void shouldCallRepositoryCorrectly() {
            String mockedEmail = "email@mock.com";

            when(userRepository.existsByEmail(mockedEmail)).thenReturn(true);

            emailUserValidationUseCase.execute(mockedEmail);

            verify(userRepository, times(1)).existsByEmail(mockedEmail);
        }

        @Test
        @DisplayName("should return an exception when email already registered")
        void shouldThrowAnException() {
            String mockedEmail = "email@mock.com";
            when(userRepository.existsByEmail(mockedEmail)).thenReturn(false);

            assertThrows(EmailAlreadyRegisteredException.class, () -> emailUserValidationUseCase.execute(mockedEmail));
        }
    }

}