package com.leverx.govoronok.model;


import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Data
@Entity
@Table(name = "GameObject")
public class GameObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "authorID", nullable = false)
    private Long authorId;

    @Column(name = "createdAt", nullable = false)
    private Date createdAt;

    @Column(name = "updatedAt", nullable = false)
    @LastModifiedDate
    private Date updatedAt;

    @Column(name = "gameID", nullable = false)
    private Long gameId;
}
