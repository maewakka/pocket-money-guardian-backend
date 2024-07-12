package com.woo.backend.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Table(name = "guardian_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "USER_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="USER_SEQUENCE_GENERATOR", sequenceName = "USER_SEQUENCE", initialValue = 1, allocationSize = 1)
    private Long id;

    private String email;
    private String password;

    private String name;
    private String profileImgPath;
    private Boolean kakaoSignUp;

    @Builder
    public User(String email, String password, String name, String profileImgPath, Boolean kakaoSignUp) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.profileImgPath = profileImgPath;
        this.kakaoSignUp = kakaoSignUp;
    }
}
