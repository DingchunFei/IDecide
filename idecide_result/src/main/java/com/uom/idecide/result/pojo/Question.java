package com.uom.idecide.result.pojo;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable {

    private String questionId;
    private String questionType;
    private String questionText;
    private List<String> questionAnswer;

    @Override
    public String toString() {
        return "Question{" +
                "questionId='" + questionId + '\'' +
                ", questionType='" + questionType + '\'' +
                ", questionText='" + questionText + '\'' +
                ", questionAnwer=" + questionAnswer +
                '}';
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getQuestionAnwer() {
        return questionAnswer;
    }

    public void setQuestionAnwer(List<String> questionAnwer) {
        this.questionAnswer = questionAnwer;
    }
}
