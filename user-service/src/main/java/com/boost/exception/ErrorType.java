package com.boost.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum ErrorType {
    INTERNAL_ERROR(2000, "Internal Server Error", INTERNAL_SERVER_ERROR),
    BAD_REQUEST_ERROR(2001, "Invalid Parameter Error", BAD_REQUEST),
    LOGIN_ERROR_001(190, "Kullanıcı adı ya da şifre hatalı", INTERNAL_SERVER_ERROR),
    GECERSIZ_TOKEN(200,"Token bilgisi geçersizdir.", INTERNAL_SERVER_ERROR),
    KULLANICI_BULUNAMADI(201,"Güncelleme yapılacak kullanıcı kayıtlı değil.", INTERNAL_SERVER_ERROR);


    private int code;
    private String message;
    HttpStatus httpStatus;

}
