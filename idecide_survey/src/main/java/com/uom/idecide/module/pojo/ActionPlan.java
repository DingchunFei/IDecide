package com.uom.idecide.module.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

public class ActionPlan implements Serializable {

    private String surveyId;
    private Integer actionSeverity;     //1-10 to indicate the severity of situation.
    private String actionTitle;
    private String actionDetails;       //I will save this as a JSON string into MongoDB

    public ActionPlan(String surveyId, Integer actionSeverity, String actionTitle, String actionDetails) {
        this.surveyId = surveyId;
        this.actionSeverity = actionSeverity;
        this.actionTitle = actionTitle;
        this.actionDetails = actionDetails;
    }

    @Override
    public String toString() {
        return "ActionPlan{" +
                "surveyId='" + surveyId + '\'' +
                ", actionSeverity=" + actionSeverity +
                ", actionTitle='" + actionTitle + '\'' +
                ", actionDetails='" + actionDetails + '\'' +
                '}';
    }

    public String getActionTitle() {
        return actionTitle;
    }

    public void setActionTitle(String actionTitle) {
        this.actionTitle = actionTitle;
    }

    public Integer getActionSeverity() {
        return actionSeverity;
    }

    public void setActionSeverity(Integer actionSeverity) {
        this.actionSeverity = actionSeverity;
    }

    public String getActionDetails() {
        return actionDetails;
    }

    public void setActionDetails(String actionDetails) {
        this.actionDetails = actionDetails;
    }

    public String getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(String surveyId) {
        this.surveyId = surveyId;
    }

}
