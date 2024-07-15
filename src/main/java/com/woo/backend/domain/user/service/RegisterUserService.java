package com.woo.backend.domain.user.service;

import com.woo.backend.domain.user.dto.EmailVerifyingDto;
import com.woo.backend.domain.user.dto.req.SignUpReq;
import com.woo.backend.domain.user.entity.repository.UserRepository;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterUserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    private final RedisTemplate<String, Object> redisTemplate;


    public void createUser(final SignUpReq req) {
        if(duplicateEmailCheck(req.getEmail())) throw new BizException("user_already_exist");
        if(duplicateEmailCheck(req.getName())) throw new BizException("duplicated_nickname");

        EmailVerifyingDto emailVerifyingDto = (EmailVerifyingDto) redisTemplate.opsForValue().get("verify:" + req.getEmail());
        if(emailVerifyingDto == null || !emailVerifyingDto.getStatus()) throw new BizException("email_not_verified");

        userRepository.save(req.toEntity(passwordEncoder));
    }


    private Boolean duplicateEmailCheck(String email) {
        return userRepository.existsByEmail(email);
    }

    private Boolean duplicateNickNameCheck(String nickName) {
        return userRepository.existsByNickName(nickName);
    }

}
