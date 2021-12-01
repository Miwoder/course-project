package com.leverx.govoronok.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;
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
    private String name;
    @Column(name = "genre", nullable = false)
    private GameGenre genre;

    @OneToMany(mappedBy = "game", fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Set<GameObject> gameObjects;
}
