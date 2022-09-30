package com.requests.backend.repositories;

import com.requests.backend.models.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Integer> {

    @Query(value="SELECT * FROM shopping_list WHERE username = :username", nativeQuery = true)
    List<ShoppingList> queryGetShoppingList(@Param("username") String username);

}
