package com.requests.backend.repositories;

import com.requests.backend.models.Token;
import com.requests.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(
            value = "SELECT * " +
                    "FROM tokens " +
                    "WHERE token = :token",
            nativeQuery = true)
    Token[] queryGetToken(@Param("token") String token);

    @Modifying
    @Query(
            value = "INSERT INTO tokens (token, creation_date, username) " +
                    "VALUES (:token, :creation_date, :username)",
            nativeQuery = true)
    @Transactional
        void queryAddToken(@Param("token") String token, @Param("creation_date") Date creationDate,  @Param("username") String username);

    // TODO Complete query logic
    @Modifying
    @Query(
            value = "UPDATE tokens " +
                    "SET token = :newToken, " +
                    "creation_date = :creation_date " +
                    "WHERE token = :oldToken",
            nativeQuery = true)
    @Transactional
    void queryUpdateToken(@Param("newToken") String newToken, @Param("creation_date") Date creationDate, @Param("oldToken") String oldToken);

    @Transactional
    long deleteByUser(User user);
}

