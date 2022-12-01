package com.requests.backend.models;

import javax.persistence.*;

@Entity
@Table(name="recipe_instructions")
public class Instruction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int instruction_id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rid")
    private Recipe recipe;
    private int stepNum;
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
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
