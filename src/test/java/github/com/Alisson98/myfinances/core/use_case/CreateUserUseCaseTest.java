package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CreateUserUseCaseTest {

    private UserRepository userRepository;
    private EmailUserValidationUseCase emailUserValidationUseCase;
    private CreateUserUseCase createUserUseCase;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        emailUserValidationUseCase = mock(EmailUserValidationUseCase.class);
        createUserUseCase = new CreateUserUseCase(userRepository, emailUserValidationUseCase);
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
            createUserUseCase.execute(mockedUser);

            verify(userRepository, times(1)).save(mockedUser);
            verify(emailUserValidationUseCase, times(1)).execute(mockedUser.getEmail());
        }

    @Test
    void shouldReturnEntitySaved() {
        User inputUser = User.builder().
                id(null).
                name("mockedName").
                email("email@mocked.com").
                password("mockedPassword")
                .build();
        User expectedInsertedUser = User.builder().
                id(2L).
                name("mockedName").
                email("email@mocked.com").
                password("mockedPassword")
                .build();
        when(userRepository.save(inputUser)).thenReturn(expectedInsertedUser);
        when(emailUserValidationUseCase.execute(inputUser.getEmail())).thenReturn(true);
        User actualInsertedUser = createUserUseCase.execute(inputUser);
        assertEquals(expectedInsertedUser, actualInsertedUser);
    }
    }
}