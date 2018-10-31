package com.bulls_and_cows.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "step_game")
public class StepGame {
    private UUID id;
    private Game game;
    private LocalDateTime dateTime;
    private String answer;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    public UUID getId() {
        return id;
    }

    public StepGame setId(UUID id) {
        this.id = id;
        return this;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public Game getGame() {
        return this.game;
    }

    public StepGame setGame(Game game) {
        this.game = game;
        return this;
    }

    @Column(name = "date_time")
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public StepGame setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public String getAnswer() {
        return this.answer;
    }

    public StepGame setAnswer(String answer) {
        this.answer = answer;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StepGame)) return false;
        StepGame stepGame = (StepGame) o;
        return Objects.equals(this.id, stepGame.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }
}
