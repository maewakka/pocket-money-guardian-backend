package com.woo.backend.global.security.service;

import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.entity.repository.UserRepository;
import com.woo.backend.global.security.util.JwtTokenProvider;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtValidateService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    public String getUserEmail(String token) {
        return jwtTokenProvider.extractAllClaims(token)
                .get("email", String.class);
    }

    @Cacheable(cacheNames = USER_CACHE, key = "'user:' + #p0")
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new BizException("user_not_found"));
    }
}
