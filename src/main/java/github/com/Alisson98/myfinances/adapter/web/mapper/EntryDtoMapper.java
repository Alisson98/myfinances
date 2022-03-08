package github.com.Alisson98.myfinances.adapter.web.mapper;

import github.com.Alisson98.myfinances.adapter.web.dto.EntryDto;
import github.com.Alisson98.myfinances.core.entities.Entry;
import github.com.Alisson98.myfinances.core.entities.User;
import github.com.Alisson98.myfinances.core.entities.enums.EntryStatus;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface EntryDtoMapper {

    EntryDto mapEntityToDto( Entry entry);

    Entry mapDtoToEntity( EntryDto entryDto, User user);

    @BeforeMapping
    default void setUserInEntity(User user, @MappingTarget Entry entity){
        entity.setUser(user);

    }

    @BeforeMapping
    default void setUserIdInDto(Entry entry, @MappingTarget EntryDto dto){
        dto.setUserId(entry.getUser().getId());
    }

    @BeforeMapping
    default void test(EntryDto entryDto, @MappingTarget Entry entry){
        if(entryDto.getStatus() == null){
            entry.setStatus(EntryStatus.PENDING);
        }
    }
}
