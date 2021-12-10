package com.leverx.govoronok.model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

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
    @NotBlank(message = "Title can't be empty")
    @Size(min = 3, max = 20, message = "Title can't be less 3 symbols and more than 20")
    private String title;

    @Column(name = "createdAt", nullable = false)
    @NotBlank(message = "Created date can't be empty")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updatedAt", nullable = false)
    @UpdateTimestamp
    @NotBlank(message = "Updated date can't be empty")
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "authorId", nullable = false)
    @NotBlank(message = "Author can't be empty")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "gameId", nullable = false)
    @NotBlank(message = "Game can't be empty")
    private Game game;
}
