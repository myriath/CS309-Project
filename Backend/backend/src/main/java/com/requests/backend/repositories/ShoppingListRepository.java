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
    List<ShoppingList> queryGetShoppingList(@Param("username") String username);

    @Modifying
    @Query(
            value =
                    "INSERT INTO shopping_list (fdc_id, username, item_name, stricken) VALUES (:fdc_id, :username, :item_name, :stricken)",
            nativeQuery = true)
    @Transactional
    void queryCreateShoppingListEntry(@Param("username") String username, @Param("item_name") String itemName, @Param("fdc_id") Integer fdcId, @Param("stricken") Boolean stricken);

    @Modifying
    @Query(
            value =
                    "UPDATE shopping_list SET stricken = NOT stricken WHERE username = :username AND item_name = :item_name",
            nativeQuery = true)
    @Transactional
    void queryShoppingChangeStricken(@Param("username") String username, @Param("item_name") String itemName);
}
