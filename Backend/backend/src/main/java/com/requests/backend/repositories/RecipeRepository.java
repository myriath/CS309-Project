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



    @Modifying
    @Query(
            value =
                    "INSERT INTO user_recipes (rid, rname)",
            nativeQuery = true)
    @Transactional
    void queryCreateRecipe(@Param("rid") String rid, @Param("rname") String rname);

    @Modifying
    @Query(
            value =
                    "INSERT INTO user_recipes (username, rname, steps)",
            nativeQuery = true)
    @Transactional
    void queryCreateRecipe2(@Param("username") String username, @Param("recipeName") String rname, @Param("instructions") String steps);

    //queryCreateRecipe2(username, recipeName, instructions);

}
