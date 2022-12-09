package com.requests.backend.repositories;

import com.requests.backend.models.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {
    @Modifying
    @Query(
            value =
                    "UPDATE shopping_list SET stricken = NOT stricken WHERE shopping_lists_username = :username AND food_id = :id AND food_custom = :isCustom",
            nativeQuery = true)
    @Transactional
    void queryShoppingChangeStricken(@Param("id") int id, @Param("isCustom") boolean isCustom, @Param("username") String username);

    @Modifying
    @Query(
            value =
                    "DELETE FROM shopping_list WHERE shopping_lists_username = :username AND food_id = :id AND food_custom = :isCustom",
            nativeQuery = true)
    @Transactional
    void queryDeleteListItem(@Param("id") int id, @Param("isCustom") boolean isCustom, @Param("username") String username);
}
