package ru.gb.gbshopmay.web.dto.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ru.gb.gbapimay.security.UserDto;
import ru.gb.gbapimay.security.UserDto.UserDtoBuilder;
import ru.gb.gbshopmay.entity.security.AccountUser;
import ru.gb.gbshopmay.entity.security.AccountUser.AccountUserBuilder;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-07-01T16:23:59+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.4.1.jar, environment: Java 17.0.3 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public AccountUser toAccountUser(UserDto userDto) {
        if ( userDto == null ) {
            return null;
        }

        AccountUserBuilder accountUser = AccountUser.builder();

        accountUser.id( userDto.getId() );
        accountUser.username( userDto.getUsername() );
        accountUser.password( userDto.getPassword() );
        accountUser.firstname( userDto.getFirstname() );
        accountUser.lastname( userDto.getLastname() );
        accountUser.email( userDto.getEmail() );
        accountUser.phone( userDto.getPhone() );

        return accountUser.build();
    }

    @Override
    public UserDto toUserDto(AccountUser accountUser) {
        if ( accountUser == null ) {
            return null;
        }

        UserDtoBuilder userDto = UserDto.builder();

        userDto.id( accountUser.getId() );
        userDto.username( accountUser.getUsername() );
        userDto.password( accountUser.getPassword() );
        userDto.firstname( accountUser.getFirstname() );
        userDto.lastname( accountUser.getLastname() );
        userDto.email( accountUser.getEmail() );
        userDto.phone( accountUser.getPhone() );

        return userDto.build();
    }
}
