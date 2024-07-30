package com.woo.backend.global.security.service;

import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.entity.repository.UserRepository;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.woo.backend.global.config.CacheConfig.USER_CACHE;

@Service
@RequiredArgsConstructor
public class GetUserWithCachingService {

    private final UserRepository userRepository;

//    @Cacheable(value = USER_CACHE, key = "'user.' + #email")
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(() -> new BizException("user_not_found"));
    }

}
