package com.woo.backend.domain.user.dto.req;

import com.woo.backend.domain.user.entity.User;
import com.woo.backend.domain.user.enums.Role;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
public class SignUpReq {

    private String email;
    private String password;
    private String name;

    public User toEntity(BCryptPasswordEncoder passwordEncoder) {
        return User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .nickName(name)
                .role(Role.USER)
                .kakaoSignUp(false)
                .profileImgPath("profile-img/default_profile.png")
                .build();
    }
}