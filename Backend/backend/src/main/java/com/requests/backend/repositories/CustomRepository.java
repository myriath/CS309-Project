package com.requests.backend.repositories;

import com.requests.backend.models.CustomFood;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomRepository extends JpaRepository<CustomFood, Integer> {
    @Query(
            value = "SELECT * FROM custom_foods WHERE name LIKE '%:search%' LIMIT 10",
            nativeQuery = true
    )
    CustomFood[] queryGetCustomFoods(@Param("search") String search);

    @Query(
            value = "INSERT INTO custom_foods (name, name, calories, carbs, protein, fat) VALUES (:name, :calories, :carbs, :protein, :fat)",
            nativeQuery = true
    )
    void queryCreateCustomFood(@Param("name") String name, @Param("calories") double calories, @Param("carbs") double carbs, @Param("protein") double protein, @Param("fat") double fat);


}
