package com.requests.backend.repositories;

import com.requests.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FollowRepository extends JpaRepository<User, Integer> {
    @Query(
            value = "SELECT follower FROM follows WHERE following = :username",
            nativeQuery = true)
    String[] queryGetFollowers(@Param("username") String username);

    @Query(
            value = "SELECT following FROM follows WHERE follower = :username",
            nativeQuery = true)
    String[] queryGetFollowing(@Param("username") String username);

    @Modifying
    @Query(
            value = "INSERT INTO follows (follower, following) VALUES (:follower, :following)",
            nativeQuery = true)
    @Transactional
    void queryAddFollow(@Param("follower") String follower, @Param("following") String following);

    @Modifying
    @Query(
            value = "DELETE FROM follows WHERE follower = :follower AND following = :following",
            nativeQuery = true)
    @Transactional
    void queryRemoveFollow(@Param("follower") String follower, @Param("following") String following);
}
