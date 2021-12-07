package com.leverx.govoronok.repository;

import com.leverx.govoronok.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment getCommentByIdAndTrader_Id(Long commentId, Long traderId);

    List<Comment> getCommentsByTrader_IdAndApprovedIsTrue(Long trader_id);

    List<Comment> getCommentsByApprovedIsFalse();

}
