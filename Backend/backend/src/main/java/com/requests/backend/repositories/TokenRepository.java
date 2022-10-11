package com.requests.backend.repositories;

import com.requests.backend.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

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
        void queryAddToken(@Param("token") String token, @Param("creation_date") long creationDate, @Param("username") String username);

    // TODO Complete query logic
    @Modifying
    @Query(
            value = "UPDATE tokens " +
                    "SET token = :newToken" +
                    "WHERE token = :oldToken",
            nativeQuery = true)
    @Transactional
    void queryUpdateToken(@Param("oldToken") String oldToken, @Param("newToken") String newToken);
}

