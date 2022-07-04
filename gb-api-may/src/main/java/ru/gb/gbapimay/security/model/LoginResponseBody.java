package ru.gb.gbapimay.security;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponseBody {

    private String token;
}
