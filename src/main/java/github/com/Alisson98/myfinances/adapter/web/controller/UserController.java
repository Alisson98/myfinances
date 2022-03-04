package github.com.Alisson98.myfinances.adapter.web.controller;


import github.com.Alisson98.myfinances.adapter.web.dto.UserDto;
import github.com.Alisson98.myfinances.adapter.web.mapper.UserDtoMapper;
import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.use_case.AuthenticateUseCase;
import github.com.Alisson98.myfinances.core.use_case.CreateUserUseCase;
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
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final CreateUserUseCase createUserUseCase;
    private final AuthenticateUseCase authenticateUseCase;
    private final UserDtoMapper userDtoMapper;

    public UserController(CreateUserUseCase createUserUseCase,AuthenticateUseCase authenticateUseCase,
                          UserDtoMapper userDtoMapper) {
        this.authenticateUseCase = authenticateUseCase;
        this.createUserUseCase = createUserUseCase;
        this.userDtoMapper = userDtoMapper;
    }

    @PostMapping("/insert")
    public ResponseEntity<UserDto> insertUser(@Valid @RequestBody UserDto userDto){
        logger.info("Received request to insert user");
        User user = userDtoMapper.userDtoToUser(userDto);
        User insertedUser = createUserUseCase.execute(user);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userDtoMapper.userToUserDto(insertedUser));
    }
    @PostMapping("/login")
    public ResponseEntity<UserDto> authenticateUser(@RequestBody UserDto userDto){
        logger.info("Received request to authenticate user");
        User user = authenticateUseCase.execute(userDto.getEmail(),userDto.getPassword());
        return ResponseEntity.status(HttpStatus.OK)
                .body(userDtoMapper.userToUserDto(user));
    }
}
