package com.requests.backend.repositories;

import com.requests.backend.models.Comment;
import com.requests.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(
            value = "SELECT * FROM comments WHERE rid = :rid",
            nativeQuery = true)
    Comment[] queryGetCommentsByRid(@Param("rid") int rid);

    @Query(
            value = "INSERT INTO comments (rid, username, body) VALUES (:rid, :username, :body)",
            nativeQuery = true)
    @Transactional
    void queryCreateComment(@Param("rid") int rid, @Param("username") String username, @Param("body") String body);

    // Returns empty array if the user as not voted on the provided comment
    // TODO Test functionality of this query
    @Query(
            value = "SELECT * FROM comments WHERE cid " +
                    "IN (SELECT cid FROM comment_votes WHERE cid = :cid AND username = :username)",
            nativeQuery = true)
    @Modifying
    String[] queryCheckIfVotedOn(@Param("cid") int cid, @Param("username") String username);

    @Query(
            value = "UPDATE comments SET upvotes = upvotes + 1 WHERE cid = :cid",
            nativeQuery = true)
    @Transactional
    void queryUpvoteComment(@Param("cid") int cid);

    @Modifying
    @Query(
            value = "UPDATE comments SET upvotes = upvotes - 1 WHERE cid = :cid",
            nativeQuery = true)
    @Transactional
    void queryDownvoteComment(@Param("cid") int cid);


    @Modifying
    @Query(
            value = "UPDATE comments SET body = :body WHERE cid = :cid",
            nativeQuery = true)
    @Transactional
    void queryUpdateComment(@Param("cid") int commentId, @Param("body") String body);

    @Modifying
    @Query(
            value = "DELETE FROM comments WHERE cid = :cid",
            nativeQuery = true)
    @Transactional
    void queryRemoveComment(@Param("cid") int commentId);

    @Query(
            value = "SELECT * FROM comments WHERE cid = :cid",
            nativeQuery = true)
    Comment[] queryGetCommentByCid(@Param("cid") int commentId);
}
