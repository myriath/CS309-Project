package com.requests.backend.repositories;

import com.requests.backend.models.Recipe;
import com.requests.backend.models.SimpleFoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SimpleFoodRepository extends JpaRepository<SimpleFoodItem, Integer> {
    @Query(
            value = "SELECT * FROM simple_foods WHERE id = :db_id AND is_custom = :is_custom",
            nativeQuery = true)
    Recipe[] queryGetFoodById(@Param("db_id") int dbId, @Param("is_custom") boolean isCustom);

    @Modifying
    @Query(
            value = "DELETE FROM `fod_db`.`simple_foods` WHERE (`id` = :db_id AND is_custom = :is_custom)",
            nativeQuery = true)
    @Transactional
    void queryDeleteFoodItem(@Param("db_id") int dbId, @Param("is_custom") boolean isCustom);
}

