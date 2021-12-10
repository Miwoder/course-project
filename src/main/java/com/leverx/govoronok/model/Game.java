package com.leverx.govoronok.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name can't be empty")
    private String name;

    @Column(name = "genre", nullable = false)
    @NotBlank(message = "Genre can't be empty")
    private GameGenre genre;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<GameObject> gameObjects;
}
