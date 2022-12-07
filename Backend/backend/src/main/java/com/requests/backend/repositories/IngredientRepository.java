package com.requests.backend.repositories;

import com.requests.backend.models.Ingredient;
import com.requests.backend.models.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Integer> {
    @Query(
            value = "SELECT * FROM recipe_ingredients WHERE rid = :rid",
            nativeQuery = true)
    Recipe[] queryGetIngredientByRid(@Param("rid") int rid);

    @Modifying
    @Query(
            value =
                    "INSERT INTO recipe_ingredients (rid, food_id, is_custom, quantity, unit) VALUES (:rid, :itemId, :isCustom, :quantity, :unit)",
            nativeQuery = true)
    @Transactional
    void queryCreateIngredient(@Param("rid") int rid, @Param("itemId") int itemId, @Param("isCustom") boolean isCustom, @Param("quantity") double quantity, @Param("unit") String unit);

    @Modifying
    @Query(
            value = "DELETE FROM `fod_db`.`recipe_ingredients` WHERE (`rid` = :rid)",
            nativeQuery = true)
    @Transactional
    void queryDeleteByRecipe(@Param("rid") int rid);
}

