package com.uom.idecide.result.pojo;

import java.io.Serializable;
import java.util.List;

public class Answer implements Serializable {

    private String userId;
    private String surveyId;
    private String sectionId;
    private String completedTime;
    private List<Question> questions;

    @Override
    public String toString() {
        return "Answer{" +
                "userId='" + userId + '\'' +
                ", surveyId='" + surveyId + '\'' +
                ", sectionId='" + sectionId + '\'' +
                ", completedTimd='" + completedTime + '\'' +
                ", questions=" + questions +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getCompletedTimd() {
        return completedTime;
    }

    public void setCompletedTimd(String completedTimd) {
        this.completedTime = completedTimd;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
