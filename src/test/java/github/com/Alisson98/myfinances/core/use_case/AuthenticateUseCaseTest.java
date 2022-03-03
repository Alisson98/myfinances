package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.adapter.web.exception.EmailAlreadyRegisteredException;
import github.com.Alisson98.myfinances.adapter.web.exception.InvalidPasswordException;
import github.com.Alisson98.myfinances.adapter.web.exception.ResourceNotFoundException;
import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
class AuthenticateUseCaseTest {

    @MockBean
    private UserRepository userRepository;

    private AuthenticateUseCase authenticateUseCase;
    @BeforeEach
    void setup() {
        authenticateUseCase = new AuthenticateUseCase(userRepository);
    }
    @Nested
    class ExecuteTest {
        @Test
        void shouldCallDependenciesCorrectly() {

            User mockedUser = User.builder().
                    id(null).
                    name("mockedName").
                    email("email@mocked.com").
                    password("mockedPassword")
                    .build();

            when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(mockedUser));

            authenticateUseCase.execute(mockedUser.getEmail(), mockedUser.getPassword());

            verify(userRepository, times(1)).findByEmail(mockedUser.getEmail());
        }

        @Test
        void shouldReturnEntityAuthenticated() {
            String inputEmail = "email@mocked.com";
            String inputPassword = "mockedPassword";
            User expectedAuthenticatedUser = User.builder().
                    id(2L).
                    name("mockedName").
                    email("email@mocked.com").
                    password("mockedPassword")
                    .build();
            when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(expectedAuthenticatedUser));

            User actualAuthenticatedUser = authenticateUseCase.execute(inputEmail, inputPassword);
            assertNotNull(actualAuthenticatedUser);
            assertEquals(expectedAuthenticatedUser, actualAuthenticatedUser);
        }

        @Test
        @DisplayName("should return an exception when the email does not exist in the database")
        void shouldThrowAnNotFoundException() {
            String mockedEmail = "email@mock.com";
            String mockedPassword = "mockedPassword";
            when(userRepository.findByEmail(mockedEmail)).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> authenticateUseCase.execute(mockedEmail, mockedPassword));
        }

        @Test
        @DisplayName("should return an exception when the password is invalid")
        void shouldThrowAnInvalidPasswordException() {
            String inputEmail = "email@mocked.com";
            String inputPassword = "mockedPassword";
            User expectedAuthenticatedUser = User.builder().
                    id(2L).
                    name("mockedName").
                    email("email@mocked.com").
                    password("otherPassword")
                    .build();
            when(userRepository.findByEmail(inputEmail)).thenReturn(Optional.of(expectedAuthenticatedUser));

            assertThrows(InvalidPasswordException.class, () -> authenticateUseCase.execute(inputEmail, inputPassword));
        }
    }
}