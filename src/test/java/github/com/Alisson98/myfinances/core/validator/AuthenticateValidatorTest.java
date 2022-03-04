package github.com.Alisson98.myfinances.core.validator;

import github.com.Alisson98.myfinances.adapter.web.exception.InvalidPasswordException;
import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.validator.AuthenticateValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class AuthenticateValidatorTest {

    private AuthenticateValidator authenticateValidator;

    @BeforeEach
    void setup() {
        authenticateValidator = new AuthenticateValidator();
    }

    @Nested
    class ExecuteTest {

        @Test
        @DisplayName("should return an exception when the password is invalid")
        void shouldThrowAnInvalidPasswordException() {
            String inputPassword = "mockedPassword";
            User expectedAuthenticatedUser = User.builder().
                    id(2L).
                    name("mockedName").
                    email("email@mocked.com").
                    password("otherPassword")
                    .build();

            assertThrows(InvalidPasswordException.class, () -> authenticateValidator.execute(expectedAuthenticatedUser, inputPassword));
        }
    }
}