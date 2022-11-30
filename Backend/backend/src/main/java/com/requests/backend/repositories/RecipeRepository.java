package com.requests.backend.repositories;

import com.requests.backend.models.Recipe;
import com.requests.backend.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    @Query(value="SELECT * FROM user_recipes", nativeQuery = true)
    Recipe[] queryGetAllRecipes();

    @Query(
            value = "SELECT * FROM user_recipes WHERE rname = :rname",
            nativeQuery = true)
    Recipe[] queryGetRecipeByRname(@Param("rname") String rname);

    @Query(
            value = "SELECT * FROM user_recipes WHERE rid = :rid",
            nativeQuery = true)
    Recipe[] queryGetRecipeByRid(@Param("rid") int rid);

    @Query(
            value = "SELECT * FROM user_recipes WHERE username = :username",
            nativeQuery = true)
    Recipe[] queryGetRecipeByUsername(@Param("username") String username);

    @Query(
            value = "SELECT * FROM user_recipes r WHERE r.rid = :rid",
            nativeQuery = true)
    Recipe[] queryGetImageByrid(@Param("rid") int rid);

    @Query(
            value = "SELECT DISTINCT(r.rid), r.instructions, r.rname, r.username FROM user_recipes r join tokens t on r.username = t.username where t.token != :token AND r.username != 'NULL' AND r.rname != 'NULL' AND r.instructions != 'NULL'",
            nativeQuery = true)
    Recipe[] queryrecipeList(@Param("token") String token);


    @Query(
            value = "SELECT rid, instructions, rname, username FROM user_recipes WHERE username = :Username",
            nativeQuery = true)
    Recipe[] queryuserRecipeList(@Param("Username") String Username);

    @Query (

            value = "SELECT token, creation_date, username FROM token WHERE t.token = :Token",
            nativeQuery = true)
    Token[] queryRecipeDeleteCheck(@Param("Token") String Token);

    @Modifying
    @Query(
            value =
                    "INSERT INTO user_recipes (username, rname, instructions) VALUES (:username, :rname, :instructions)",
            nativeQuery = true)
    @Transactional
    void queryCreateRecipe(@Param("username") String username, @Param("rname") String rname, @Param("instructions") String instructions);

    @Query(
            value ="SELECT * FROM user_recipes WHERE username " +
            "IN (SELECT following FROM follows WHERE follower = :username)",
            nativeQuery = true)
    Recipe[] queryGetFeed(@Param("username") String username);

    @Modifying
    @Query(
            value = "DELETE FROM `fod_db`.`user_recipes` WHERE (`rid` = :rid);",
            nativeQuery = true)
    @Transactional
    void queryDeleteRecipe(@Param("rid") int rid);



}

