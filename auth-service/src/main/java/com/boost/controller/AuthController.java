package com.boost.controller;
import com.boost.dto.request.GetAllRolesRequestDto;
import com.boost.dto.request.LoginRequestDto;
import com.boost.dto.request.RegisterRequestDto;
import com.boost.dto.response.LoginResponseDto;
import com.boost.dto.response.RegisterResponseDto;
import com.boost.rabbitmq.producer.MessageProducer;
import com.boost.repository.entity.Auth;
import com.boost.repository.entity.AuthRoles;
import com.boost.repository.entity.Authorities;
import com.boost.repository.enums.Activated;
import com.boost.repository.enums.Roles;
import com.boost.service.AuthRolesServices;
import com.boost.service.AuthService;
import com.boost.service.AuthoritiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static com.boost.constants.ApiUrls.*;
@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final MessageProducer  messageProducer;

    private final AuthRolesServices authRolesServices;
    private final AuthoritiesService authoritiesServices;
    @PostMapping(DOLOGIN)
    public ResponseEntity<LoginResponseDto> doLogin(@RequestBody @Valid LoginRequestDto dto){
        String token = authService.doLogin(dto);
        return ResponseEntity.ok(LoginResponseDto.builder()
                .token(token)
                .code(1201)
                .message("Giriş başarılı")
                .build());
    }
    @PostMapping(REGISTER)
    public ResponseEntity<RegisterResponseDto> register(@RequestBody @Valid RegisterRequestDto dto){
        if(authService.save(dto))
            return ResponseEntity.ok(RegisterResponseDto.builder()
                    .code(1200)
                    .message("Kayıt Başarılı")
                    .build());
        return ResponseEntity.badRequest().body(RegisterResponseDto.builder()
                .message("Kayıt Başarısız")
                .code(1400)
                .build());
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Auth>> getList(){
        return ResponseEntity.ok(authService.findAll());
    }

    @PostMapping("/sendmessage")
    public ResponseEntity<Void> sendMessage(String message, Long code){
        messageProducer.sendMessage(message, code);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getallroles")
    public ResponseEntity<List<Authorities>> getAllRoles(){
        return ResponseEntity.ok(authoritiesServices.findAll());
    }

    @PostMapping("/saveroles")
    public ResponseEntity<Void> saveRoles(String roleName){
        authoritiesServices.save(Authorities.builder()
                .name(roleName)
                .build());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/saveauthloes")
    public ResponseEntity<Void> saveAuthRoles(Long authid, Long roleid){
        authRolesServices.save(AuthRoles.builder()
                .authid(authid)
                .roleid(roleid)
                .build());
        return ResponseEntity.ok().build();
    }
    @PostMapping("/getrolesbyauthid")
    public ResponseEntity<List<String>> getAllRolesByAuthid(@RequestBody GetAllRolesRequestDto dto){
        return ResponseEntity.ok(authRolesServices.getRolesByAuthid(dto.getAuthid()));
    }

}