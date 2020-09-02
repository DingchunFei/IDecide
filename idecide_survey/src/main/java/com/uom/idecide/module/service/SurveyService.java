package com.uom.idecide.module.service;


import com.uom.idecide.module.dao.SurveyDao;
import com.uom.idecide.module.pojo.ActionPlan;
import com.uom.idecide.module.pojo.Survey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private SurveyDao surveyDao;


    public void save(Survey survey) {
        surveyDao.save(survey);
    }

    public List<Survey> findAllByJsonStrNotNull(){
        return surveyDao.findAllByJsonStrNotNull();
    }

    public Survey findById(String id) {
        return surveyDao.findById(id).get();
    }

    public void deleteById(String id) {
        surveyDao.deleteById(id);
    }
/*
    public List<ActionPlan> getAllActionsBySurveyId(String id) {
        return actionPlanDao.findAllBySurveyId(id);
    }

    public ActionPlan getBySurveyIdAndActionSeverity(String surveyId, int actionSeverity){
        return actionPlanDao.findBySurveyIdAndActionSeverity(surveyId,actionSeverity);
    }

    public void saveActionPlan(ActionPlan actionPlan){
        actionPlanDao.save(actionPlan);
    }*/
}
