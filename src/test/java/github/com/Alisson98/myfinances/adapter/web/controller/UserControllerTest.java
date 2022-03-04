package github.com.Alisson98.myfinances.adapter.web.controller;

import github.com.Alisson98.myfinances.adapter.web.dto.LoginDto;
import github.com.Alisson98.myfinances.adapter.web.dto.UserDto;
import github.com.Alisson98.myfinances.adapter.web.mapper.UserDtoMapper;
import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.use_case.CreateUserUseCase;
import github.com.Alisson98.myfinances.core.use_case.GetUserByEmailUseCase;
import github.com.Alisson98.myfinances.core.validator.AuthenticateValidator;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
class UserControllerTest {

    @MockBean
    private CreateUserUseCase createUserUseCase;
    @SpyBean
    private AuthenticateValidator authenticateValidator;
    @MockBean
    private GetUserByEmailUseCase getUserByEmailUseCase;
    @MockBean
    private UserDtoMapper userDtoMapper;

    private UserController userController;

    @BeforeEach
    void setUp() {
        userController = new UserController(createUserUseCase,
                authenticateValidator,
                getUserByEmailUseCase,
                userDtoMapper);
    }

    @NotNull
    private UserDto getMockedUserDto() {
        return new UserDto("John",
                "user@email.com",
                "encryptedPassword"
        );
    }

    @NotNull
    private User getMockedUser() {
        return new User(2L,
                "John",
                "user@email.com",
                "encryptedPassword"
        );
    }
    @NotNull
    private LoginDto getMockedLoginDto() {
        return new LoginDto(
                "user@email.com",
                "encryptedPassword"
        );
    }

    @Nested
    class InsertUserTest {

        @Test
        void shouldCallDependenciesCorrectly() {
            User user = getMockedUser();
            UserDto userDto = getMockedUserDto();

            when(userDtoMapper.userDtoToUser(userDto)).thenReturn(user);
            when(userDtoMapper.userToUserDto(user)).thenReturn(userDto);
            when(createUserUseCase.execute(user)).thenReturn(user);

            userController.insertUser(userDto);

            verify(userDtoMapper, times(1)).userDtoToUser(userDto);
            verify(createUserUseCase, times(1)).execute(user);
            verify(userDtoMapper, times(1)).userToUserDto(user);
        }

        @Test
        void shouldReturnCreatedStatusTest() {
            User user = getMockedUser();
            UserDto userDto = getMockedUserDto();

            when(userDtoMapper.userDtoToUser(userDto)).thenReturn(user);
            when(userDtoMapper.userToUserDto(user)).thenReturn(userDto);
            when(createUserUseCase.execute(user)).thenReturn(user);

            ResponseEntity<UserDto> response = userController.insertUser(userDto);


            assertTrue(response.hasBody());
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
            assertEquals(userDto, response.getBody());

        }
    }
    @Nested
    class authenticateUser {
        @Test
        void shouldCallDependenciesCorrectly() {
            User user = getMockedUser();
            LoginDto loginDto = getMockedLoginDto();
            UserDto userDto = getMockedUserDto();
            when(userDtoMapper.userToUserDto(user)).thenReturn(userDto);
            when(getUserByEmailUseCase.execute(loginDto.getEmail())).thenReturn(user);
            doNothing().when(authenticateValidator).execute(user, loginDto.getPassword());


            userController.authenticateUser(loginDto);


            verify(getUserByEmailUseCase, times(1)).execute(loginDto.getEmail());
            verify(authenticateValidator, times(1)).execute(user, loginDto.getPassword());
            verify(userDtoMapper, times(1)).userToUserDto(user);
        }

        @Test
        void shouldReturnOkStatusTest() {
            User user = getMockedUser();
            UserDto userDto = getMockedUserDto();
            LoginDto loginDto = getMockedLoginDto();

            when(getUserByEmailUseCase.execute(loginDto.getEmail())).thenReturn(user);
            when(userDtoMapper.userToUserDto(user)).thenReturn(userDto);
            doNothing().when(authenticateValidator).execute(user, loginDto.getPassword());

            ResponseEntity<UserDto> response = userController.authenticateUser(loginDto);


            assertTrue(response.hasBody());
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertEquals(userDto, response.getBody());

        }
    }


}

