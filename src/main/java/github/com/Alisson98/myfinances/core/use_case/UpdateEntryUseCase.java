package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.adapter.web.exception.ResourceNotFoundException;
import github.com.Alisson98.myfinances.core.entities.Entry;
import github.com.Alisson98.myfinances.core.repository.EntryRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateEntryUseCase {
    private static final Logger logger = LoggerFactory.getLogger(UpdateEntryUseCase.class);

    private final EntryRepository entryRepository;


    public UpdateEntryUseCase(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }
    @Transactional
    public Entry execute(@NotNull Entry entry){
        logger.info("Update Entry with id {}", entry.getEntryId());
        if(entryRepository.existsById(entry.getEntryId())){
            entryRepository.save(entry);
            logger.info("Updated entry with id {}", entry.getEntryId());
            return entry;
        }
        logger.warn("Entry with id {} not found, updated failed", entry.getEntryId());
        throw new ResourceNotFoundException("Entry with id " + entry.getEntryId() + "not found, updated failed");

    }
}
