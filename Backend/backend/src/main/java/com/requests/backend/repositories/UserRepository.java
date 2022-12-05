package com.requests.backend.repositories;

import com.requests.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value="SELECT * FROM users", nativeQuery = true)
    User[] queryGetAllUsers();

    @Query(
            value = "SELECT * FROM users WHERE username = :username",
            nativeQuery = true)
    User[] queryGetUserByUsername(@Param("username") String username);

    @Query(
            value = "SELECT * " +
                    "FROM users " +
                    "WHERE username = :username",
            nativeQuery = true)
    User[] queryValidateUsername(@Param("username") String username);

    @Modifying
    @Query(
            value =
                    "INSERT INTO users (username, email, p_hash, p_salt, user_type) values (:username, :email, :pHash, :pSalt, :userType)",
            nativeQuery = true)
    @Transactional
    void queryCreateUser(@Param("username") String username, @Param("email") String email, @Param("pHash") String pHash, @Param("pSalt") String pSalt, @Param("userType") String userType);

    @Modifying
    @Query(
            value = "UPDATE users SET bio = :bio WHERE username = :username",
            nativeQuery = true
    )
    @Transactional
    void queryUpdateBio(@Param("username") String username, @Param("bio") String bio);

    @Query(
            value = "SELECT bio FROM users WHERE username = :username",
            nativeQuery = true
    )
    void queryGetBio(@Param("username") String username);

    @Query(
            value = "SELECT * FROM users WHERE email = :email",
            nativeQuery = true)
    User[] queryGetUserByEmail(@Param("email") String email);

    long deleteByUsername(String username);
}