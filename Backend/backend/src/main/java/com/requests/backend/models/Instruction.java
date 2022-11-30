package com.requests.backend.models;

import javax.persistence.*;

@Entity
@Table(name="recipe_instructions")
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instruction_id;
    private int rid;
    private int stepNum;
    private String stepText;

    public Instruction(int rid, int stepNum, String stepText) {
        this.rid = rid;
        this.stepNum = stepNum;
        this.stepText = stepText;
    }

    public int getInstruction_id() {
        return instruction_id;
    }

    public void setInstruction_id(int instruction_id) {
        this.instruction_id = instruction_id;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
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
