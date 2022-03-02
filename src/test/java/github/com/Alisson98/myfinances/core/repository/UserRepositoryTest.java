package github.com.Alisson98.myfinances.core.repository;


import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository repositoryTest;
    @Test
    void shouldCheckExistenceOfAnEmail() {
        User mockedUser = User.builder().name("mockedUser").email("email@mocked.com").build();

        repositoryTest.save(mockedUser);

        boolean result = repositoryTest.existsByEmail("email@mocked.com");

        assertTrue(result);
    }

    @Test
    void shouldReturnAnFalseWhenEmailAlreadyRegistered() {
        repositoryTest.deleteAll();

        boolean result = repositoryTest.existsByEmail("email@mocked.com");

        assertFalse(result);
    }

}