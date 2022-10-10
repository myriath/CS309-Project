package com.requests.backend.repositories;

import com.requests.backend.models.Token;
import com.requests.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(
            value = "SELECT * " +
                    "FROM tokens " +
                    "WHERE token = :token",
            nativeQuery = true)
    Token[] queryGetToken(@Param("token") String token);

    @Modifying
    @Query(
            value = "INSERT INTO tokens (token) " +
                    "VALUES (:token)",
            nativeQuery = true)
    @Transactional
    void queryAddToken(@Param("token") String token);

    // TODO Complete query logic
    @Modifying
    @Query(
            value = "UPDATE tokens " +
                    "SET column1 = value1" +
                    "WHERE condition",
            nativeQuery = true)
    @Transactional
    void queryUpdateToken(@Param("oldToken") String oldToken, @Param("newToken") String newToken);
}

