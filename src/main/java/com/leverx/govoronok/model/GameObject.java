package com.leverx.govoronok.model;


import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Data
@Entity
public class GameObject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private Long authorId;
    private Date createdAt;
    private Date updatedAt;
    private Long gameId;
}
