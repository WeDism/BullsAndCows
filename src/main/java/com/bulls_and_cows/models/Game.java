package com.bulls_and_cows.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "game")
public class Game {
    private UUID id;
    private User user;
    private LocalDateTime gameStartTime;
    private LocalDateTime gameEndTime;
    private String riddle;
    private List<StepGame> stepGames = new ArrayList<>();

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "uuid")
    public UUID getId() {
        return this.id;
    }

    public Game setId(UUID id) {
        this.id = id;
        return this;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public User getUser() {
        return this.user;
    }

    public Game setUser(User user) {
        this.user = user;
        return this;
    }

    @Column(name = "game_start_time")
    public LocalDateTime getGameStartTime() {
        return this.gameStartTime;
    }

    public Game setGameStartTime(LocalDateTime gameStartTime) {
        this.gameStartTime = gameStartTime;
        return this;
    }

    @Column(name = "game_end_time")
    public LocalDateTime getGameEndTime() {
        return this.gameEndTime;
    }

    public Game setGameEndTime(LocalDateTime gameEndTime) {
        this.gameEndTime = gameEndTime;
        return this;
    }

    public String getRiddle() {
        return this.riddle;
    }

    public Game setRiddle(String riddle) {
        this.riddle = riddle;
        return this;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "game")
    public List<StepGame> getStepGames() {
        return stepGames;
    }

    public Game setStepGames(List<StepGame> stepGames) {
        this.stepGames = stepGames;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(this.getId(), game.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
