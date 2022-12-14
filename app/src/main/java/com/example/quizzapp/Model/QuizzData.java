package com.example.quizzapp.Model;

import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("jsonschema2pojo")
public class QuizzData {

    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("correctAnswer")
    @Expose
    private String correctAnswer;
    @SerializedName("incorrectAnswers")
    @Expose
    private List<String> incorrectAnswers = null;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("tags")
    @Expose
    private List<String> tags = null;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("difficulty")
    @Expose
    private String difficulty;
    @SerializedName("regions")
    @Expose
    private List<Object> regions = null;
    @SerializedName("isNiche")
    @Expose
    private Boolean isNiche;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public List<String> getIncorrectAnswers() {
        return incorrectAnswers;
    }

    public void setIncorrectAnswers(List<String> incorrectAnswers) {
        this.incorrectAnswers = incorrectAnswers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public List<Object> getRegions() {
        return regions;
    }

    public void setRegions(List<Object> regions) {
        this.regions = regions;
    }

    public Boolean getIsNiche() {
        return isNiche;
    }

    public void setIsNiche(Boolean isNiche) {
        this.isNiche = isNiche;
    }

}