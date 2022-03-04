package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.adapter.web.exception.ResourceNotFoundException;
import github.com.Alisson98.myfinances.core.entities.Entry;
import github.com.Alisson98.myfinances.core.repository.EntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetByIDEntryUseCase {
    private static final Logger logger = LoggerFactory.getLogger(GetByIDEntryUseCase.class);

    private final EntryRepository entryRepository;

    public GetByIDEntryUseCase(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public Entry execute (Long entryId) {
        logger.info("Fetching entry with id = {}...", entryId);
        Optional<Entry> user = entryRepository.findById(entryId);
        return user.orElseThrow(() -> new ResourceNotFoundException("Resource with id %s not found".formatted(entryId)));
    }
}
