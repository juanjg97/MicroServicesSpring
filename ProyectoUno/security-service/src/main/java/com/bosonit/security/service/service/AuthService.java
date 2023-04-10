package com.bosonit.security.service.service;

import com.bosonit.security.service.dto.AuthUserInputDto;
import com.bosonit.security.service.dto.NewUserInputDto;
import com.bosonit.security.service.dto.TokenDto;
import com.bosonit.security.service.entity.AuthUser;
import com.bosonit.security.service.repository.AuthUserRepository;
import com.bosonit.security.service.security.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private AuthUserRepository authUserRepository;
    private PasswordEncoder passwordEncoder;
    private JwtProvider jwtProvider;

    public AuthUser save(NewUserInputDto newUserInputDto){
        Optional<AuthUser> user = authUserRepository.findByUserName(newUserInputDto.getUserName());
        if(user.isPresent()){
            return null;
        }
        String password = passwordEncoder.encode(newUserInputDto.getPassword());
        AuthUser authUser = AuthUser.builder()
                .userName(newUserInputDto.getUserName())
                .password(password)
                .role(newUserInputDto.getRole())
                .build();
        return authUserRepository.save(authUser);
    }

    public TokenDto login(AuthUserInputDto authUserInputDto){
        Optional<AuthUser> user = authUserRepository.findByUserName(authUserInputDto.getUserName());
        if(!user.isPresent()){
            log.info("User not found: " + authUserInputDto.getUserName());
            return null;
        }
        if(passwordEncoder.matches(authUserInputDto.getPassword(),user.get().getPassword())){
            return new TokenDto(jwtProvider.createToken(user.get()));
        }
        return null;
    }

    public TokenDto validateToken(String token){
        if(!jwtProvider.validate(token)){
            return null;
        }
        String userName = jwtProvider.getUserNameFromToken(token);
        if(!authUserRepository.findByUserName(userName).isPresent()){
            return null;
        }
        return new TokenDto(token);
    }
}
