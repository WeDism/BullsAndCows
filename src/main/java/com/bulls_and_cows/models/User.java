package com.bulls_and_cows.models;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_entity")
public class User {
    private String nickname;
    private String password;
    private Role role;
    private String email;
    private float rating;
    private List<Game> games = new ArrayList<>();

    @Id
    public String getNickname() {
        return this.nickname;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    @NotBlank
    @Size(min = 4, max = 200, message = "Password must be between 4 and 200 characters long.")
    @Column(nullable = false)
    public String getPassword() {
        return this.password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Role getRole() {
        return this.role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$",
            message = "Mail isn't valid")
    @Column(nullable = false, unique = true)
    public String getEmail() {
        return this.email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public float getRating() {
        return this.rating;
    }

    public User setRating(float rating) {
        this.rating = rating;
        return this;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user", orphanRemoval = true)
    public List<Game> getGames() {
        return this.games;
    }

    public User setGames(List<Game> games) {
        this.games = games;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(this.getNickname(), user.getNickname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getNickname());
    }

    @Override
    public String toString() {
        return "User{" +
                "nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", email='" + email + '\'' +
                ", rating=" + rating +
                ", games=" + Arrays.toString(games.toArray()) +
                '}';
    }

    public enum Role {
        USER
    }
}
