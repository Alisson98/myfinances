package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.adapter.web.exception.ResourceNotFoundException;
import github.com.Alisson98.myfinances.core.repository.EntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Service
public class DeleteEntryByIdUseCase {
    private static final Logger logger = LoggerFactory.getLogger(DeleteEntryByIdUseCase.class);

    private final EntryRepository entryRepository;

    public DeleteEntryByIdUseCase(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    public void execute(Long entryId) {
        logger.info("Deleting entry with id {}", entryId);
        try {

            entryRepository.deleteById(entryId);

            logger.info("Entry with id {} deleted successfully", entryId);
        } catch (EmptyResultDataAccessException emptyResultDataAccessException) {

            logger.warn("Assessment with questionId {} not found", entryId);

            throw new ResourceNotFoundException("Entry  with id " + entryId + " not found");
        }
    }
}
