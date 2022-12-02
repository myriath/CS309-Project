package com.requests.backend.models;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@Table(name="recipe_instructions")
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Expose
    private int instruction_id;
    @Expose
    private int stepNum;
    @Expose
    private String stepText;

    public Instruction(int stepNum, String stepText) {
        this.stepNum = stepNum;
        this.stepText = stepText;
    }

    public Instruction() {}

    public int getInstruction_id() {
        return instruction_id;
    }

    public void setInstruction_id(int instruction_id) {
        this.instruction_id = instruction_id;
    }

    public int getStepNum() {
        return stepNum;
    }

    public void setStepNum(int stepNum) {
        this.stepNum = stepNum;
    }

    public String getStepText() {
        return stepText;
    }

    public void setStepText(String stepText) {
        this.stepText = stepText;
    }
}
