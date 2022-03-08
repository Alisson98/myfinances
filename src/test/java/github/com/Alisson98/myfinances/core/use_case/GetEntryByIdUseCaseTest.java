package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.adapter.web.exception.ResourceNotFoundException;
import github.com.Alisson98.myfinances.core.entities.Entry;
import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.entities.enums.EntryStatus;
import github.com.Alisson98.myfinances.core.entities.enums.EntryType;
import github.com.Alisson98.myfinances.core.repository.EntryRepository;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class GetEntryByIdUseCaseTest {

    @MockBean
    private EntryRepository entryRepository;
    private GetEntryByIdUseCase getEntryByIdUseCase;

    @BeforeEach
    void setUp() {
        getEntryByIdUseCase = new GetEntryByIdUseCase(entryRepository);
    }

    @Nested
    class ExecuteTest {
        private static Stream<Arguments> provideEntryFromRepository() {
            return Stream.of(
                    Arguments.of(new Entry()),
                    Arguments.of(new Entry(
                            1L,
                            "Some description",
                            3,
                            2022,
                            BigDecimal.valueOf(3500),
                            EntryType.INCOME,
                            EntryStatus.PENDING,
                            new User(),
                            LocalDate.now())
                    )
            );
        }

        @Test
        void shouldCallDependenciesCorrectly() {
            Long mockedId = 1L;
            Entry mockedEntry = new Entry();
            when(entryRepository.findById(any())).thenReturn(Optional.of(mockedEntry));

            getEntryByIdUseCase.execute(mockedId);

            verify(entryRepository, times(1)).findById(mockedId);
        }

        @ParameterizedTest(name = "when repository returns {0}")
        @MethodSource("provideEntryFromRepository")
        void shouldReturnDataFromRepository(Entry mockedEntry) {
            when(entryRepository.findById(mockedEntry.getEntryId())).thenReturn(Optional.of(mockedEntry));

            Entry result = getEntryByIdUseCase.execute(mockedEntry.getEntryId());
            assertEquals(result, mockedEntry);
        }

        @Test
        @DisplayName("Should throw ResourceNotFoundException when no User is returned")
        void notFound() {
            when(entryRepository.findById(any())).thenReturn(Optional.empty());

            assertThrows(ResourceNotFoundException.class, () -> getEntryByIdUseCase.execute(1L));
        }
    }

}