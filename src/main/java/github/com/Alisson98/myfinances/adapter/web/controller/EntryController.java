package github.com.Alisson98.myfinances.adapter.web.controller;

import github.com.Alisson98.myfinances.adapter.web.dto.EntryDto;
import github.com.Alisson98.myfinances.adapter.web.mapper.EntryDtoMapper;
import github.com.Alisson98.myfinances.core.entities.Entry;
import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.use_case.CreateEntryUseCase;
import github.com.Alisson98.myfinances.core.use_case.GetUserByIdUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/entries")
public class EntryController {
    private static final Logger logger = LoggerFactory.getLogger(EntryController.class);

    private final CreateEntryUseCase createEntryUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final EntryDtoMapper entryDtoMapper;

    public EntryController(CreateEntryUseCase createEntryUseCase,
                           GetUserByIdUseCase getUserByIdUseCase,
                           EntryDtoMapper entryDtoMapper) {
        this.createEntryUseCase = createEntryUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.entryDtoMapper = entryDtoMapper;
    }


    @PostMapping("/insert")
    public ResponseEntity<EntryDto> insertEntry(@Valid @RequestBody EntryDto entryDto) {
        logger.info("Received request to insert entry");

        User user = getUserByIdUseCase.execute(entryDto.getUserId());
        Entry insertedEntry = createEntryUseCase.execute(entryDtoMapper.mapDtoToEntity(entryDto,user));

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(entryDtoMapper.mapEntityToDto(insertedEntry));
    }
}
