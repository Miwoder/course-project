package com.leverx.govoronok.service;

import com.leverx.govoronok.model.Comment;
import com.leverx.govoronok.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public List<Comment> getCommentsForUserById(Long id){
        return commentRepository.getCommentsByTrader_IdAndApprovedIsTrue(id);
    }

    public Comment getCommentForUserByCommentId(Long commentId, Long traderId){
        return commentRepository.getCommentByIdAndTrader_Id(commentId, traderId);
    }

    public List<Comment> getAllUnconfirmedComments(){
        return commentRepository.getCommentsByApprovedIsFalse();
    }

    public void addNewComment(Comment comment){
        commentRepository.save(comment);
    }

    public void deleteCommentById(Long id){
        commentRepository.deleteById(id);
    }

    public Optional<Comment> getCommentById(Long id){
        return commentRepository.findById(id);
    }

    public void updateComment(Long id, Comment updatedComment){
        Optional<Comment> commentToBeUpdated = commentRepository.findById(id);
        if (commentToBeUpdated.isPresent()) {
            commentToBeUpdated.get().setMessage(updatedComment.getMessage());
            commentToBeUpdated.get().setRating(updatedComment.getRating());
            commentToBeUpdated.get().setApproved(Boolean.FALSE);
            commentRepository.save(commentToBeUpdated.get());
        } else {
            System.out.println("ERROR UPD");
        }
    }

    public void setApprovedStatusToCommentById(Long id){
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            comment.get().setApproved(Boolean.TRUE);
            commentRepository.save(comment.get());
        } else {
            System.out.println("ERROR WITH APPR");
        }

    }

}


