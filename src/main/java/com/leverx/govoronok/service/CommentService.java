package com.leverx.govoronok.service;

import com.leverx.govoronok.model.Comment;
import com.leverx.govoronok.repository.CommentRepository;
import com.leverx.govoronok.repository.UserRepository;
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

    public List<Comment> getCommentsForUserById(Long id){
        return commentRepository.getCommentsByTrader_Id(id);
    }

    public Comment getCommentForUserByCommentId(Long commentId, Long traderId){
        return commentRepository.getCommentByIdAndTrader_Id(commentId, traderId);
    }

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

}


