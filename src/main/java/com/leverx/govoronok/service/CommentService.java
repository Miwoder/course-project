package com.leverx.govoronok.service;

import com.leverx.govoronok.repository.CommentRepository;
import com.leverx.govoronok.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }
}
