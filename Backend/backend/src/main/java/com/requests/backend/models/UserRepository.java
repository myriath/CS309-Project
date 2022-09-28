package com.requests.backend.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value="SELECT * FROM users", nativeQuery = true)
    Collection<User> queryGetAllUsers();

    @Query(
            value = "SELECT * FROM users WHERE username = :username",
            nativeQuery = true)
    Collection<User> queryGetUserByUsername(@Param("username") String username);

    @Query(
            value = "SELECT *" +
                    "FROM users " +
                    "WHERE username = :username",
            nativeQuery = true)
    Collection<User> queryValidateUsername(@Param("username") String username);

    @Modifying
    @Query(
            value =
                    "INSERT INTO users (username, p_hash, p_salt, user_type, email, full_name, age) values (:username, :pHash, :pSalt, 'User', :email, :name, :age)",
            nativeQuery = true)
    @Transactional
    void queryCreateUser(@Param("username") String username, @Param("pHash") String pHash, @Param("pSalt") String pSalt, @Param("email") String email, @Param("name") String name, @Param("age") int age);
}