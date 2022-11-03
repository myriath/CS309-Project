package com.requests.backend.repositories;

import com.requests.backend.models.FoodLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;

@Repository
public interface FoodLogRepository extends JpaRepository<FoodLog, Integer> {

    @Query(
        value = "SELECT * " +
                "FROM food_log " +
                "WHERE username = :username",
        nativeQuery = true)
    FoodLog[] queryGetFoodLog(@Param("username") String username);

    @Query(
            value = "SELECT * " +
                    "FROM food_log " +
                    "WHERE username = :username " +
                    "AND date = :date",
            nativeQuery = true)
    FoodLog[] queryGetLogByDay(@Param("username") String username, @Param("date") String date);

    @Modifying
    @Query(
            value = "INSERT INTO food_log (username, fdc_id, food_name, serving_amt, serving_unit, fat, sat_fat, " +
                    "sodium, carbohydrates, fiber, sugars, protein, date, meal)" +
                "VALUES (:username, :fdc_id, :food_name, :serving_amt, :serving_unit, :fat, :sat_fat, :sodium, " +
                    ":carbohydrates, :fiber, :sugars, :protein, :date, :meal)",
            nativeQuery = true)
    @Transactional
    void queryAddToLog(
        @Param("username") String username,
        @Param("fdc_id") int fdcId,
        @Param("food_name") String foodName,
        @Param("serving_amt") double servingAmt,
        @Param("serving_unit") String servingUnit,
        @Param("fat") double fat,
        @Param("sat_fat") double satFat,
        @Param("sodium") double sodium,
        @Param("carbohydrates") double carbohydrates,
        @Param("fiber") double fiber,
        @Param("sugars") double sugars,
        @Param("protein") double protein,
        @Param("date") Date date,
        @Param("meal") String meal
    );

    @Modifying
    @Query(
            value = "DELETE FROM food_log WHERE log_id = :log_id",
            nativeQuery = true)
    void queryDeleteFromLog(@Param("log_id") int logId);
}
