package com.leverx.govoronok.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String message;
    private Long traderId;
    private Long authorId;
    private Boolean approved;
    private Date createdAt;
    private Integer rating;
}
