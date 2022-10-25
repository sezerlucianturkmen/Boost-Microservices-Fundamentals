package com.boost.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class FallBackController {

    @GetMapping("/fallback-user-service")
    public ResponseEntity<String> fallbackUserService(){
        return ResponseEntity.ok("Userservice is not available");
    }
    @GetMapping("/fallback-auth-service")
    public ResponseEntity<String> fallbackAuthService(){
        return ResponseEntity.ok("Authservice is not available");
    }

}
