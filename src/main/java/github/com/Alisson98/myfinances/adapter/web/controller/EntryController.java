package github.com.Alisson98.myfinances.adapter.web.controller;

import github.com.Alisson98.myfinances.adapter.web.dto.EntryDto;
import github.com.Alisson98.myfinances.adapter.web.mapper.EntryDtoMapper;
import github.com.Alisson98.myfinances.core.entities.Entry;
import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.entities.enums.EntryStatus;
import github.com.Alisson98.myfinances.core.use_case.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/entries")
public class EntryController {
    private static final Logger logger = LoggerFactory.getLogger(EntryController.class);

    private final CreateEntryUseCase createEntryUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetEntryByIdUseCase getEntryByIdUseCase;
    private final UpdateEntryUseCase updateEntryUseCase;
    private final DeleteEntryByIdUseCase deleteEntryByIdUseCase;
    private final GetAllEntriesWithFilterUseCase getAllEntriesWithFilterUseCase;
    private final EntryDtoMapper entryDtoMapper;

    public EntryController(CreateEntryUseCase createEntryUseCase,
                           GetUserByIdUseCase getUserByIdUseCase,
                           GetEntryByIdUseCase getEntryByIdUseCase,
                           UpdateEntryUseCase updateEntryUseCase,
                           DeleteEntryByIdUseCase deleteEntryByIdUseCase,
                           GetAllEntriesWithFilterUseCase getAllEntriesWithFilterUseCase,
                           EntryDtoMapper entryDtoMapper) {
        this.createEntryUseCase = createEntryUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.getEntryByIdUseCase = getEntryByIdUseCase;
        this.updateEntryUseCase = updateEntryUseCase;
        this.deleteEntryByIdUseCase = deleteEntryByIdUseCase;
        this.getAllEntriesWithFilterUseCase = getAllEntriesWithFilterUseCase;
        this.entryDtoMapper = entryDtoMapper;
    }

    @PostMapping("/insert")
    public ResponseEntity<EntryDto> insertEntry(@Valid @RequestBody EntryDto entryDto) {
        logger.info("Received request to insert entry");

        User user = getUserByIdUseCase.execute(entryDto.getUserId());
        Entry insertedEntry = createEntryUseCase.execute(entryDtoMapper.mapDtoToEntity(entryDto, user));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entryDtoMapper.mapEntityToDto(insertedEntry));
    }

    @PutMapping("/{entryId}")
    public ResponseEntity<EntryDto> updateEntry(@PathVariable Long entryId, @Valid @RequestBody EntryDto entryDto) {
        logger.info("Received request to update entry");

        getEntryByIdUseCase.execute(entryId);
        User user = getUserByIdUseCase.execute(entryDto.getUserId());
        Entry entry = entryDtoMapper.mapDtoToEntity(entryDto, user);
        entry.setEntryId(entryId);
        Entry updatedEntry = updateEntryUseCase.execute(entry);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(entryDtoMapper.mapEntityToDto(updatedEntry));
    }

    @DeleteMapping("/{entryId}")
    public ResponseEntity<String> deleteEntry(@PathVariable Long entryId) {
        logger.info("Received request to delete entry");
        deleteEntryByIdUseCase.execute(entryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
    }

    @GetMapping
    public ResponseEntity<List<EntryDto>> findAllFiltered(@RequestParam(value = "description", required = false) String description,
                                                          @RequestParam(value = "month", required = false) Integer month,
                                                          @RequestParam(value = "year", required = false) Integer year,
                                                          @RequestParam(value = "status", required = false) String status,
                                                          @RequestParam(value = "userId") Long userId) {
        Entry filteredEntry = new Entry();
        filteredEntry.setDescription(description);
        filteredEntry.setMonth(month);
        filteredEntry.setYear(year);
        filteredEntry.setStatus(EntryStatus.valueOf(status));
        User user = getUserByIdUseCase.execute(userId);
        filteredEntry.setUser(user);
        List<Entry> entries = getAllEntriesWithFilterUseCase.execute(filteredEntry);
        List<EntryDto> entryDtos = entries.stream().
                map(entryDtoMapper::mapEntityToDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(entryDtos);
    }
}
