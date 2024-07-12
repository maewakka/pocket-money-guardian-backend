package com.woo.backend.global.security.service;

import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.entity.repository.UserRepository;
import com.woo.backend.global.security.dto.GuardianUserDetails;
import com.woo.exception.util.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class GuardianUserDetailsService implements UserDetailsService {

    private final JwtValidateService jwtValidateService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = jwtValidateService.findUserByEmail(email);

        return new GuardianUserDetails(user);
    }
}
