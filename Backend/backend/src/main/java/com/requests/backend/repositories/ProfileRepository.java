package com.requests.backend.repositories;


import com.requests.backend.models.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    @Query(
            value = "SELECT * " +
                    "FROM profiles " +
                    "WHERE username = :username",
            nativeQuery = true)
    Profile[] queryGetBio(@Param("username") String username);

    @Modifying
    @Query(
            value =
                    "INSERT INTO profiles (username, Bio) VALUES (:username, :bio)",
            nativeQuery = true)
    @Transactional
    void queryCreateProfile(@Param("username") String username, @Param("bio") String bio);

    @Modifying
    @Query(
            value = "DELETE FROM `fod_db`.`profiles` WHERE (`username` = :username);",
            nativeQuery = true)
    @Transactional
    void queryDeleteProfile(@Param("username") String username);

}
