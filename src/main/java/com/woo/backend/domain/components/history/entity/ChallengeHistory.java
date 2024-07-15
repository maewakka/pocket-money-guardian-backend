package com.woo.backend.domain.components.history.entity;

import com.woo.backend.domain.challenge.core.entity.Participants;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChallengeHistory {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "CHALLENGE_HISTORY_SEQUENCE_GENERATOR")
    @SequenceGenerator(name="CHALLENGE_HISTORY_SEQUENCE_GENERATOR", sequenceName = "CHALLENGE_HISTORY_SEQUENCE", initialValue = 1, allocationSize = 1)
    private Long id;

    @ManyToOne
    private Participants participant;

    private LocalDate regDate;
    private String content;
    private Integer amount;

    @Builder
    public ChallengeHistory(Participants participant, LocalDate regDate, String content, Integer amount) {
        this.participant = participant;
        this.regDate = regDate;
        this.content = content;
        this.amount = amount;
    }
}
