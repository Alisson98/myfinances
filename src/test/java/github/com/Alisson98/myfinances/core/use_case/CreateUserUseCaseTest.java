package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class CreateUserUseCaseTest {

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private EmailUserValidationUseCase emailUserValidationUseCase;
    private CreateUserUseCase createUserUseCase;

    @BeforeEach
    void setup() {
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
            when(emailUserValidationUseCase.execute(inputUser.getEmail())).thenReturn(false);
            User actualInsertedUser = createUserUseCase.execute(inputUser);
            assertEquals(expectedInsertedUser, actualInsertedUser);
        }
    }
}