package com.requests.backend.repositories;

import com.requests.backend.models.Recipe;
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
            value = "SELECT * FROM user_recipes r WHERE r.rid = :rid",
            nativeQuery = true)
    Recipe[] queryGetImageByrid(@Param("rid") int rid);


    @Modifying
    @Query(
            value =
                    "INSERT INTO user_recipes (username, rname, instructions) VALUES (:username, :rname, :instructions)",
            nativeQuery = true)
    @Transactional
    void queryCreateRecipe(@Param("username") String username, @Param("rname") String rname, @Param("instructions") String instructions);








}

