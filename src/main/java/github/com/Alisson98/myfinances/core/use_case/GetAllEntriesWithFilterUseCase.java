package github.com.Alisson98.myfinances.core.use_case;

import github.com.Alisson98.myfinances.core.entities.Entry;
import github.com.Alisson98.myfinances.core.repository.EntryRepository;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;



import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GetAllEntriesWithFilterUseCase {

    private static final Logger logger = LoggerFactory.getLogger(GetAllEntriesWithFilterUseCase.class);

    private final EntryRepository entryRepository;


    public GetAllEntriesWithFilterUseCase(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }
    @Transactional( readOnly = true)
    public List<Entry> execute(@NotNull Entry filterEntry){
        logger.info("fetching all filtered entries");
        Example<Entry> example = Example.of( filterEntry,
                ExampleMatcher.matching().
                withIgnoreCase().
                withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));

        return entryRepository.findAll(example);

    }
}
