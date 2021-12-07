package com.leverx.govoronok.model;


import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GameObject")
public class GameObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt = LocalDate.now();;

    @Column(name = "updatedAt", nullable = false)
    @LastModifiedDate
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "authorId", nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "gameId", nullable = false)
    private Game game;
}
