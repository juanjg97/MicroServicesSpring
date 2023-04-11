package com.bosonit.security.service.controller;

import com.bosonit.security.service.dto.AuthUserInputDto;
import com.bosonit.security.service.dto.NewUserInputDto;
import com.bosonit.security.service.dto.RequestDto;
import com.bosonit.security.service.dto.TokenDto;
import com.bosonit.security.service.entity.AuthUser;
import com.bosonit.security.service.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody AuthUserInputDto authUserInputDto){
        TokenDto tokenDto = authService.login(authUserInputDto);
        if(authUserInputDto == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token, @RequestBody RequestDto requestDto){
        TokenDto tokenDto = authService.validateToken(token,requestDto);
        if(tokenDto == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(tokenDto);
    }

    @PostMapping("/create")
    public ResponseEntity<AuthUser> create(@RequestBody NewUserInputDto newUserInputDto){
        AuthUser authUser = authService.save((newUserInputDto));
        if(authUser == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(authUser);
    }
}
