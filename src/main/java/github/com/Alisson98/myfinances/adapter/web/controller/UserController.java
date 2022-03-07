package github.com.Alisson98.myfinances.adapter.web.controller;


import github.com.Alisson98.myfinances.adapter.web.dto.LoginDto;
import github.com.Alisson98.myfinances.adapter.web.dto.UserDto;
import github.com.Alisson98.myfinances.adapter.web.mapper.UserDtoMapper;
import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.validator.AuthenticateValidator;
import github.com.Alisson98.myfinances.core.use_case.CreateUserUseCase;
import github.com.Alisson98.myfinances.core.use_case.GetUserByEmailUseCase;
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
    private final AuthenticateValidator authenticateValidator;
    private final GetUserByEmailUseCase getUserByEmailUseCase;
    private final UserDtoMapper userDtoMapper;

    public UserController(CreateUserUseCase createUserUseCase, AuthenticateValidator authenticateValidator,
                          GetUserByEmailUseCase getUserByEmailUseCase,
                          UserDtoMapper userDtoMapper) {
        this.authenticateValidator = authenticateValidator;
        this.createUserUseCase = createUserUseCase;
        this.getUserByEmailUseCase = getUserByEmailUseCase;
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
    public ResponseEntity<UserDto> authenticateUser(@Valid @RequestBody LoginDto loginDto){
        logger.info("Received request to authenticate user");
        User user = getUserByEmailUseCase.execute(loginDto.getEmail());
        authenticateValidator.execute(user,loginDto.getPassword());

        return ResponseEntity.status(HttpStatus.OK)
                .body(userDtoMapper.userToUserDto(user));
    }
}
