package com.leverx.govoronok.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "message", nullable = false)
    @NotBlank(message = "Message can't be empty")
    @Size(min = 3, message = "Message can't be less 3 symbols")
    private String message;

    @Column(name = "approved", nullable = false)
    private Boolean approved = Boolean.FALSE;

    @Column(name = "createdAt", nullable = false)
    @NotBlank(message = "Created date can't be empty")
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "rating", nullable = false)
    @NotBlank(message = "Message can't be empty")
    @Size(min = 1, max = 1, message = "Rating can be only 1 digit")
    private Integer rating;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "traderId", nullable = false)
    @NotBlank(message = "Trader can't be empty")
    private User trader;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "authorId", nullable = false)
    @NotBlank(message = "Author can't be empty")
    private User author;
}
