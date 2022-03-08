package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.adapter.web.exception.ResourceNotFoundException;
import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class GetUserByIdUseCaseTest {

    @MockBean
    private UserRepository userRepository;
    private GetUserByIdUseCase getUserByIdUseCase;

    @BeforeEach
    void setUp() {
        getUserByIdUseCase = new GetUserByIdUseCase(userRepository);
    }

    @Nested
    class ExecuteTest {
        private static Stream<Arguments> provideUserFromRepository() {
            return Stream.of(
                    Arguments.of(new User()),
                    Arguments.of(new User(
                            1L,
                            "mockName",
                            "mockEmail",
                            "mockPassword")
                    )
            );
        }

        @Test
        void shouldCallDependenciesCorrectly() {
            Long mockedId = 1L;
            User mockedUser = new User();
            when(userRepository.findById(any())).thenReturn(Optional.of(mockedUser));

            getUserByIdUseCase.execute(mockedId);

            verify(userRepository, times(1)).findById(mockedId);
        }

        @ParameterizedTest(name = "when repository returns {0}")
        @MethodSource("provideUserFromRepository")
        void shouldReturnDataFromRepository(User mockedUser) {
            when(userRepository.findById(mockedUser.getId())).thenReturn(Optional.of(mockedUser));

            User result = getUserByIdUseCase.execute(mockedUser.getId());
            assertEquals(result, mockedUser);
        }

        @Test
        @DisplayName("Should throw ResourceNotFoundException when no User is returned")
        void notFound() {
            when(userRepository.findById(any())).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> getUserByIdUseCase.execute(1L));
        }
    }

}
