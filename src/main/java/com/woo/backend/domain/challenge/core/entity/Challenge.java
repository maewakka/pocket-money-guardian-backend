package com.woo.backend.domain.challenge.core.entity;

import com.woo.backend.domain.user.entity.User;
import com.woo.backend.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Challenge extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "CHALLENGE_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="CHALLENGE_SEQUENCE_GENERATOR", sequenceName = "CHALLENGE_SEQUENCE", initialValue = 1, allocationSize = 1)
    private Long id;

    private String name;

    private Integer limit;
    private Integer initialDay;
    @ManyToOne
    private User owner;

    @Builder
    public Challenge(String name, Integer limit, Integer initialDay, User owner) {
        this.name = name;
        this.limit = limit;
        this.initialDay = initialDay;
        this.owner = owner;
    }
}
