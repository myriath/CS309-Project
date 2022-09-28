package com.requests.backend.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<SearchFood, Integer> {
    @Query(value="SELECT *, MATCH (description) AGAINST ((:keywords) IN BOOLEAN MODE) AS score FROM all_foods_cleaned WHERE MATCH (description) AGAINST ((:keywords) IN BOOLEAN MODE)", nativeQuery = true)
    List<SearchFood> queryGetDbFoods(@Param("keywords") String keywords);
}
