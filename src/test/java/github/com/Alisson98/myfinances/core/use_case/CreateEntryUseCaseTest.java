package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.core.entities.Entry;
import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.entities.enums.EntryStatus;
import github.com.Alisson98.myfinances.core.entities.enums.EntryType;
import github.com.Alisson98.myfinances.core.repository.EntryRepository;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class CreateEntryUseCaseTest {

    @MockBean
    private EntryRepository entryRepository;

    private CreateEntryUseCase createEntryUseCase;

    @BeforeEach
    void setup() {
        createEntryUseCase = new CreateEntryUseCase(entryRepository);
    }

    @Nested
    class ExecuteTest {
        @Test
        void shouldCallDependenciesCorrectly() {

            Entry mockedEntry = getMockedEntry();

            createEntryUseCase.execute(mockedEntry);

            verify(entryRepository, times(1)).save(mockedEntry);
        }


        @Test
        void shouldReturnEntitySaved() {
            Entry inputEntry = new Entry(
                    null,
                    "Some description",
                    3,
                    2022,
                    BigDecimal.valueOf(3500),
                    EntryType.INCOME,
                    EntryStatus.PENDING,
                    new User(),
                    LocalDate.now());
            Entry expectedInsertedEntry = new Entry(
                    2L,
                    "Some description",
                    3,
                    2022,
                    BigDecimal.valueOf(3500),
                    EntryType.INCOME,
                    EntryStatus.PENDING,
                    new User(),
                    LocalDate.now());

            when(entryRepository.save(inputEntry)).thenReturn(expectedInsertedEntry);

            Entry actualInsertedUser = createEntryUseCase.execute(inputEntry);
            assertEquals(expectedInsertedEntry, actualInsertedUser);
        }
        @NotNull
        private Entry getMockedEntry() {
            return new Entry(
                    1L,
                    "Some description",
                    3,
                    2022,
                    BigDecimal.valueOf(3500),
                    EntryType.INCOME,
                    EntryStatus.PENDING,
                    new User(),
                    LocalDate.now());
        }
    }
}