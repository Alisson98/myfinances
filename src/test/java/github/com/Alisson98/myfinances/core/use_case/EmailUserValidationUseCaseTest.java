package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.adapter.web.exception.EmailAlreadyRegisteredException;
import github.com.Alisson98.myfinances.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class EmailUserValidationUseCaseTest {

    @MockBean
    private UserRepository  userRepository;
    private EmailUserValidationUseCase emailUserValidationUseCase;

    @BeforeEach
    void setup() {
        emailUserValidationUseCase = new EmailUserValidationUseCase(userRepository);
    }

    @Nested
    class ExecuteTest {
        @Test
        void shouldCallRepositoryCorrectly() {
            String mockedEmail = "email@mock.com";

            when(userRepository.existsByEmail(mockedEmail)).thenReturn(false);

            emailUserValidationUseCase.execute(mockedEmail);

            verify(userRepository, times(1)).existsByEmail(mockedEmail);
        }

        @Test
        @DisplayName("should return an exception when email already registered")
        void shouldThrowAnException() {
            String mockedEmail = "email@mock.com";
            when(userRepository.existsByEmail(mockedEmail)).thenReturn(true);

            assertThrows(EmailAlreadyRegisteredException.class, () -> emailUserValidationUseCase.execute(mockedEmail));
        }
    }

}