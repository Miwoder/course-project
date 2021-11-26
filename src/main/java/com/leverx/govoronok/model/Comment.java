package com.leverx.govoronok.model;

import lombok.Data;
import org.springframework.stereotype.Component;

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

    @Column(name = "traderID", nullable = false)
    private Long traderId;

    @Column(name = "authorID", nullable = false)
    private Long authorId;

    @Column(name = "approved", nullable = false)
    private Boolean approved;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Column(name = "rating", nullable = false)
    private Integer rating;
}
