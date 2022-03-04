package github.com.Alisson98.myfinances.adapter.web.mapper;

import github.com.Alisson98.myfinances.adapter.web.dto.UserDto;
import github.com.Alisson98.myfinances.core.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    User userDtoToUser(UserDto userDto);

    UserDto userToUserDto(User user);
}
