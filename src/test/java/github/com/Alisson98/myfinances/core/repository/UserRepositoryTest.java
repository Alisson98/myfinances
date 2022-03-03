package github.com.Alisson98.myfinances.core.repository;


import github.com.Alisson98.myfinances.core.entities.User;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase( replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    UserRepository repositoryTest;

    @Autowired
    TestEntityManager entityManager;
    @Test
    void shouldCheckExistenceOfAnEmail() {
        User mockedUser = User.builder().name("mockedUser").email("email@mocked.com").build();

        entityManager.persist(mockedUser);

        boolean result = repositoryTest.existsByEmail("email@mocked.com");

        assertTrue(result);
    }

    @Test
    void shouldReturnAnFalseWhenEmailAlreadyRegistered() {
        boolean result = repositoryTest.existsByEmail("email@mocked.com");

        assertFalse(result);
    }

}