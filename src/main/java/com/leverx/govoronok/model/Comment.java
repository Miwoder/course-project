package com.leverx.govoronok.model;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "message", nullable = false)
    private String message;

    @Column(name = "approved", nullable = false)
    private Boolean approved;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "traderId", nullable = false)
    private User trader;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "authorId", nullable = false)
    private User author;
}
