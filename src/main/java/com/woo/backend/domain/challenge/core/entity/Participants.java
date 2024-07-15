package com.woo.backend.domain.challenge.core.entity;

import com.woo.backend.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Participants {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "PARTICIPANTS_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="PARTICIPANTS_SEQUENCE_GENERATOR", sequenceName = "PARTICIPANTS_SEQUENCE", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne
    private Challenge challenge;

    @ManyToOne
    private User participant;

    @Builder
    public Participants(Challenge challenge, User participant) {
        this.challenge = challenge;
        this.participant = participant;
    }
}
