package com.boost.controller;

import com.boost.dto.request.LoginRequestDto;
import com.boost.dto.request.RegisterRequestDto;
import com.boost.repository.entity.Auth;
import com.boost.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static  com.boost.constants.ApiUrls.*;

@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping(DOLOGIN)
    public ResponseEntity<String> doLogin(@RequestBody @Valid LoginRequestDto dto){
        return ResponseEntity.ok(authService.doLogin(dto));
    }
    @PostMapping(REGISTER)
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDto dto){
        if(authService.save(dto))
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
    @GetMapping("/getall")
    public ResponseEntity<List<Auth>> getList(){
        return ResponseEntity.ok(authService.findAll());
    }
}