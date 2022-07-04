package ru.gb.gbshopmay.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecaptchaService {
 
  @Value("${google.recaptcha.key.secret}")
  String recaptchaSecret;
   
  private static final String GOOGLE_RECAPTCHA_VERIFY_URL =
    "https://www.google.com/recaptcha/api/siteverify";
   
  @Autowired
  RestTemplateBuilder restTemplateBuilder;
 
  public String verifyRecaptcha(String ip, 
    String recaptchaResponse){
    Map<String, String> body = new HashMap<>();
    body.put("secret", recaptchaSecret);
    body.put("response", recaptchaResponse);
    body.put("remoteip", ip);

    ResponseEntity<Map> recaptchaResponseEntity =
      restTemplateBuilder.build()
        .postForEntity(GOOGLE_RECAPTCHA_VERIFY_URL+
          "?secret={secret}&response={response}&remoteip={remoteip}", 
          body, Map.class, body);

    Map<String, Object> responseBody = 
      recaptchaResponseEntity.getBody();

    boolean recaptchaSuccess = (Boolean)responseBody.get("success");

    if ( !recaptchaSuccess) {
      List<String> errorCodes =
        (List)responseBody.get("error-codes");
       
      String errorMessage = errorCodes.stream()
          .collect(Collectors.joining(", "));
           
      return errorMessage;
    }else {
      return "";
    }
  }

  public List<String> reviewValid(String review) {
    List<String> errors = new ArrayList<>();
    if (Objects.equals(review, "")) {
      errors.add("Empty comment field");
    }
    return errors;
  }
 }