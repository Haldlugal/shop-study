package ru.gb.gbapimay.security;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface UserGateway {

    @PostMapping
    ResponseEntity<UserDto> handlePost(@Validated @RequestBody UserDto userDto);

    @PostMapping("/login")
    ResponseEntity<ru.gb.gbapimay.security.LoginResponseBody> handleLogin(@RequestBody AuthenticationUserDto authenticationUserDto);

}
