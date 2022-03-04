package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.core.entities.Entry;
import github.com.Alisson98.myfinances.core.repository.EntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateEntryUseCase {
    private static final Logger logger = LoggerFactory.getLogger(CreateEntryUseCase.class);

    private final EntryRepository entryRepository;


    public CreateEntryUseCase(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }
    @Transactional
    public Entry execute(Entry entry){
        logger.info("Saving Entry");
        Entry savedEntry = entryRepository.save(entry);
        logger.info("User with id {} saved successfully", entry.getEntryId());
        return savedEntry;
    }
}
