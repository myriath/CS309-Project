package com.requests.backend.models;

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
    List<Recipe> queryGetAllRecipes();

    @Query(
            value = "SELECT * FROM user_recipes WHERE rname = :rname",
            nativeQuery = true)
    List<Recipe> queryGetRecipeByRname(@Param("rname") String rname);

    @Query(
            value = "SELECT * FROM user_recipes WHERE rid = :rid",
            nativeQuery = true)
    List<Recipe> queryGetRecipeByRid(@Param("rid") int rid);

}
