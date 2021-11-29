package com.leverx.govoronok.service;

import com.leverx.govoronok.model.Comment;
import com.leverx.govoronok.model.Game;
import com.leverx.govoronok.repository.CommentRepository;
import com.leverx.govoronok.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("commentService")
public class CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

//    public Optional<List<Comment>> getCommentsByUsersId(Long id){
//        return ;
//    }

}


