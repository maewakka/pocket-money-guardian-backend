package com.woo.backend.domain.user.entity;

import com.woo.backend.domain.user.enums.Role;
import com.woo.backend.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@Table(name = "guardian_user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "USER_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="USER_SEQUENCE_GENERATOR", sequenceName = "USER_SEQUENCE", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(unique = true)
    private String email;
    private String password;
    @Column(unique = true)
    private String nickName;
    private String profileImgPath;
    private Boolean kakaoSignUp;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String email, String password, String nickName, String profileImgPath, Boolean kakaoSignUp, Role role) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.profileImgPath = profileImgPath;
        this.kakaoSignUp = kakaoSignUp;
        this.role = role;
    }
}
