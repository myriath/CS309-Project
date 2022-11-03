package com.requests.backend.repositories;

import com.requests.backend.models.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {

    @Query(value="SELECT * FROM shopping_list WHERE username = :username", nativeQuery = true)
    ShoppingList[] queryGetShoppingList(@Param("username") String username);

    @Modifying
    @Query(
            value =
                    "INSERT INTO shopping_list (description, username, id, is_custom, stricken) VALUES (:description, :username, :id, :is_custom, :stricken)",
            nativeQuery = true)
    @Transactional
    void queryCreateShoppingListEntry(@Param("description") String description, @Param("username") String username, @Param("id") Integer id, @Param("is_custom") Boolean isCustom, @Param("stricken") Boolean stricken);

    @Modifying
    @Query(
            value =
                    "UPDATE shopping_list SET stricken = NOT stricken WHERE username = :username AND id = :id AND is_custom = :isCustom",
            nativeQuery = true)
    @Transactional
    void queryShoppingChangeStricken(@Param("id") int id, @Param("isCustom") boolean isCustom, @Param("username") String username);

    @Modifying
    @Query(
            value =
                    "DELETE FROM shopping_list WHERE username = :username AND id = :id",
            nativeQuery = true)
    @Transactional
    void queryDeleteListItem(@Param("username") String username, @Param("id") int id);
}
