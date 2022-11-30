package com.requests.backend.repositories;

import com.requests.backend.models.Instruction;
import com.requests.backend.models.Recipe;
import com.requests.backend.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface InstructionRepository extends JpaRepository<Instruction, Integer> {
    @Query(
            value = "SELECT * FROM recipe_instructions WHERE rid = :rid",
            nativeQuery = true)
    Recipe[] queryGetInstructionByRid(@Param("rid") int rid);

    @Modifying
    @Query(
            value =
                    "INSERT INTO recipe_instructions (rid, step_num, step_text) VALUES (:rid, :stepNum, :stepText)",
            nativeQuery = true)
    @Transactional
    void queryCreateInstruction(@Param("rid") int rid, @Param("stepNum") int stepNum, @Param("stepText") String stepText);

    @Modifying
    @Query(
            value = "DELETE FROM `fod_db`.`recipe_instructions` WHERE (`rid` = :rid)",
            nativeQuery = true)
    @Transactional
    void queryDeleteRecipe(@Param("rid") int rid);
}

