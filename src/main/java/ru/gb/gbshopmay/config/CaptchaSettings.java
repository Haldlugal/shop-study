package ru.gb.gbshopmay.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "google.recaptcha.key")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class CaptchaSettings {

    private String site;
    private String secret;

}